package gestaotreinos.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import gestaotreinos.model.entity.Exercicio;

public class ExercicioDAO {

	    private Connection conn;

	    public ExercicioDAO(Connection conn) {
	        this.conn = conn;   
	    }
	    
	    public void inserirExercicio(Exercicio oExercicio, long idTreino) throws SQLException {
	        String sSql = "INSERT INTO exercicio (nome, carga, series, repeticoes, idtreino) "
	                   + "VALUES (?, ?, ?, ?, ?)";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setString(1, oExercicio.getNome());
	            stmt.setInt(2, oExercicio.getCarga());
	            stmt.setInt(3, oExercicio.getSeries()); 
	            stmt.setInt(4, oExercicio.getRepeticoes());
	            stmt.setLong(5, idTreino);
	            stmt.executeUpdate();
	        }
	    }
	    
	    public void atualizarExercicio(Exercicio oExercicio) throws SQLException {
	        String sSql = "UPDATE exercicio SET nome = ?, carga = ? , series = ?, repeticoes = ? "
	                   + "WHERE idexercicio = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setString(1, oExercicio.getNome());
	            stmt.setInt(2, oExercicio.getCarga());
	            stmt.setInt(3, oExercicio.getSeries());
	            stmt.setInt(4, oExercicio.getRepeticoes());
	            stmt.setLong(5, oExercicio.getIdExercicio());
	            stmt.executeUpdate();
	        }
	    }
	    
	    public void deletarExercicio(long id) throws SQLException {
	        String sSql = "DELETE FROM exercicio WHERE idexercicio = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, id);
	            stmt.executeUpdate();
	        }
	    }
	    
	    public void deletarPorTreino(long idTreino) throws SQLException {
	        String sSql = "DELETE FROM exercicio WHERE idtreino = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, idTreino);
	            stmt.executeUpdate();
	        }
	    }
	    
	    public Exercicio buscarPorIdexercicio(long id) throws SQLException {
	        String sSql = "SELECT idexercicio, nome, carga, series, repeticoes FROM exercicio WHERE idexercicio = ?";

	        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
	            stmt.setLong(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    Exercicio oExercicio = new Exercicio();
	                    oExercicio.setIdExercicio(rs.getInt("idexercicio"));
	                    oExercicio.setNome(rs.getString("nome"));
	                    oExercicio.setCarga(rs.getInt("carga"));
	                    oExercicio.setSeries(rs.getInt("series"));
	                    oExercicio.setRepeticoes(rs.getInt("repeticoes"));
	                    
	                   return oExercicio;
	                }
	            }
	        }
	        return null;
	    }
	
	    public List<Exercicio> listarExercicio() throws SQLException {
	        String sSql = "SELECT idexercicio, nome, carga, series, repeticoes FROM exercicio ORDER BY nome";
	        
	        List<Exercicio> lista = new ArrayList<>();

	        try (PreparedStatement stmt = conn.prepareStatement(sSql);
	        ResultSet rs = stmt.executeQuery()) {
	            while(rs.next()) {
	                Exercicio exercicioRS = new Exercicio();
	                exercicioRS.setIdExercicio(rs.getInt("idexercicio"));
	                exercicioRS.setNome(rs.getString("nome"));
	                exercicioRS.setCarga(rs.getInt("carga"));
	                exercicioRS.setSeries(rs.getInt("series"));
	                exercicioRS.setRepeticoes(rs.getInt("repeticoes"));
	                
	                lista.add(exercicioRS);
	            }
	        }
	        return lista;
	    }
}
