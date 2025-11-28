package gestaotreinos.model.dao;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import gestaotreinos.enums.TipoRefeicao;
import gestaotreinos.model.entity.Refeicao;

public class RefeicaoDAO {
	
	    private Connection conn;

	    public RefeicaoDAO(Connection conn) {
	        this.conn = conn;
	    }
	    
	    public void inserirRefeicao(Refeicao refeicao) throws SQLException {
	        String sql = "INSERT INTO refeicao (data, tipo, idusuario) "
	                   + "VALUES (?, ?, ?)";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setDate(1, Date.valueOf(refeicao.getData()));
	            stmt.setString(2, refeicao.getTipo().toString());
	            stmt.setLong(3, refeicao.getUsuario().getIdUsuario());
	            stmt.executeUpdate();
	        }
	    }
	    
	    public void atualizarRefeicao(Refeicao refeicao) throws SQLException {
	        String sql = "UPDATE refeicao SET data = ?, tipo = ?, idusuario = ? "
	                   + "WHERE idrefeicao = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setDate(1, Date.valueOf(refeicao.getData()));
	            stmt.setString(2, refeicao.getTipo().toString());
	            stmt.setLong(3, refeicao.getUsuario().getIdUsuario());
	            stmt.setLong(4, refeicao.getIdRefeicao());
	            stmt.executeUpdate();
	        }
	    }
	    
	    public void deletarRefeicao(long id) throws SQLException {
	        String sql = "DELETE FROM refeicao WHERE idrefeicao = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setLong(1, id);
	            stmt.executeUpdate();
	        }
	    }
	    
	    public void deletarPorUsuario(long idUsuario) throws SQLException {
	        String sql = "DELETE FROM refeicao WHERE idusuario = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setLong(1, idUsuario);
	            stmt.executeUpdate();
	        }
	    }
	    
	    public List<Refeicao> listarPorUsuario(long idUsuario) throws SQLException {
	        String sql = "SELECT idrefeicao, data, tipo, idusuario FROM refeicao "
	                   + "WHERE idusuario = ? ORDER BY data DESC";

	        List<Refeicao> lista = new ArrayList<Refeicao>();

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setLong(1, idUsuario);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Refeicao refeicaoRS = new Refeicao();
	                    refeicaoRS.setIdRefeicao(rs.getInt("idrefeicao"));
	                    refeicaoRS.setData(rs.getDate("data").toLocalDate());
	                    refeicaoRS.setTipo(TipoRefeicao.valueOf(rs.getString("tipo")));
	                    	                                    
                        lista.add(refeicaoRS);
	                }
	            }
	        }
	        return lista;
	    }
	    
}
