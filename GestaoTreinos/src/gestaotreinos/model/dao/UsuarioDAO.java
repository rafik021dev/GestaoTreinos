package gestaotreinos.model.dao;
import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class UsuarioDAO {

	private Connection conn;
	
	public UsuarioDAO(Connection conn) {
        this.conn = conn;
	}

    /**
     * Método responsável por Cadastrar o Usuario no Sistema
     * @param oUsuario
     * @throws SQLException
     */
	public void insertUsuario(Usuario oUsuario) throws SQLException{
		String sSql = "INSERT INTO usuario "
                + "(nome, sexo, idade, peso, altura, metapeso, email, senha)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sSql)){
			stmt.setString(1, oUsuario.getNome());
			stmt.setString(2,String.valueOf(oUsuario.getSexo()));
			stmt.setInt(3, oUsuario.getIdade());
			stmt.setDouble(4, oUsuario.getPeso());
			stmt.setDouble(5, oUsuario.getAltura());
			
			if(oUsuario.getMetaPeso() != null) {
				stmt.setDouble(6, oUsuario.getMetaPeso());
			}
			else {	
				stmt.setNull(6, java.sql.Types.DECIMAL);
			}
			
			stmt.setString(7, oUsuario.getEmail());
			stmt.setString(8, oUsuario.getSenha());

			stmt.executeUpdate();
		}
	}
	/*
	 * metodo responsável por atualizar um usuário ja cadastrado no sistema
	 */
	public void atualizarUsuario(Usuario oUsuario) throws SQLException{
		String sSql = "UPDATE usuario SET"
				+"nome = ?, sexo = ?, idade = ?, peso = ?,"
				+ " altura = ?, metapeso = ?, email = ?, senha = ? "
				+"WHERE idusuario = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sSql)){
			stmt.setString(1, oUsuario.getNome());
			stmt.setString(2,String.valueOf(oUsuario.getSexo()));
			stmt.setInt(3, oUsuario.getIdade());
			stmt.setDouble(4, oUsuario.getPeso());
			stmt.setDouble(5, oUsuario.getAltura());
			
			if(oUsuario.getMetaPeso() != null) {
				stmt.setDouble(6, oUsuario.getMetaPeso());
			}
			else {	
				stmt.setNull(6, java.sql.Types.DECIMAL);
			}
			
			stmt.setString(7, oUsuario.getEmail());
			stmt.setString(8, oUsuario.getSenha());
			stmt.setLong(9, oUsuario.getIdUsuario());

			stmt.executeUpdate();
		}
	}	
	/*
	 * metodo responável por deletar um usuário cadastrado no sistema
	 */
	public void deletarUsuario(long id) throws SQLException{
		String sSql = "DELETE FROM usuario WHERE idusuario = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sSql)){
			stmt.setLong(1, id);
			stmt.executeUpdate();
		}
	}
	/* 
	 * metodo para realizar uma busca por id nos usuários do sistema
	 */
	    public Usuario buscarPorId(long id) throws SQLException {
	        String sql = "SELECT idusuario, nome, sexo, idade, peso, altura, "
	                   + "metapeso, email, senha FROM usuario WHERE idusuario = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setLong(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
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
	                }
	            }
	        }
	        return null;
	    }
	    
	    /* 
	     * metodo para listar todos usuários do sistema por ordem de id
	     */
	    public List<Usuario> listarUsuarios() throws SQLException {
	        String sSql = "SELECT idusuario, nome, sexo, idade, peso, altura, "
	                   + "metapeso, email, senha FROM usuario ORDER BY idusuario";
	        
	        List<Usuario> lista = new ArrayList();	
	
	        try (PreparedStatement stmt = conn.prepareStatement(sSql);
	        		ResultSet rs = stmt.executeQuery()) {
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
	    
        /**
         * Método responsável por validar o Login do Usuário no Sistema
         * @param oUsuario
         * @return ResultSet ResSet
         * @todo TESTAR!
         
        public ResultSet autenticacaoUsuario(Usuario oUsuario) {
            //conn = new ConexaoBD().//amanhã, adaptar o novo método de conexao passando os parâmetros corretos
        //método não testado, apenas copiei o que tinha feito no NetBeans
            try {
                String sSql = "SELECT nome," +
                                    " senha " +
                                "FROM usuario " +
                               "WHERE nome = ? " +
                                 "AND senha = ?";

                PreparedStatement statement = conn.prepareStatement(sSql);

                statement.setString(1, oUsuario.getNome());
                statement.setString(2, oUsuario.getSenha());

                ResultSet ResSet = statement.executeQuery();

                return ResSet;

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);

                return null;
            }
        }*/	
	}

