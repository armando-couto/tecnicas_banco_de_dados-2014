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
public class AutorDAO extends GenericDAO {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 7812186780470156721L;
	
	private String[] columns = new String[] { "ID", "NOME" };
	private RelationalTable table = RelationalTable.newTable(5, columns);

	public void search() {

		try {
			Conexao cf = new Conexao();
			Connection c = cf.getConnection();

			PreparedStatement st = c.prepareStatement("SELECT " + getColumns(columns) + " FROM AUTOR");
			ResultSet rs = st.executeQuery();

			String name = null;
			Integer autor_id = null;
			while (rs.next()) {
				name = rs.getString("NOME");
				autor_id = rs.getInt("ID");
				table.insert(columns, new Object[] { autor_id, name });
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

	public static void main(String[] args) {
		AutorDAO a = new AutorDAO();
		a.search();
		RelationalTable rt = a.getSearch();
		System.out.println(rt.getPagesNum());
	}
}