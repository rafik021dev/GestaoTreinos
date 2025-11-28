package gestaotreinos.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import gestaotreinos.enums.TipoAlerta;
import gestaotreinos.model.entity.Alerta;
import gestaotreinos.model.entity.Usuario;

public class AlertaDAO {

	 private Connection conn;

	    public AlertaDAO(Connection conn) {
	        this.conn = conn;
	    }
	    
	    public void inserirAlerta(Alerta alerta) throws SQLException {
	        String sSql = "INSERT INTO alerta (data, tipo, mensagem, idusuario) "
	                   + "VALUES (?, ?, ?, ?)";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setDate(1, Date.valueOf(alerta.getData()));
	            stmt.setString(2, alerta.getTipo().toString());
	            stmt.setString(3, alerta.getMensagem());
	            stmt.setLong(4, alerta.getUsuario().getIdUsuario());
	            stmt.executeUpdate();
	        }
	    }
	    
	    public void atualizarAlerta(Alerta alerta) throws SQLException {
	        String sSql = "UPDATE alerta SET data = ?, tipo = ?, mensagem = ?, idusuario = ? "
	                   + "WHERE idalerta = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setDate(1, Date.valueOf(alerta.getData()));
	            stmt.setString(2, alerta.getTipo().toString());
	            stmt.setString(3, alerta.getMensagem());
	            stmt.setLong(4, alerta.getUsuario().getIdUsuario());
	            stmt.setLong(5, alerta.getIdAlerta());
	            stmt.executeUpdate();
	        }
	    }
	    public void deletarAlerta(long id) throws SQLException {
	        String sSql = "DELETE FROM alerta WHERE idalerta = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, id);
	            stmt.executeUpdate();
	        }
	    }
	    public Alerta buscarPorIdalerta(long id) throws SQLException {
	        String sSql = "SELECT idalerta, data, tipo, mensagem, idusuario FROM alerta WHERE idalerta = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                	Alerta alertaRS = new Alerta();
	                	alertaRS.setIdAlerta(rs.getInt("idalerta"));
	                	alertaRS.setData(rs.getDate("data").toLocalDate());
	                	alertaRS.setTipo(TipoAlerta.valueOf(rs.getString("tipo")));
	                	alertaRS.setMensagem(rs.getString("mensagem"));
	                    
	                    Usuario usuario = new Usuario();
	                    usuario.setIdUsuario(rs.getInt("idusuario"));
	                    alertaRS.setUsuario(usuario);
	                    
	                    return alertaRS;
	                }
	            }
	        }
	        return null;
	    }
}
