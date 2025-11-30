//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gestaotreinos.model.dao;

import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean inserir(Usuario oUsuario) {
        String sSql = "INSERT INTO usuario (nome, sexo, idade, peso, altura, metapeso, email, senha)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = this.conn.prepareStatement(sSql)) {
            ps.setString(1, oUsuario.getNome());
            ps.setString(2, String.valueOf(oUsuario.getSexo()));
            ps.setInt(3, oUsuario.getIdade());
            ps.setDouble(4, oUsuario.getPeso());
            ps.setDouble(5, oUsuario.getAltura());
            if (oUsuario.getMetaPeso() != null) {
                ps.setDouble(6, oUsuario.getMetaPeso());
            } else {
                ps.setNull(6, 3);
            }

            ps.setString(7, oUsuario.getEmail());
            ps.setString(8, oUsuario.getSenha());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Usuario oUsuario) {
        String sSql = "UPDATE usuario SETnome = ?, sexo = ?, idade = ?, peso = ?, altura = ?, metapeso = ?, email = ?, senha = ? WHERE idusuario = ?";

        try (PreparedStatement ps = this.conn.prepareStatement(sSql)) {
            ps.setString(1, oUsuario.getNome());
            ps.setString(2, String.valueOf(oUsuario.getSexo()));
            ps.setInt(3, oUsuario.getIdade());
            ps.setDouble(4, oUsuario.getPeso());
            ps.setDouble(5, oUsuario.getAltura());
            if (oUsuario.getMetaPeso() != null) {
                ps.setDouble(6, oUsuario.getMetaPeso());
            } else {
                ps.setNull(6, 3);
            }

            ps.setString(7, oUsuario.getEmail());
            ps.setString(8, oUsuario.getSenha());
            ps.setInt(9, oUsuario.getIdUsuario());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletar(int idUsuario) {
        String sSql = "DELETE FROM usuario WHERE idusuario = ?";

        try (PreparedStatement ps = this.conn.prepareStatement(sSql)) {
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario buscarPorId(int idUsuario) throws SQLException {
        String sSql = "SELECT idusuario, nome, sexo, idade, peso, altura, metapeso, email, senha FROM usuario WHERE idusuario = ?";

        Usuario usuarioRS;
        try (PreparedStatement ps = this.conn.prepareStatement(sSql)) {
            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuarioRS = new Usuario();
                    usuarioRS.setIdUsuario(rs.getInt("idusuario"));
                    usuarioRS.setNome(rs.getString("nome"));
                    usuarioRS.setSexo(rs.getString("sexo").charAt(0));
                    usuarioRS.setIdade(rs.getInt("idade"));
                    usuarioRS.setPeso(rs.getDouble("peso"));
                    usuarioRS.setAltura(rs.getDouble("altura"));
                    usuarioRS.setMetaPeso(rs.getDouble("metapeso"));
                    usuarioRS.setEmail(rs.getString("email"));
                    usuarioRS.setSenha(rs.getString("senha"));
                    Usuario var6 = usuarioRS;
                    return var6;
                }

                usuarioRS = null;
            }
        }

        return usuarioRS;
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        String sSql = "SELECT idusuario, nome, sexo, idade, peso, altura, metapeso, email, senha FROM usuario ORDER BY idusuario";
        List<Usuario> lista = new ArrayList();

        try (
            PreparedStatement ps = this.conn.prepareStatement(sSql);
            ResultSet rs = ps.executeQuery();
        ) {
            while(rs.next()) {
                Usuario usuarioRS = new Usuario();
                usuarioRS.setIdUsuario(rs.getInt("idusuario"));
                usuarioRS.setNome(rs.getString("nome"));
                usuarioRS.setSexo(rs.getString("sexo").charAt(0));
                usuarioRS.setIdade(rs.getInt("idade"));
                usuarioRS.setPeso(rs.getDouble("peso"));
                usuarioRS.setAltura(rs.getDouble("altura"));
                usuarioRS.setMetaPeso(rs.getDouble("metapeso"));
                usuarioRS.setEmail(rs.getString("email"));
                usuarioRS.setSenha(rs.getString("senha"));
                lista.add(usuarioRS);
            }
        }

        return lista;
    }
}
