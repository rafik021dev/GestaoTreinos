/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import gestaotreinos.enums.TipoAlerta;
import gestaotreinos.model.entity.Alerta;
import gestaotreinos.model.entity.Usuario;
import java.time.LocalDate;

public class AlertaDAO {

	 private Connection conn;

	    public AlertaDAO(Connection conn) {
                this.conn = conn;
	    }
	    /* 
	     * inserir na tabela alerta
	     */
	    public boolean inserirAlerta(Alerta oAlerta){
	        String sSql = "INSERT INTO alerta (data, tipo, mensagem, idusuario) "
	                   + "VALUES (?, ?, ?, ?)";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setDate(1, Date.valueOf(oAlerta.getData()));
	            ps.setString(2, oAlerta.getTipo().name());
	            ps.setString(3, oAlerta.getMensagem());
	            ps.setInt(4, oAlerta.getUsuario().getIdUsuario());
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	            
	        }
	    }
	    /* 
	     * atualizar na tabela alerta
	     */
	    public boolean atualizarAlerta(Alerta oAlerta) {
	        String sSql = "UPDATE alerta SET data = ?, tipo = ?, mensagem = ?, idusuario = ? "
	                   + "WHERE idalerta = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setDate(1, Date.valueOf(oAlerta.getData()));
	            ps.setString(2, oAlerta.getTipo().name());
	            ps.setString(3, oAlerta.getMensagem());
	            ps.setInt(4, oAlerta.getUsuario().getIdUsuario());
	            ps.setInt(5, oAlerta.getIdAlerta());
	            ps.executeUpdate();
	            return true;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	            
	        }
	    }
	    /*
	     * deletar na tabela alerta
	     */
	    public boolean deletarAlerta(int id) {
	        String sSql = "DELETE FROM alerta WHERE idalerta = ?";

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
	     * buscar pelo id na tabela alerta
	     */
	    public Alerta buscarPorIdalerta(int id) throws SQLException {
	        String sSql = "SELECT idalerta, data, tipo, mensagem, idusuario FROM alerta WHERE idalerta = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sSql)) {
	            ps.setInt(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                	Alerta alertaRS = new Alerta();
	                	alertaRS.setIdAlerta(rs.getInt("idalerta"));
	                	alertaRS.setData(rs.getDate("data").toLocalDate());
	                	alertaRS.setTipo(TipoAlerta.valueOf(rs.getString("tipo")));
	                	alertaRS.setMensagem(rs.getString("mensagem"));
	                    
	                    Usuario usuario = new Usuario();
	                    usuario.setIdUsuario(rs.getInt("idusuario"));
	                    alertaRS.setUsuario(usuario);
	                    
	                    return alertaRS;
	                }
	            }
	        }
	        return null;
	    }
            public java.util.List<Alerta> listarPorUsuario(int idUsuario) {
                String sSql = "SELECT idalerta, data, tipo, mensagem, idusuario FROM alerta "
                   + "WHERE idusuario = ? ORDER BY data DESC";
        
                    java.util.List<Alerta> lista = new java.util.ArrayList<>();

                try (PreparedStatement ps = conn.prepareStatement(sSql)) {
                ps.setInt(1, idUsuario);
            
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            Alerta alertaRS = new Alerta();
                            alertaRS.setIdAlerta(rs.getInt("idalerta"));
                            alertaRS.setData(rs.getDate("data").toLocalDate());
                            alertaRS.setTipo(gestaotreinos.enums.TipoAlerta.valueOf(rs.getString("tipo")));
                            alertaRS.setMensagem(rs.getString("mensagem"));

                            Usuario usuario = new Usuario();
                            usuario.setIdUsuario(rs.getInt("idusuario"));
                            alertaRS.setUsuario(usuario);

                            lista.add(alertaRS);
                }
            }
            return lista;           
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
            /*
             *
            */
            public boolean ExisteAlerta(int idUsuario, TipoAlerta tipo) throws SQLException {
                String sql = "SELECT idalerta FROM alerta WHERE idusuario = ? AND tipo = ? AND data = ?";
    
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                   ps.setInt(1, idUsuario);
                    ps.setString(2, tipo.name());
                    ps.setDate(3, Date.valueOf(LocalDate.now()));
        
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return true;
                    }
                }
                }catch (SQLException e) {
                e.printStackTrace();
                }
                return false;
                }
            }          
