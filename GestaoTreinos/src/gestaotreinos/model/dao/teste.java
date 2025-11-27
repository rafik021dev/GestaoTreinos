package gestaotreinos.model.dao;
import java.sql.Connection;
import gestaotreinos.model.dao.ConexaoBD;
import gestaotreinos.model.dao.UsuarioDAO;
import gestaotreinos.model.entity.Usuario;

public class teste {

	public static void main(String[] args) {
		try {
            ConexaoBD conexaoBD = new ConexaoBD();
            Connection conn = conexaoBD.connection("GestaoTreinos", "postgres", "07171826");

            Usuario u = new Usuario();
            u.setNome("jairton3");
            u.setSexo('M');
            u.setIdade(18);
            u.setPeso(80);
            u.setAltura(1.80);
            u.setMetaPeso(78.0);
            u.setEmail("jairto3gmail.com");
            u.setSenha("1234567");

            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            usuarioDAO.insert(u);

            System.out.println("Usuario inserido.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}