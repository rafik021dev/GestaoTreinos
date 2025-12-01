/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.dao;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import gestaotreinos.enums.TipoRefeicao;
import gestaotreinos.model.entity.Refeicao;
import gestaotreinos.model.entity.Usuario;

public class RefeicaoDAO {
	
	    private Connection conn;

	    public RefeicaoDAO(Connection conn) {
	        this.conn = conn;
	    }
            
            
            /**
             * Método Responsável por Inserir a Refeição no Banco de Dados
             * @param refeicao
             * @return refeicao
             */
            public Refeicao inserir(Refeicao refeicao) {
                String sql = "INSERT INTO refeicao (data, tipo, idUsuario) "
                            + "VALUES (?, ?, ?) RETURNING idRefeicao";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setDate(1, new java.sql.Date(refeicao.getData().getTime()));
                ps.setString(2, refeicao.getTipo().name());
                ps.setInt(3, refeicao.getUsuario().getIdUsuario());

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    refeicao.setIdRefeicao(rs.getInt("idRefeicao"));
                }

                return refeicao;

                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao inserir refeição: " + e.getMessage());
                }
            }
            
            /**
             * Busca pela última Refeição 
             * @param id
             * @return refeicao | null 
             */
            public Refeicao buscarPorId(int id) {

                String sql = "SELECT * FROM refeicao WHERE idRefeicao = ?";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {

                        Refeicao refeicao = new Refeicao();
                        refeicao.setIdRefeicao(rs.getInt("idRefeicao"));
                        refeicao.setData(rs.getDate("data"));
                        refeicao.setTipo(TipoRefeicao.valueOf(rs.getString("tipo")));

                        return refeicao;
                    }

                    return null;

                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao buscar refeição: " + e.getMessage());
                }
                
            }

	    /*
	     * inserir na tabela refeicao
	     * @Deprecated
            */
	    public boolean inserirRefeicao(Refeicao refeicao) {
	        String sSql = "INSERT INTO refeicao (data, tipo, idusuario) "
	                   + "VALUES (?, ?, ?)";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setDate(1, new java.sql.Date(refeicao.getData().getTime()));
	            ps.setString(2, refeicao.getTipo().name());
	            ps.setInt(3, refeicao.getUsuario().getIdUsuario());
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * atualizar na tabela refeicao
	     */
	    public boolean atualizarRefeicao(Refeicao refeicao) {
	        String sSql = "UPDATE refeicao SET data = ?, tipo = ?, idusuario = ? "
	                   + "WHERE idrefeicao = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setDate(1, new java.sql.Date(refeicao.getData().getTime()));
	            ps.setString(2, refeicao.getTipo().name());
	            ps.setInt(3, refeicao.getUsuario().getIdUsuario());
	            ps.setInt(4, refeicao.getIdRefeicao());
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    /*
	     * deletar na tabela refeicao
	     */
	    public boolean deletarRefeicao(int id) {
	        String sSql = "DELETE FROM refeicao WHERE idrefeicao = ?";

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
	     * deletar refeicao por usuario
	     */
	    public boolean deletarRefeicaoPorUsuario(int idUsuario) {
	        String sSql = "DELETE FROM refeicao WHERE idusuario = ?";

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
	     * listar refeicao por usuario
	     */
	    public List<Refeicao> listarPorUsuario(int idUsuario) throws SQLException {
	        String sql = "SELECT idrefeicao, data, tipo, idusuario FROM refeicao WHERE idusuario =? ORDER BY data DESC";

	        List<Refeicao> lista = new ArrayList<>();

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, idUsuario);
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Refeicao refeicao = new Refeicao();
	                    refeicao.setIdRefeicao(rs.getInt("idrefeicao")); 
	                    refeicao.setData(rs.getDate("data"));
	                    refeicao.setTipo(TipoRefeicao.valueOf(rs.getString("tipo")));
	                    
	                    
	                    Usuario usuario = new Usuario();
	                    usuario.setIdUsuario(rs.getInt("idusuario")); 
	                    refeicao.setUsuario(usuario);
	                                        
	                    lista.add(refeicao);
	                }
	            }
	            return lista;
	        }catch(Exception e) {
	        	e.printStackTrace();
	    		return null;	    }
	}
            public int buscarUltimoId(int idUsuario) throws SQLException {
        String sSql = "SELECT MAX(idrefeicao) as id FROM refeicao WHERE idusuario = ?";
        
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