package gestaotreinos.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
	public void createtable(Connection conn, String tableNome) {
	    Statement statement = null;
	    try {
	        String query = "CREATE TABLE usuario ("
	                     + "id SERIAL PRIMARY KEY, "
	                     + "nome VARCHAR(100) NOT NULL, "
	                     + "sexo CHAR(1) NOT NULL, "
	                     + "idade INT NOT NULL, "
	                     + "peso NUMERIC(5,2) NOT NULL, "
	                     + "altura NUMERIC(4,2) NOT NULL"
	                     + ");";

	        statement = conn.createStatement();
	        statement.executeUpdate(query);
	        System.out.println("Tabela criada.");

	    } catch (Exception e) {
	        System.out.println("Erro ao criar tabela: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) statement.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
}
