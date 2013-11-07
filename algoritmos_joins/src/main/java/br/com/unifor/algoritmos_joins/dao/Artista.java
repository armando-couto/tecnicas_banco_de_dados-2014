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
public class Artista extends BasicDao {

	private String[] columns = new String[] { "ID", "NAME" };
	private RelationalTable table = RelationalTable.newTable(5, columns);

	public void search() {

		try {
			ConnectionFactory cf = new ConnectionFactory();
			Connection c = cf.getConnection();

			PreparedStatement st = c.prepareStatement("SELECT " + getColumns(columns) + " FROM ARTIST");
			ResultSet rs = st.executeQuery();

			String name = null;
			Integer artist_id = null;
			while (rs.next()) {
				name = rs.getString("NAME");
				artist_id = rs.getInt("ID");
				table.insert(columns, new Object[] { artist_id, name });
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
		Artista a = new Artista();
		a.search();
		RelationalTable rt = a.getSearch();
		System.out.println(rt.getPagesNum());
	}

}
