/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
	    /*
	     * inseir na tabela exercicio
	     */
	    public boolean inserirExercicio(Exercicio oExercicio, int idTreino){
	        String sSql = "INSERT INTO exercicio (nome, carga, series, repeticoes, idtreino) "
	                   + "VALUES (?, ?, ?, ?, ?)";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setString(1, oExercicio.getNome());
	            ps.setInt(2, oExercicio.getCarga());
	            ps.setInt(3, oExercicio.getSeries()); 
	            ps.setInt(4, oExercicio.getRepeticoes());
	            ps.setInt(5, idTreino);
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * atualizar na tabela exercicio
	     */
	    public boolean atualizarExercicio(Exercicio oExercicio){
	        String sSql = "UPDATE exercicio SET nome = ?, carga = ? , series = ?, repeticoes = ? "
	                   + "WHERE idexercicio = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	        	ps.setString(1, oExercicio.getNome());
	        	ps.setInt(2, oExercicio.getCarga());
	        	ps.setInt(3, oExercicio.getSeries());
	        	ps.setInt(4, oExercicio.getRepeticoes());
	        	ps.setInt(5, oExercicio.getIdExercicio());
	        	ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /* 
	     * deletar na tabela exercicio
	     */
	    public boolean deletarExercicio(int id) {
	        String sSql = "DELETE FROM exercicio WHERE idexercicio = ?";

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
	     * deletar na tabela exercicio por treino
	     */
	    public boolean deletarPorTreino(int idTreino){
	        String sSql = "DELETE FROM exercicio WHERE idtreino = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	        	ps.setInt(1, idTreino);
	        	ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * buscar exercicio pelo idexercicio
	     */
	    public Exercicio buscarPorIdexercicio(int id) throws SQLException {
	        String sSql = "SELECT idexercicio, nome, carga, series, repeticoes FROM exercicio WHERE idexercicio = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	        	ps.setInt(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
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
	
	    /*
	     * listar ecercicio da tabela exercicio
	     */
	    public List<Exercicio> listarPorTreino(int idTreino) throws SQLException {
	        String sql = "SELECT idexercicio, nome, carga, series, repeticoes FROM exercicio WHERE idtreino = ? ORDER BY nome";
	        		
	        
	        List<Exercicio> lista = new ArrayList<>();

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, idTreino); 
	            try (ResultSet rs = ps.executeQuery()) {
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
	        }catch(Exception e) {
        	e.printStackTrace();
        	return null;	       
	        }
	    }
}
