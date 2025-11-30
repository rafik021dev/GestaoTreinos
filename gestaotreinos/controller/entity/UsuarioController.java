package gestaotreinos.controller.entity;

import java.sql.Connection;
import java.sql.SQLException;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.UsuarioDAO;
import gestaotreinos.model.entity.Usuario;
import java.util.List;

public class UsuarioController {

    private Connection conn;

    public UsuarioController() {
       
        ConexaoBD conexaoBD = new ConexaoBD();
        this.conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");
    }

    public String cadastrar(String nome, String email, String senha, 
                            String sexoTexto, String idadeTexto, 
                            String pesoTexto, String alturaTexto,
                            String metaTexto) throws SQLException {
        try {          
            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                return "erro: nome, email e senha sao obrigatorios.";
            }
            
            if (sexoTexto == null || sexoTexto.isEmpty()) {
                return "erro: selecione o sexo.";
            }

            char sexo = sexoTexto.toUpperCase().charAt(0); 
            int idade = Integer.parseInt(idadeTexto);
            double peso = Double.parseDouble(pesoTexto);
            
            double altura = Double.parseDouble(alturaTexto.replace(",", "."));

            Double metaPeso = null;
            if (metaTexto != null && !metaTexto.isEmpty()) {
                try {
                    double valorMeta = Double.parseDouble(metaTexto.replace(",", "."));
                    if (valorMeta > 0) {
                        metaPeso = valorMeta;
                    }
                } catch (NumberFormatException e) {                   
                    metaPeso = null; 
                }
            }

            Usuario novoUsuario = new Usuario(nome, sexo, idade, peso, altura, metaPeso);
            novoUsuario.setEmail(email);
            novoUsuario.setSenha(senha);
            
            if (inserir(novoUsuario)){
                return "Cadastro realizado.";
            } else {
                return "erro: falha ao salvar no banco de dados.";
            }
            
        } catch (NumberFormatException e) {
            return e.getMessage();
        }
    }    	
        public boolean inserir(Usuario usuario) throws SQLException {
            UsuarioDAO dao = new UsuarioDAO(conn);

            List<Usuario> lista = dao.listarUsuarios();
            if (lista != null) {
                for(Usuario u : lista){

                    if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                        throw new IllegalArgumentException("este email ja esta cadastrado.");
                    }
                }
            }
         return dao.inserir(usuario);
        }
	
	public boolean atualizarUsuario(Usuario usuario){
            UsuarioDAO dao = new UsuarioDAO(conn);
            if(usuario.getIdUsuario() <= 0) {
		return false;
            }else {	
		return dao.atualizar(usuario);	
            }
	}
	
	public boolean DeletarUsuario(int idUsuario){
            UsuarioDAO dao = new UsuarioDAO(conn);
            if(idUsuario <= 0) {
		return false;
            }else {
		return dao.deletar(idUsuario);
            }
	}	
}