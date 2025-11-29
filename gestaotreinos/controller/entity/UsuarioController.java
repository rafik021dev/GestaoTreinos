package gestaotreinos.controller.entity;

import java.sql.Connection;
import java.sql.SQLException;
import gestaotreinos.model.bo.UsuarioBO;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.entity.Usuario;

public class UsuarioController {

    private Connection conn;

    public UsuarioController() {
       
        ConexaoBD conexaoBD = new ConexaoBD();
        this.conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");
    }

    public String cadastrar(String nome, String email, String senha, 
                            String sexoTexto, String idadeTexto, 
                            String pesoTexto, String alturaTexto, String metaTexto) {
        try {
           
            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                return "Erro: Nome, Email e Senha são obrigatórios.";
            }
            
            if (sexoTexto == null || sexoTexto.isEmpty()) {
                return "Erro: Selecione o sexo (Masculino ou Feminino).";
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

            UsuarioBO bo = new UsuarioBO(conn);
            
            if (bo.inserirUsuario(novoUsuario)) {
                return "Sucesso: Cadastro realizado!";
            } else {
                return "Erro: Falha ao salvar no banco de dados.";
            }

        } catch (NumberFormatException e) {
            return "Erro de Formatação: Verifique se Idade, Peso e Altura são números válidos.";
        } catch (IllegalArgumentException e) {
            return "Atenção: " + e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro técnico: " + e.getMessage();
        }
    }
}