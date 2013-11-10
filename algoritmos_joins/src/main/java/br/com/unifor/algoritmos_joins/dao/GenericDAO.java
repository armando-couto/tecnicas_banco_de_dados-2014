package br.com.unifor.algoritmos_joins.dao;

import java.io.Serializable;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class GenericDAO implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -202405722063791582L;

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