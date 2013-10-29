package br.com.unifor.algoritmos_joins.dao;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class BasicDao {

	String getColumns(String[] columns) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < columns.length - 1; i++) {
			sb.append(columns[i] + ", ");
		}

		if (columns.length > 0) {
			sb.append(columns[columns.length - 1]);
		}
		return sb.toString();
	}
}