package gestaotreinos.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gestaotreinos.model.entity.Alimento;

public class AlimentoDAO {

	    private Connection conn;

	    public AlimentoDAO(Connection conn) {
	        this.conn = conn;
	    }
	    /*
	     * inserir na tabela alimento
	     */
	    public void inserirAlimento(Alimento oAlimento, long idRefeicao) throws SQLException {
	        String sSql = "INSERT INTO alimento (nome, quantidade, calorias, idrefeicao) "
	                   + "VALUES (?, ?, ?, ?)";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setString(1, oAlimento.getNomeAlimento());
	            stmt.setDouble(2, oAlimento.getQuantidade());
	            stmt.setInt(3, oAlimento.getCalorias());
	            stmt.setLong(4, idRefeicao);
	            stmt.executeUpdate();
	        }
	    }
	    /*
	     * atualizar na tabela alimento
	     */
	    public void atualizarAlimento(Alimento alimento) throws SQLException {
	        String sSql = "UPDATE alimento SET nome = ?, quantidade = ?, calorias = ? "
	                   + "WHERE idalimento = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setString(1, alimento.getNomeAlimento());
	            stmt.setDouble(2, alimento.getQuantidade());
	            stmt.setInt(3, alimento.getCalorias());
	            stmt.setLong(4, alimento.getIdAlimento());
	            stmt.executeUpdate();
	        }
	    }
	    /*
	     * deletar na tabela alimento
	     */
	    public void deletarAlimento(long id) throws SQLException {
	        String sSql = "DELETE FROM alimento WHERE idalimento = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, id);
	            stmt.executeUpdate();
	        }
	    }
	    /*
	     * deletar alimentos por refeição
	     */
	    public void deletarPorRefeicao(long idRefeicao) throws SQLException {
	        String sSql = "DELETE FROM alimento WHERE idrefeicao = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, idRefeicao);
	            stmt.executeUpdate();
	        }
	    }
	    /*
	     * listar alimentos por refeição
	     */
	    public List<Alimento> listarPorRefeicao(long idRefeicao) throws SQLException {
	        String sSql = "SELECT idalimento, nome, quantidade, calorias, idrefeicao FROM alimento "
	                   + "WHERE idrefeicao = ? ORDER BY nome ASC";

	        List<Alimento> lista = new ArrayList<>();

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, idRefeicao);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                   Alimento AlimentoRS = new Alimento();
	                   AlimentoRS.setIdAlimento(rs.getInt("idalimento"));
	                   AlimentoRS.setNomeAlimento(rs.getString("nome"));
	                   AlimentoRS.setQuantidade(rs.getDouble("quantidade"));
	                   AlimentoRS.setCalorias(rs.getInt("calorias"));
	                   
	                   lista.add(AlimentoRS);
	                }
	            }
	        }
	        return lista;
	    }
	    	    
}	    