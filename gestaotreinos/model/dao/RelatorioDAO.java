/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.dao;

import gestaotreinos.enums.TipoRelatorio;
import gestaotreinos.model.entity.Desempenho;
import gestaotreinos.model.entity.Relatorio;
import gestaotreinos.model.entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class RelatorioDAO {

	    private Connection conn;

	    public RelatorioDAO(Connection conn) {
	        this.conn = conn;
	    }
	    /*
	     * inserir na tabela relatorio
	     */
	    public boolean inserirRelatorio(Relatorio relatorio) {
	        String sSql = "INSERT INTO relatorio (tipo, idusuario, iddesempenho) "
	                   + "VALUES (?, ?, ?)";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setString(1, relatorio.getTipo().name());
	            ps.setInt(2, relatorio.getUsuario().getIdUsuario());
	                ps.setInt(3, relatorio.getDesempenho().getIdDesempenho());	            	                
	            	            
	                ps.executeUpdate();
		            return true;
		            
		        } catch (SQLException e) {
		            e.printStackTrace();
		            return false;
	        }
	    }
	    /*
	     * atualizar na tabela relatorio
	     */
	    public boolean atualizarRelatorio(Relatorio relatorio) {
	        String sSql = "UPDATE relatorio SET tipo = ?, idusuario = ?, iddesempenho = ? "
	                   + "WHERE idrelatorio = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setString(1, relatorio.getTipo().name());
	            ps.setInt(2, relatorio.getUsuario().getIdUsuario());
	            ps.setInt(3, relatorio.getDesempenho().getIdDesempenho());
	            ps.setInt(4, relatorio.getIdRelatorio());
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * deletar na tabela relatorio
	     */
	    public boolean deletarRelatorio(int id) {
	        String sSql = "DELETE FROM relatorio WHERE idrelatorio = ?";

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
	     * deletar relatorio por idusuario
	     */
	    public boolean deletarRelatorioPorUsuario(int idUsuario) {
	        String sSql = "DELETE FROM relatorio WHERE idusuario = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setInt(1, idUsuario);
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * listar relatorio por usuario
	     */
	    public List<Relatorio> listarRelatorioPorUsuario(int idUsuario) throws SQLException {
	        String sSql = "SELECT idrelatorio, tipo, idusuario, iddesempenho FROM relatorio WHERE idusuario =?";
	        
	        List<Relatorio> lista = new ArrayList<>();
	        
	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setInt(1, idUsuario);
	            try (ResultSet rs = ps.executeQuery()) {
	                while(rs.next()) {
	                    Relatorio relatorioRS = new Relatorio();
	                    relatorioRS.setIdRelatorio(rs.getInt("idrelatorio"));
	                    
	                    relatorioRS.setTipo(TipoRelatorio.valueOf(rs.getString("tipo"))); 
	                    	                    
	                    Usuario usuario = new Usuario();
	                    usuario.setIdUsuario(rs.getInt("idusuario"));
	                    relatorioRS.setUsuario(usuario);
	                    
	                    Desempenho desempenho = new Desempenho();
	                    desempenho.setIdDesempenho(rs.getInt("iddesempenho"));
	                    relatorioRS.setDesempenho(desempenho);
	                    
	                    lista.add(relatorioRS);
	                }
	            }
	            return lista;
	        }catch(Exception e) {
	        	e.printStackTrace();
	    		return null;
	        }
	    }	    
}


