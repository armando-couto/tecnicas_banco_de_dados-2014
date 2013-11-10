package br.com.unifor.algoritmos_joins.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.unifor.algoritmos_joins.database.RelationalTable;
import br.com.unifor.algoritmos_joins.database.exception.DatabaseException;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class LivroDAO extends GenericDAO {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 4821534029199462109L;

	private String[] columns = new String[] { "ID", "NOME", "ID_AUTOR" };
	private RelationalTable table = RelationalTable.newTable(5, columns);

	public void search() {
		try {
			Conexao cf = new Conexao();
			Connection c = cf.getConnection();

			PreparedStatement st = c.prepareStatement("SELECT " + getColumns(columns) + " FROM LIVRO");
			ResultSet rs = st.executeQuery();

			Integer livro_id = null;
			String name = null;
			Integer autor_id = null;
			while (rs.next()) {
				autor_id = rs.getInt("ID_AUTOR");
				name = rs.getString("NOME");
				livro_id = rs.getInt("ID");
				table.insert(columns, new Object[] { livro_id, name, autor_id });
			}
			rs.close();
			st.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public RelationalTable getSearch() {
		return table;
	}
}