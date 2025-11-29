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

import gestaotreinos.model.entity.Alimento;

public class AlimentoDAO {

	    private Connection conn;

	    public AlimentoDAO(Connection conn) {
	        this.conn = conn;
	    }
	    /*
	     * inserir na tabela alimento
	     */
	    public boolean inserirAlimento(Alimento oAlimento, int idRefeicao){
	        String sSql = "INSERT INTO alimento (nome, quantidade, calorias, idrefeicao) "
	                   + "VALUES (?, ?, ?, ?)";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setString(1, oAlimento.getNomeAlimento());
	            ps.setDouble(2, oAlimento.getQuantidade());
	            ps.setInt(3, oAlimento.getCalorias());
	            ps.setInt(4, idRefeicao);
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * atualizar na tabela alimento
	     */
	    public boolean atualizarAlimento(Alimento alimento) {
	        String sSql = "UPDATE alimento SET nome = ?, quantidade = ?, calorias = ? "
	                   + "WHERE idalimento = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setString(1, alimento.getNomeAlimento());
	            ps.setDouble(2, alimento.getQuantidade());
	            ps.setInt(3, alimento.getCalorias());
	            ps.setInt(4, alimento.getIdAlimento());
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * deletar na tabela alimento
	     */
	    public boolean deletarAlimento(int id) {
	        String sSql = "DELETE FROM alimento WHERE idalimento = ?";

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
	     * deletar alimentos por refeição
	     */
	    public boolean deletarPorRefeicao(int idRefeicao) {
	        String sSql = "DELETE FROM alimento WHERE idrefeicao = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setInt(1, idRefeicao);
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }	        
	    }
	    /*
	     * listar alimentos por refeição
	     */
	    public List<Alimento> listaPorRefeicao(int idRefeicao){
	        String sSql = "SELECT idalimento, nome, quantidade, calorias, idrefeicao FROM alimento "
	                   + "WHERE idrefeicao = ? ORDER BY nome ASC";

	        List<Alimento> lista = new ArrayList<>();

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setInt(1, idRefeicao);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                   Alimento AlimentoRS = new Alimento();
	                   AlimentoRS.setIdAlimento(rs.getInt("idalimento"));
	                   AlimentoRS.setNomeAlimento(rs.getString("nome"));
	                   AlimentoRS.setQuantidade(rs.getDouble("quantidade"));
	                   AlimentoRS.setCalorias(rs.getInt("calorias"));
	                  	                  
	                   lista.add(AlimentoRS);
	                }
	            }
	            return lista;
	        }catch(Exception e) {
	        	e.printStackTrace();
	        	return null;
	        }	        
	    }
	    	    
}	    
