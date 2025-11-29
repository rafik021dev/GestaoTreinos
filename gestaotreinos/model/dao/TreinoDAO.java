/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gestaotreinos.enums.TipoTreino;
import gestaotreinos.model.entity.Exercicio;
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
	        /*
	         * lista os treino pelo idusuario
	         */
	        public List<Treino> listarTreinosPorUsuario(int idUsuario) throws SQLException {
	            String sSql = "SELECT idtreino, data, tipo, idusuario FROM treino WHERE idusuario = ? ORDER BY data DESC";
	            List<Treino> lista = new ArrayList<>();

	            ExercicioDAO exercicioDAO = new ExercicioDAO(conn);
	            
	            try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	                ps.setInt(1, idUsuario);
	                try (ResultSet rs = ps.executeQuery()) {
	                    while (rs.next()) {
	                        Treino treinoRS = new Treino();
	                        treinoRS.setIdTreino(rs.getInt("idtreino"));
	                        treinoRS.setData(rs.getDate("data").toLocalDate());
	                        treinoRS.setTipo(TipoTreino.valueOf(rs.getString("tipo")));
	                        List<Exercicio> exerciciosTreino = exercicioDAO.listarPorTreino(treinoRS.getIdTreino());
	                        treinoRS.setExercicios(exerciciosTreino);
	                        
	                        lista.add(treinoRS);
	                    }
	                }
	            }
	            return lista;
	        }
	        public int buscarUltimoId(int idUsuario) throws SQLException {
	           
	            String sSql = "SELECT MAX(idtreino) as id FROM treino WHERE idusuario = ?";
	            
	            try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	                ps.setInt(1, idUsuario);
	                
	                try (ResultSet rs = ps.executeQuery()) {
	                    if (rs.next()) {
	                        return rs.getInt("id");
	                    }
	                }
	            }
	            return 0;
	        }
  }

