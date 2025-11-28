package gestaotreinos.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import gestaotreinos.enums.QualidadeSono;
import gestaotreinos.model.entity.Sono;

public class SonoDAO {
    private Connection conn;

    public SonoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserirSono(Sono sono) throws SQLException {
        String sSql = "INSERT INTO sono (data, horasdormidas, qualidade, idusuario) "
                   + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setDate(1, Date.valueOf(sono.getData()));
            stmt.setDouble(2, sono.getHorasDormidas());
            stmt.setString(3, sono.getQualidade().toString());
            stmt.setLong(4, sono.getUsuario().getIdUsuario());
            stmt.executeUpdate();
        }
    }

    public void atualizarSono(Sono sono) throws SQLException {
        String sSql = "UPDATE sono SET data = ?, horasdormidas = ?, qualidade = ?, idusuario = ? "
                   + "WHERE idsono = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setDate(1, Date.valueOf(sono.getData()));
            stmt.setDouble(2, sono.getHorasDormidas());
            stmt.setString(3, sono.getQualidade().toString());
            stmt.setLong(4, sono.getUsuario().getIdUsuario());
            stmt.setLong(5, sono.getIdSono());
            stmt.executeUpdate();
        }
    }

    public void deletarSono(long id) throws SQLException {
        String sSql = "DELETE FROM sono WHERE idsono = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Sono> listarPorUsuario(long idUsuario) throws SQLException {
        String sSql = "SELECT idsono, data, horasdormidas, qualidade, idusuario FROM sono "
                   + "WHERE idusuario = ? ORDER BY data DESC";

        List<Sono> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sSql)) {
            stmt.setLong(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sono oSono = new Sono();
                    oSono.setIdSono(rs.getInt("idsono"));
                    oSono.setData(rs.getDate("data").toLocalDate());
                    oSono.setHorasDormidas(rs.getDouble("horasdormidas"));
                    oSono.setQualidade(QualidadeSono.valueOf(rs.getString("qualidade")));

                    lista.add(oSono);
                }
            }
        }
        return lista;
    }
}
