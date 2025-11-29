package gestaotreinos.model.BO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import gestaotreinos.model.dao.UsuarioDAO;
import gestaotreinos.model.entity.Usuario;

public class UsuarioBO {
	
	private Connection conn;
	
	public UsuarioBO(Connection conn){
		this.conn = conn;
	}	
	
	public boolean atualizarUsuario(Usuario usuario){
		UsuarioDAO dao = new UsuarioDAO(conn);
		if(usuario.getIdUsuario() <= 0) {
			return false;
		}else {	
		return dao.atualizar(usuario);	
		}
	}
	
	public boolean DeletarUsuario(int idUsuario){
		UsuarioDAO dao = new UsuarioDAO(conn);
			if(idUsuario <= 0) {
				return false;
			}else {
				return dao.deletar(idUsuario);
			}
	}	
	
	public Usuario login(String email, String senha) throws SQLException {
		UsuarioDAO dao = new UsuarioDAO(conn);
		
		List<Usuario> listaUsuarios = dao.listarUsuarios();
		if(listaUsuarios != null) {
			for(Usuario u : listaUsuarios) {
				if(u.getEmail().equals(email) && u.getSenha().equals(senha)) {
					return u;
				}
			}
		}
		return null;		
	}
	
	public Usuario buscarUsuario(int id) throws SQLException {
		UsuarioDAO dao = new UsuarioDAO(conn);
		Usuario u = dao.buscarPorId(id);
		
		if(u == null) {
			throw new IllegalArgumentException("usuario nao encontrado");
		}
		return u;
	}
}
