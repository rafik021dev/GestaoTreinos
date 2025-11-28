package gestaotreinos.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConexaoBD {
	public Connection connection(String bdname, String user, String pass) {
		Connection conn=null;
			try {
				Class.forName("org.postgresql.Driver");
				conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdname,user,pass);
				if(conn != null) {
					System.out.println("Conexão estabelecida.");
				}else
					System.out.println("A conexão falhou.");
			
		}catch(Exception e) {
			System.out.println("Erro ao conectar: " + e.getMessage());
			e.printStackTrace();
		}
		return conn;	
	}	
}
