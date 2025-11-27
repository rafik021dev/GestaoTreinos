package gestaotreinos.model.dao;
import gestaotreinos.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class UsuarioDAO {

	private Connection conn;
	
	public UsuarioDAO(Connection conn) {
        this.conn = conn;
	}

    /**
     * Método responsável por Cadastrar o Usuario no Sistema
     * @param oUsuario
     * @throws SQLException
     */
	public void insertUsuario(Usuario oUsuario) throws SQLException{
		String sSql = "INSERT INTO usuario "
                + "(nome, sexo, idade, peso, altura, metapeso, email, senha)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(sSql)){
			statement.setString(1, oUsuario.getNome());
			statement.setString(2,String.valueOf(oUsuario.getSexo()));
			statement.setInt(3, oUsuario.getIdade());
			statement.setDouble(4, oUsuario.getPeso());
			statement.setDouble(5, oUsuario.getAltura());
			statement.setDouble(6, oUsuario.getMetaPeso());
			statement.setNull(6, java.sql.Types.DECIMAL);
			statement.setString(7, oUsuario.getEmail());
			statement.setString(8, oUsuario.getSenha());

			statement.executeUpdate();
		}

        /**
         * Método responsável por validar o Login do Usuário no Sistema
         * @param oUsuario
         * @return ResultSet ResSet
         * @todo TESTAR!
         */
        public ResultSet autenticacaoUsuario(Usuario oUsuario) {
            //conn = new ConexaoBD().//amanhã, adaptar o novo método de conexao passando os parâmetros corretos
        //método não testado, apenas copiei o que tinha feito no NetBeans
            try {
                String sSql = "SELECT nome," +
                                    " senha " +
                                "FROM usuario " +
                               "WHERE nome = ? " +
                                 "AND senha = ?";

                PreparedStatement statement = conn.prepareStatement(sSql);

                statement.setString(1, oUsuario.getNome());
                statement.setString(2, oUsuario.getSenha());

                ResultSet ResSet = pstm.executeQuery();

                return ResSet;

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);

                return null;
            }
        }
    }
	
//		String sql = "INSERT INTO usuario "
//				+ "(nome, sexo, idade, peso, altura, metapeso, email, senha)"
//				+ "VALUES ("
//				+ usuario.getNome()	+" , "
//				+ usuario.getSexo() +" , "
//				+ usuario.getIdade() +" , "
//				+ usuario.getPeso() +" , "
//				+ usuario.getAltura() +" , "
//				+ usuario.getMetaPeso() +" , "
//				+ usuario.getEmail() +" , "
//				+ usuario.getSenha() + ")";
//
//		    try (Statement st = conn.createStatement()) {
//		        st.executeUpdate(sql);
//
//		    }
	}
}
