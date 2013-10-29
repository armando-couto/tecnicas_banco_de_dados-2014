package br.com.unifor.algoritmos_joins.join;

import java.util.List;

import br.com.unifor.algoritmos_joins.database.RelationalTable;
import br.com.unifor.algoritmos_joins.database.Tuple;
import br.com.unifor.algoritmos_joins.database.exception.DatabaseException;
import br.com.unifor.algoritmos_joins.main.GlobalVars;
import br.com.unifor.algoritmos_joins.utils.ArrayUtils;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class MergeJoin implements JoinAlgorithm {

	public RelationalTable join(RelationalTable artist, RelationalTable music) {
		String[] resultColumns = ArrayUtils.concat(artist.getColumnsNames(), music.getColumnsNames());
		RelationalTable resultTable = RelationalTable.newTable(GlobalVars.pageSize, resultColumns);
		try {
			List<Tuple> r = artist.orderBy("ID");
			List<Tuple> s = music.orderBy("ID_ARTIST");

			for (Tuple tr : r) {
				for (Tuple ts : s) {
					Integer rAtr = (Integer) tr.get("ID");
					Integer sAtr = (Integer) ts.get("ID_ARTIST");
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