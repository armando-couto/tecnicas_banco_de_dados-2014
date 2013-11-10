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
public class MusicaDAO extends BasicDAO {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 4821534029199462109L;

	private String[] columns = new String[] { "ID", "NAME", "ID_ARTIST" };
	private RelationalTable table = RelationalTable.newTable(5, columns);

	public void search() {
		try {
			ConnectionFactory cf = new ConnectionFactory();
			Connection c = cf.getConnection();

			PreparedStatement st = c.prepareStatement("SELECT " + getColumns(columns) + " FROM MUSIC");
			ResultSet rs = st.executeQuery();

			Integer music_id = null;
			String name = null;
			Integer artist_id = null;
			while (rs.next()) {
				artist_id = rs.getInt("ID_ARTIST");
				name = rs.getString("NAME");
				music_id = rs.getInt("ID");
				table.insert(columns, new Object[] { music_id, name, artist_id });
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