package br.com.unifor.algoritmos_joins.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class ConnectionFactory implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -5089133664267397875L;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.print("Classe do driver JDBC não encontrada.");
		}
	}

	public Connection getConnection() throws SQLException {
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/musica";
		return DriverManager.getConnection(url, user, password);
	}
}