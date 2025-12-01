package gestaotreinos.model.dao;

import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

public class UsuarioDAO {

	static Connection conn;
	
	public UsuarioDAO(Connection conn) {
        this.conn = conn;
	}

    /**
     * Método responsável por Cadastrar o Usuario no Sistema
     * @param oUsuario
     * @throws SQLException
     */
	public boolean inserir(Usuario oUsuario) {
		String sSql = "INSERT INTO usuario "
                + "(nome, sexo, idade, peso, altura, metapeso, email, senha)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sSql)){
			ps.setString(1, oUsuario.getNome());
			ps.setString(2,String.valueOf(oUsuario.getSexo()));
			ps.setInt(3, oUsuario.getIdade());
			ps.setDouble(4, oUsuario.getPeso());
			ps.setDouble(5, oUsuario.getAltura());
			
			if(oUsuario.getMetaPeso() != null) {
				ps.setDouble(6, oUsuario.getMetaPeso());
			}
			else {	
				ps.setNull(6, java.sql.Types.DECIMAL);
			}
			
			ps.setString(7, oUsuario.getEmail());
			ps.setString(8, oUsuario.getSenha());

			ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
		}
	}
	/*
	 * metodo responsável por atualizar um usuário ja cadastrado no sistema
	 */
	public boolean atualizar(Usuario oUsuario) {
		String sSql = "UPDATE usuario SET"
				+"nome = ?, sexo = ?, idade = ?, peso = ?,"
				+ " altura = ?, metapeso = ?, email = ?, senha = ? "
				+"WHERE idusuario = ?";
		try (PreparedStatement ps = conn.prepareStatement(sSql)){
			ps.setString(1, oUsuario.getNome());
			ps.setString(2,String.valueOf(oUsuario.getSexo()));
			ps.setInt(3, oUsuario.getIdade());
			ps.setDouble(4, oUsuario.getPeso());
			ps.setDouble(5, oUsuario.getAltura());
			
			if(oUsuario.getMetaPeso() != null) {
				ps.setDouble(6, oUsuario.getMetaPeso());
			}
			else {	
				ps.setNull(6, java.sql.Types.DECIMAL);
			}
			
			ps.setString(7, oUsuario.getEmail());
			ps.setString(8, oUsuario.getSenha());
			ps.setInt(9, oUsuario.getIdUsuario());

			ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
		}
	}	
	/*
	 * metodo responável por deletar um usuário cadastrado no sistema
	 */
	public boolean deletar(int idUsuario) {
		String sSql = "DELETE FROM usuario WHERE idusuario = ?";
		
		try (PreparedStatement ps = conn.prepareStatement(sSql)){
			ps.setInt(1, idUsuario);
			ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
		}
	}
	/* 
	 * metodo para realizar uma busca por id nos usuários do sistema
	 */
	    public Usuario buscarPorId(int idUsuario) throws SQLException {
	        String sSql = "SELECT idusuario, nome, sexo, idade, peso, altura, "
	                   + "metapeso, email, senha FROM usuario WHERE idusuario = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setInt(1, idUsuario);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                   Usuario usuarioRS = new Usuario();
	                   usuarioRS.setIdUsuario(rs.getInt("idusuario"));
	                   usuarioRS.setNome(rs.getString("nome"));
	                   usuarioRS.setSexo(rs.getString("sexo").charAt(0));
	                   usuarioRS.setIdade(rs.getInt("idade"));	                  
	                   usuarioRS.setPeso(rs.getDouble("peso"));
	                   usuarioRS.setAltura(rs.getDouble("altura"));
	                   usuarioRS.setMetaPeso(rs.getDouble("metapeso"));
	                   usuarioRS.setEmail(rs.getString("email"));
	                   usuarioRS.setSenha(rs.getString("senha"));	 
	                   
	                   return usuarioRS;
	                }else 
	                   return null;
	            }
	       }
	    }
            /*metodo responsavel por buscar id com email e senha
            * @param email, senha
            *return idusuario
            */
            public int BuscarIdPorEmailSenha(String email, String senha){
                String sSql = "SELECT idusuario FROM usuario where email = ? AND senha = ?";
                try (PreparedStatement ps = conn.prepareStatement(sSql)){
                    ps.setString(1, email);
                    ps.setString(2, senha);
                    try(ResultSet rs = ps.executeQuery()){
                        if(rs.next()){
                            return rs.getInt("idusuario");
                        }
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                return 0;
            }           
	    /* 
	     * metodo para listar todos usuários do sistema por ordem de id
	     */
	    public List<Usuario> listarUsuarios() throws SQLException {
	        String sSql = "SELECT idusuario, nome, sexo, idade, peso, altura, "
	                   + "metapeso, email, senha FROM usuario ORDER BY idusuario";
	        
	        List<Usuario> lista = new ArrayList<>();	
	
	        try (PreparedStatement ps = conn.prepareStatement(sSql);
	        		ResultSet rs = ps.executeQuery()) {
	        			while(rs.next()) {
	        				 Usuario usuarioRS = new Usuario();
	  	                   usuarioRS.setIdUsuario(rs.getInt("idusuario"));
	  	                   usuarioRS.setNome(rs.getString("nome"));
	  	                   usuarioRS.setSexo(rs.getString("sexo").charAt(0));
	  	                   usuarioRS.setIdade(rs.getInt("idade"));
	  	                   usuarioRS.setPeso(rs.getDouble("peso"));
	  	                   usuarioRS.setAltura(rs.getDouble("altura"));
	  	                   usuarioRS.setMetaPeso(rs.getDouble("metapeso"));
	  	                   usuarioRS.setEmail(rs.getString("email"));
	  	                   usuarioRS.setSenha(rs.getString("senha"));	 
	  	                   
	  	                   lista.add(usuarioRS);
	        			}
	        }
	        	return lista;
	    }  
        /*
         * Método responsável por validar o Login do Usuário no Sistema
         * @param oUsuario
         * @return ResultSet ResSet
	*/
         
        public ResultSet autenticacaoUsuario(Usuario oUsuario) {
            conn = new ConexaoBD().conectaBD();
            try {
                String sSql = "SELECT email," +
                                    " senha " +
                                "FROM usuario " +
                               "WHERE email = ? " +
                                 "AND senha = ?";

                PreparedStatement statement = conn.prepareStatement(sSql);

                statement.setString(1, oUsuario.getEmail());
                statement.setString(2, oUsuario.getSenha());

                ResultSet ResSet = statement.executeQuery();

                return ResSet;

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
                return null;
            }
        }
        public static Usuario buscarUsuarioPorEmailSenha(String email, String senha) {
            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, senha);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Usuario u = new Usuario();
                        u.setIdUsuario(rs.getInt("idusuario"));
                        u.setNome(rs.getString("nome"));
                        u.setSexo(rs.getString("sexo").charAt(0));
                        u.setIdade(rs.getInt("idade"));
                        u.setPeso(rs.getDouble("peso"));
                        u.setAltura(rs.getDouble("altura"));
                        u.setMetaPeso(rs.getDouble("metapeso"));
                        u.setEmail(rs.getString("email"));
                        u.setSenha(rs.getString("senha"));
                        return u;
                    }
                }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}
	


