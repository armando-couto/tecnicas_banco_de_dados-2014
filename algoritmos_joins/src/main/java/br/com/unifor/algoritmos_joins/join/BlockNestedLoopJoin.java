package br.com.unifor.algoritmos_joins.join;

import java.util.List;

import br.com.unifor.algoritmos_joins.database.Page;
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
public class BlockNestedLoopJoin implements JoinAlgorithm {

	public RelationalTable join(RelationalTable artist, RelationalTable music) {
		String[] resultColumns = ArrayUtils.concat(artist.getColumnsNames(),
				music.getColumnsNames());

		RelationalTable resultTable = RelationalTable.newTable(
				GlobalVars.pageSize, resultColumns);
		try {
			List<Tuple> artistTuples = artist.getAllTuples();
			List<Tuple> musicTuples = music.getAllTuples();

			for (Page pagesArtist : artist.getAllPages()) {
				for (Page pagesMusic : music.getAllPages()) {
					
					for (Tuple artistTuple : pagesArtist.getTuples()) {
						for (Tuple musicTuple : pagesMusic.getTuples()) {
							int aIDMusic = (Integer) musicTuple
									.get("ID_ARTIST");
							int aIDArtist = (Integer) artistTuple
									.get("ID");
							if (aIDMusic == aIDArtist) {
								Object[] valuesArray = ArrayUtils.concat(
										artistTuple.toArray(),
										musicTuple.toArray());
								resultTable.insert(resultColumns, valuesArray);
							}
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