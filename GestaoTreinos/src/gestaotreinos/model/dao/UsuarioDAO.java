package gestaotreinos.model.dao;
import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

	private Connection conn;
	
	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(Usuario usuario)throws SQLException{
		/*String sql = "INSERT INTO usuario "
				+ "(nome, sexo, idade, peso, altura, metapeso, email, senha)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1,usuario.getNome());
			statement.setString(2,String.valueOf(usuario.getSexo()));
			statement.setInt(3,usuario.getIdade());
			statement.setDouble(4,usuario.getPeso());
			statement.setDouble(5,usuario.getAltura());						
			statement.setDouble(6, usuario.getMetaPeso());
			statement.setNull(6, java.sql.Types.DECIMAL);			
			statement.setString(7, usuario.getEmail());
			statement.setString(8, usuario.getSenha());
			
			statement.executeUpdate();
		}*/
	
		String sql = "INSERT INTO usuario "
				+ "(nome, sexo, idade, peso, altura, metapeso, email, senha)"
				+ "VALUES (" 
				+ usuario.getNome()	+" , "
				+ usuario.getSexo() +" , "
				+ usuario.getIdade() +" , "				
				+ usuario.getPeso() +" , "
				+ usuario.getAltura() +" , "
				+ usuario.getMetaPeso() +" , "
				+ usuario.getEmail() +" , "
				+ usuario.getSenha() + ")";

		    try (Statement st = conn.createStatement()) {
		        st.executeUpdate(sql);
	
		    }
	}
}
