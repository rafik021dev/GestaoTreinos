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
	    public void inserirTreino(Treino treino) throws SQLException {
	        String sSql = "INSERT INTO treino (data, tipo, idusuario) "
	                   + "VALUES (?, ?, ?)";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setDate(1, Date.valueOf(treino.getData()));
	            stmt.setString(2, treino.getTipo().toString());
	            stmt.setLong(3, treino.getUsuario().getIdUsuario());
	            stmt.executeUpdate();
	        }
	    }    
	    /*
	     * atualizar na tabela treino
	     */
	    public void atualizarTreino(Treino treino) throws SQLException {
	        String sSql = "UPDATE treino SET data = ?, tipo = ?, idusuario = ? "
	                   + "WHERE idtreino = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setDate(1, Date.valueOf(treino.getData()));
	            stmt.setString(2, treino.getTipo().toString());
	            stmt.setLong(3, treino.getUsuario().getIdUsuario());
	            stmt.setLong(4, treino.getIdTreino());
	            stmt.executeUpdate();
	        }
	    }
	    /*
	     * deletar na tabela treino
	     */
	    public void deletarTreino(long id) throws SQLException {
	        String sSql = "DELETE FROM treino WHERE idtreino = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, id);
	            stmt.executeUpdate();
	        }
	    }	    
	    /*
	     * buscar pelo idtreino
	     */
	        public Treino buscarPorIdtreino(long id) throws SQLException {
	            String sSql = "SELECT idtreino, data, tipo, idusuario FROM treino WHERE idtreino = ?";

	            try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	                stmt.setLong(1, id);
	                try (ResultSet rs = stmt.executeQuery()) {
	                    if (rs.next()) {
	                        Treino treinoRS = new Treino();
	                        
	                        treinoRS.setData(rs.getDate("data").toLocalDate());
	                        treinoRS.setIdTreino(rs.getInt("idtreino"));
	                        treinoRS.setTipo(TipoTreino.valueOf(rs.getString("tipo")));
	                        
	                        Usuario usuario = new Usuario();
	                        usuario.setIdUsuario((int)rs.getLong("idusuario"));
	                        treinoRS.setUsuario(usuario);
	                        
	                        return treinoRS;
	                    }
	                }
	            }  
	            return null;
	        }	      	    	   	    	    	    
  }
