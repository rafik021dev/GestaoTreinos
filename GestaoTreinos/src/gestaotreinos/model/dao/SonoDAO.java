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
import gestaotreinos.model.entity.Usuario;

public class SonoDAO {
    
    private final Connection conn;

    public SonoDAO(Connection conn) {
        this.conn = conn;
    }

    /*
     * inserir na tabela sono   
     */
    public boolean inserirSono(Sono sono) {
        String sSql = "INSERT INTO sono (data, horasdormidas, qualidade, idusuario) VALUES (?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setDate(1, Date.valueOf(sono.getData()));
            ps.setDouble(2, sono.getHorasDormidas());        
            ps.setString(3, sono.getQualidade().name()); 
            ps.setInt(4, sono.getUsuario().getIdUsuario()); 
            
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * atualizar na tabela sono   
     */
    public boolean atualizarSono(Sono sono) {
        String sSql = "UPDATE sono SET data =?, horasdormidas =?, qualidade =?, idusuario =? WHERE idsono =?";

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setDate(1, Date.valueOf(sono.getData()));
            ps.setDouble(2, sono.getHorasDormidas());
            ps.setString(3, sono.getQualidade().name()); 
            ps.setInt(4, sono.getUsuario().getIdUsuario());
            ps.setInt(5, sono.getIdSono()); 
            
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*
     * deletar na tabela sono
     */
    public boolean deletarSono(int idSono) {
        String sSql = "DELETE FROM sono WHERE idsono =?";

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setInt(1, idSono);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {           
            e.printStackTrace();
            return false;
        }
    }   

    /*
     * listar sono pelo idusuario
     */
    public List<Sono> listarSonoPorUsuario(int idUsuario) {
        String sSql = "SELECT idsono, data, horasdormidas, qualidade, idusuario FROM sono WHERE idusuario =? ORDER BY data DESC";
        List<Sono> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
            ps.setInt(1, idUsuario);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sono oSono = new Sono();
                    oSono.setIdSono(rs.getInt("idsono"));
                    oSono.setData(rs.getDate("data").toLocalDate());
                    oSono.setHorasDormidas(rs.getDouble("horasdormidas"));                    
                    oSono.setQualidade(QualidadeSono.valueOf(rs.getString("qualidade")));

                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idusuario"));
                    oSono.setUsuario(usuario);

                    lista.add(oSono);
                }
            }
            return lista;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}