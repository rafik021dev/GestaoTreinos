/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.dao;


import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.util.List;

public class teste {

    public static void main(String[] args) {
        Connection conn = null;
        
        try {
            
            ConexaoBD conexaoBD = new ConexaoBD();
            conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            Usuario usuarioTeste = new Usuario();
            usuarioTeste.setIdUsuario(2);
            usuarioTeste.setNome("gustavo");
            usuarioTeste.setSexo('M');
            usuarioTeste.setIdade(18);
            usuarioTeste.setPeso(80.0);
            usuarioTeste.setAltura(1.80);
            usuarioTeste.setMetaPeso(78.00);
            usuarioTeste.setEmail("gustavo@email.com");
            usuarioTeste.setSenha("12345");
            
            System.out.println("\nusuario inserido");

            List<Usuario> usuarios = usuarioDAO.listarUsuarios();
            
            System.out.println("\nqtd usuarios: " + usuarios.size());
            
                for (Usuario u : usuarios) {
                    System.out.println("ID: " + u.getIdUsuario());
                    System.out.println("Nome: " + u.getNome());
                    System.out.println("Email: " + u.getEmail());
                    System.out.println("Peso: " + u.getPeso());
                    System.out.println("   ");
                }
                usuarioDAO.deletarUsuario(2);
                System.out.println("usuario deletado");
                
                List<Usuario> usuarios2 = usuarioDAO.listarUsuarios();
                
                System.out.println("\nnumero de usuarios: " + usuarios.size());
                
                    for (Usuario u : usuarios) {
                        System.out.println("ID: " + u.getIdUsuario());
                        System.out.println("Nome: " + u.getNome());
                        System.out.println("Email: " + u.getEmail());
                        System.out.println("Peso: " + u.getPeso());
                        System.out.println("   ");
                    }
                    
                    Usuario usuario = usuarioDAO.buscarPorIdusuario(1); 
                    System.out.println("busca:");
                    if (usuario != null) {
                        System.out.println("ID: " + usuario.getIdUsuario());
                        System.out.println("Nome: " + usuario.getNome());
                        System.out.println("Email: " + usuario.getEmail());
                        System.out.println("Peso: " + usuario.getPeso());
                    } 
                    
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }         
        }
    }
}

