package gestaotreinos.model.dao;

import gestaotreinos.model.entity.Desempenho;
import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DesempenhoDAO {

    private Connection conn;

    public DesempenhoDAO(Connection conn) {
        this.conn = conn;
    }
    /* 
     * inserir na tabela desempenho
     */
    public boolean inserirDesempenho(Desempenho oDesempenho, int idUsuario) {
        String sSql = "INSERT INTO desempenho (mediasono, mediacalorias, mediatreino, indicedesempenho, idusuario) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setDouble(1, oDesempenho.getMediaSono());
            ps.setDouble(2, oDesempenho.getMediaCalorias());
            ps.setDouble(3, oDesempenho.getMediaTreino());
            ps.setDouble(4, oDesempenho.getIndiceDesempenho());
            ps.setInt(5, idUsuario);
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
        String sSql = "SELECT iddesempenho, mediasono, mediacalorias, mediatreino, indicedesempenho, idusuario "
                   + "FROM desempenho WHERE idusuario = ? ORDER BY iddesempenho";

        List<Desempenho> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Desempenho desempenhoRS = new Desempenho();
                    desempenhoRS.setIdDesempenho(rs.getInt("iddesempenho"));
                    desempenhoRS.setMediaSono(rs.getDouble("mediasono"));
                    desempenhoRS.setMediaCalorias(rs.getDouble("mediacalorias"));
                    desempenhoRS.setMediaTreino(rs.getDouble("mediatreino"));
                    desempenhoRS.setIndiceDesempenho(rs.getDouble("indicedesempenho"));
                    
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