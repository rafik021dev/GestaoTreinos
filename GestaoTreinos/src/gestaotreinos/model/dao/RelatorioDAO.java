package gestaotreinos.model.dao;
import gestaotreinos.enums.TipoRelatorio;
import gestaotreinos.model.entity.Relatorio;
import gestaotreinos.model.entity.Desempenho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioDAO {

	    private Connection conn;

	    public RelatorioDAO(Connection conn) {
	        this.conn = conn;
	    }
	    /*
	     * inserir na tabela relatorio
	     */
	    public void inserirRelatorio(Relatorio relatorio) throws SQLException {
	        String sSql = "INSERT INTO relatorio (tipo, idusuario, iddesempenho) "
	                   + "VALUES (?, ?, ?)";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setString(1, relatorio.getTipo().toString());
	            stmt.setLong(2, relatorio.getUsuario().getIdUsuario());
	                stmt.setInt(3, relatorio.getDesempenho().getIdDesempenho());	            	                
	            	            
	            stmt.executeUpdate();
	        }
	    }
	    /*
	     * atualizar na tabela relatorio
	     */
	    public void atualizarRelatorio(Relatorio relatorio) throws SQLException {
	        String sSql = "UPDATE relatorio SET tipo = ?, idusuario = ?, iddesempenho = ? "
	                   + "WHERE idrelatorio = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setString(1, relatorio.getTipo().toString());
	            stmt.setLong(2, relatorio.getUsuario().getIdUsuario());
	            stmt.setInt(3, relatorio.getDesempenho().getIdDesempenho());
	            stmt.setInt(4, relatorio.getIdRelatorio());
	            stmt.executeUpdate();
	        }
	    }
	    /*
	     * deletar na tabela relatorio
	     */
	    public void deletarRelatorio(int id) throws SQLException {
	        String sSql = "DELETE FROM relatorio WHERE idrelatorio = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setInt(1, id);
	            stmt.executeUpdate();
	        }
	    }
	    /*
	     * deletar relatorio por idusuario
	     */
	    public void deletarRelatorioPorUsuario(long idUsuario) throws SQLException {
	        String sSql = "DELETE FROM relatorio WHERE idusuario = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, idUsuario);
	            stmt.executeUpdate();
	        }
	    }
	    
}

