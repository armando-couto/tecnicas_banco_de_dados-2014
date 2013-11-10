package br.com.unifor.algoritmos_joins.join;

import br.com.unifor.algoritmos_joins.database.Pagina;
import br.com.unifor.algoritmos_joins.database.RelationalTable;
import br.com.unifor.algoritmos_joins.database.Tupla;
import br.com.unifor.algoritmos_joins.database.exception.DatabaseException;
import br.com.unifor.algoritmos_joins.utils.ArrayUtils;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class BlockNestedLoopJoin implements AlgoritmoJoin {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -6192906236537302180L;
	
	public int pageSize = 5;	
	
	public RelationalTable join(RelationalTable autor, RelationalTable livro) {
		String[] resultColumns = ArrayUtils.concat(autor.getColumnsNames(), livro.getColumnsNames());

		RelationalTable resultTable = RelationalTable.newTable(pageSize, resultColumns);
		try {
			for (Pagina pagesAutor : autor.getAllPages()) {
				for (Pagina pagesLivro : livro.getAllPages()) {

					for (Tupla autorTupla : pagesAutor.getTuplas()) {
						for (Tupla livroTupla : pagesLivro.getTuplas()) {
							int aIDLivro = (Integer) livroTupla.get("ID_AUTOR");
							int aIDAutor = (Integer) autorTupla.get("ID");
							if (aIDLivro == aIDAutor) {
								Object[] valuesArray = ArrayUtils.concat(autorTupla.toArray(), livroTupla.toArray());
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