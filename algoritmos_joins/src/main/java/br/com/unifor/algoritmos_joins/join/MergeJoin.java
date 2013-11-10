package br.com.unifor.algoritmos_joins.join;

import java.util.List;

import br.com.unifor.algoritmos_joins.database.RelationalTable;
import br.com.unifor.algoritmos_joins.database.Tupla;
import br.com.unifor.algoritmos_joins.database.exception.DatabaseException;
import br.com.unifor.algoritmos_joins.utils.ArrayUtils;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class MergeJoin implements AlgoritmoJoin {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -3292404501075364485L;
	
	public int pageSize = 5;
	
	public RelationalTable join(RelationalTable autor, RelationalTable livro) {
		String[] resultColumns = ArrayUtils.concat(autor.getColumnsNames(), livro.getColumnsNames());
		RelationalTable resultTable = RelationalTable.newTable(pageSize, resultColumns);
		try {
			List<Tupla> r = autor.orderBy("ID");
			List<Tupla> s = livro.orderBy("ID_AUTOR");

			for (Tupla tr : r) {
				for (Tupla ts : s) {
					Integer rAtr = (Integer) tr.get("ID");
					Integer sAtr = (Integer) ts.get("ID_AUTOR");
					if (sAtr >= rAtr) {
						if (rAtr == sAtr) {
							Object[] valuesArray = ArrayUtils.concat(tr.toArray(), ts.toArray());
							resultTable.insert(resultColumns, valuesArray);
						}
					}
				}
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return resultTable;
	}
}