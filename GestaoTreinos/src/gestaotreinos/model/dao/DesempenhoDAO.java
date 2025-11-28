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
    public void inserirDesempenho(Desempenho oDesempenho, long idUsuario) throws SQLException {
        String sSql = "INSERT INTO desempenho (mediasono, mediacalorias, mediatreino, indicedesempenho, idusuario) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setDouble(1, oDesempenho.getMediaSono());
            stmt.setDouble(2, oDesempenho.getMediaCalorias());
            stmt.setDouble(3, oDesempenho.getMediaTreino());
            stmt.setDouble(4, oDesempenho.getIndiceDesempenho());
            stmt.setLong(5, idUsuario);
            stmt.executeUpdate();
        }
    }
    /* 
     * atualizar na tabela desempenho
     */
    public void atualizarDesempenho(Desempenho oDesempenho) throws SQLException {
        String sSql = "UPDATE desempenho SET mediasono = ?, mediacalorias = ?, mediatreino = ?, indicedesempenho = ? "
                   + "WHERE iddesempenho = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setDouble(1, oDesempenho.getMediaSono());
            stmt.setDouble(2, oDesempenho.getMediaCalorias());
            stmt.setDouble(3, oDesempenho.getMediaTreino());
            stmt.setDouble(4, oDesempenho.getIndiceDesempenho());
            stmt.setInt(5, oDesempenho.getIdDesempenho());
            stmt.executeUpdate();
        }
    }
    /*
     * deletar na tabela desempenho
     */
    public void deletarDesempenho(int id) throws SQLException {
        String sSql = "DELETE FROM desempenho WHERE iddesempenho = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    /* 
     * buscar pelo id na tabela desempenho     
     */
    public Desempenho buscarPorIddesempenho(int id) throws SQLException {
        String sSql = "SELECT iddesempenho, mediasono, mediacalorias, mediatreino, indicedesempenho, idusuario "
                   + "FROM desempenho WHERE iddesempenho = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
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
    public List<Desempenho> listarDesempenhoPorUsuario(long idUsuario) throws SQLException {
        String sSql = "SELECT iddesempenho, mediasono, mediacalorias, mediatreino, indicedesempenho, idusuario "
                   + "FROM desempenho WHERE idusuario = ? ORDER BY iddesempenho";

        List<Desempenho> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setLong(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
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
        }
        return lista;
    }
}
