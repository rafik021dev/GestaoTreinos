/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.dao;


import gestaotreinos.model.entity.Desempenho;
import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import gestaotreinos.enums.TipoRelatorio;

public class DesempenhoDAO {

    private Connection conn;

    public DesempenhoDAO(Connection conn) {
        this.conn = conn;
    }
    /* 
     * inserir na tabela desempenho
     */
    public boolean inserirDesempenho(Desempenho oDesempenho) {
        String sSql = "INSERT INTO desempenho (mediasono, mediacalorias, mediatreinos, indicedesempenho, idusuario, data, tipo, textoresumo) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setDouble(1, oDesempenho.getMediaSono());
            ps.setDouble(2, oDesempenho.getMediaCalorias());
            ps.setDouble(3, oDesempenho.getMediaTreino());
            ps.setDouble(4, oDesempenho.getIndiceDesempenho());
            ps.setInt(5, oDesempenho.getUsuario().getIdUsuario());
            ps.setDate(6, new java.sql.Date(oDesempenho.getDataGeracao().getTime()));
            ps.setString(7, oDesempenho.getTipo().name());
            ps.setString(8, oDesempenho.getTextoResumo());
            
            ps.executeUpdate();
            
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /* 
     * atualizar na tabela desempenho
     */
    public boolean atualizarDesempenho(Desempenho oDesempenho) {
        String sSql = "UPDATE desempenho SET mediasono = ?, mediacalorias = ?, mediatreino = ?, indicedesempenho = ? "
                   + "WHERE iddesempenho = ?";

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setDouble(1, oDesempenho.getMediaSono());
            ps.setDouble(2, oDesempenho.getMediaCalorias());
            ps.setDouble(3, oDesempenho.getMediaTreino());
            ps.setDouble(4, oDesempenho.getIndiceDesempenho());
            ps.setInt(5, oDesempenho.getIdDesempenho());
            ps.executeUpdate();
            
            return true;
        }catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
        
    }
    /*
     * deletar na tabela desempenho
     */
    public boolean deletarDesempenho(int id) {
        String sSql = "DELETE FROM desempenho WHERE iddesempenho = ?";

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            
            return true;
        }catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }
    /* 
     * buscar pelo id na tabela desempenho     
     */
    public Desempenho buscarPorIddesempenho(int id) throws SQLException {
        String sSql = "SELECT iddesempenho, mediasono, mediacalorias, mediatreino, indicedesempenho, idusuario "
                   + "FROM desempenho WHERE iddesempenho = ?";

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Desempenho desempenhoRS = new Desempenho();
                    desempenhoRS.setIdDesempenho(rs.getInt("iddesempenho"));
                    desempenhoRS.setMediaSono(rs.getDouble("mediasono"));
                    desempenhoRS.setMediaCalorias(rs.getDouble("mediacalorias"));
                    desempenhoRS.setMediaTreino(rs.getDouble("mediatreino"));
                    desempenhoRS.setIndiceDesempenho(rs.getDouble("indicedesempenho"));
                    
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idusuario"));
                    desempenhoRS.setUsuario(usuario);
                    
                    return desempenhoRS;
                }
            }
        }
        return null;
    }
    /* 
     * listar desempenho por usuário
     */
    public List<Desempenho> listarDesempenhoPorUsuario(int idUsuario) throws SQLException {
        String sSql = "SELECT iddesempenho, mediasono, mediacalorias, mediatreinos, indicedesempenho, idusuario, data, tipo, textoresumo "
               + "FROM desempenho WHERE idusuario = ? ORDER BY data DESC"; 

        List<Desempenho> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Desempenho desempenhoRS = new Desempenho();
                    desempenhoRS.setIdDesempenho(rs.getInt("iddesempenho"));
                    desempenhoRS.setMediaSono(rs.getDouble("mediasono"));
                    desempenhoRS.setMediaCalorias(rs.getDouble("mediacalorias"));
                    desempenhoRS.setMediaTreino(rs.getDouble("mediatreinos"));
                    desempenhoRS.setIndiceDesempenho(rs.getDouble("indicedesempenho"));         
                    desempenhoRS.setDataGeracao(rs.getDate("data"));
                    desempenhoRS.setTipo(TipoRelatorio.valueOf(rs.getString("tipo")));
                    desempenhoRS.setTextoResumo(rs.getString("textoresumo"));
                    
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idusuario"));
                    desempenhoRS.setUsuario(usuario);
                    
                    lista.add(desempenhoRS);
                }
            }
            return lista;
        }catch(Exception e) {
        	e.printStackTrace();
    		return null;        
        }
    }
}
