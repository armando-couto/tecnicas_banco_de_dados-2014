package br.com.unifor.algoritmos_joins.utils;

import java.util.List;

import br.com.unifor.algoritmos_joins.database.Pagina;
import br.com.unifor.algoritmos_joins.database.RelationalTable;
import br.com.unifor.algoritmos_joins.database.Tupla;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class RelationalTableUtils {

	public static void print(RelationalTable rt) {
		StringBuffer sbColumn = new StringBuffer();
		String[] columns = rt.getColumnsNames();
		List<Tupla> tuplas = rt.getAllTuples();
		List<Pagina> pages = rt.getAllPages();

		for (int i = 0; i < columns.length - 1; i++) {
			String column = columns[i];
			sbColumn.append(column + "\t|");
		}

		if (columns.length > 0) {
			sbColumn.append(columns[columns.length - 1] + "\t|");
		}

		sbColumn.append("\n");

		for (int i = 0; i < pages.size(); i++) {
			sbColumn.append("PAGINA " + i);
			sbColumn.append("\n");
			Pagina p = pages.get(i);
			for (Tupla t : p.getTuples()) {
				for (int j = 0; j < t.getSize() - 1; j++) {
					sbColumn.append(t.get(j) + "\t|");
				}
				if (t.getSize() > 0)
					sbColumn.append(t.get(t.getSize() - 1) + "\t|");
				sbColumn.append("\n");
			}
		}

		System.out.println(sbColumn.toString());
	}
}