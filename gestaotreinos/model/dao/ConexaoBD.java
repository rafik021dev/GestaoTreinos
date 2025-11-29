    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaotreinos.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoBD {

	public Connection connection(String bdname, String user, String pass) {
		Connection conn = null;
			try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdname,user,pass);
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

	public static Connection conectaBD() {
		Connection conn = null;

		try {
			String url = "jdbc:postgresql://localhost:5432/GestaoTreinos?user=postgres&password=07171826";
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ConexaoDAO" + e.getMessage());
		}
		return conn;
	}

}