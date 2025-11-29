package gestaotreinos.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import gestaotreinos.enums.TipoTreino;
import gestaotreinos.model.entity.Treino;
import gestaotreinos.model.entity.Usuario;

public class TreinoDAO {

	    private Connection conn;

	    public TreinoDAO(Connection conn) {
	        this.conn = conn;
	    }
	   /*
	    * inserir na tabela treino 
	    */
	    public boolean inserirTreino(Treino treino) {
	        String sSql = "INSERT INTO treino (data, tipo, idusuario) "
	                   + "VALUES (?, ?, ?)";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setDate(1, Date.valueOf(treino.getData()));
	            ps.setString(2, treino.getTipo().name());
	            ps.setInt(3, treino.getUsuario().getIdUsuario());
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }    
	    /*
	     * atualizar na tabela treino
	     */
	    public boolean atualizarTreino(Treino treino) {
	        String sSql = "UPDATE treino SET data = ?, tipo = ?, idusuario = ? "
	                   + "WHERE idtreino = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setDate(1, Date.valueOf(treino.getData()));
	            ps.setString(2, treino.getTipo().name());
	            ps.setInt(3, treino.getUsuario().getIdUsuario());
	            ps.setInt(4, treino.getIdTreino());
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * deletar na tabela treino
	     */
	    public boolean deletarTreino(int id) {
	        String sSql = "DELETE FROM treino WHERE idtreino = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setInt(1, id);
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }	    
	    /*
	     * buscar pelo idtreino
	     */
	        public Treino buscarPorIdtreino(int id) throws SQLException {
	            String sSql = "SELECT idtreino, data, tipo, idusuario FROM treino WHERE idtreino = ?";

	            try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	                ps.setInt(1, id);
	                try (ResultSet rs = ps.executeQuery()) {
	                    if (rs.next()) {
	                        Treino treinoRS = new Treino();
	                        
	                        treinoRS.setData(rs.getDate("data").toLocalDate());
	                        treinoRS.setIdTreino(rs.getInt("idtreino"));
	                        treinoRS.setTipo(TipoTreino.valueOf(rs.getString("tipo")));
	                        
	                        Usuario usuario = new Usuario();
	                        usuario.setIdUsuario(rs.getInt("idusuario"));
	                        treinoRS.setUsuario(usuario);
	                        
	                        return treinoRS;
	                    }
	                }
	            }  
	            return null;
	        }	      	    	   	    	    	    
  }
