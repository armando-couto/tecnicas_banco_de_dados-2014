package br.com.unifor.algoritmos_joins.join;

import java.util.Hashtable;
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
public class HashJoin implements AlgoritmoJoin {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -5098223404919048277L;
	
	public int pageSize = 5;
	private Hashtable<Integer, Integer> hashTableAutor = new Hashtable<Integer, Integer>();
	private Hashtable<Integer, Integer> hashTableLivro = new Hashtable<Integer, Integer>();

	public RelationalTable join(RelationalTable autor, RelationalTable livro) {
		String[] resultColumns = ArrayUtils.concat(autor.getColumnsNames(), livro.getColumnsNames());

		RelationalTable resultTable = RelationalTable.newTable(pageSize, resultColumns);
		try {
			List<Tupla> autorTuplas = autor.getAllTuplas();
			List<Tupla> livroTuplas = livro.getAllTuplas();

			for (Tupla livroTupla : livroTuplas) {
				Integer autorID = (Integer) livroTupla.get("ID_AUTOR");
				int bucket = hashFunction(autorID);
				hashTableLivro.put(bucket, autorID);
			}

			for (Tupla autorTupla : autorTuplas) {
				Integer autorID = (Integer) autorTupla.get("ID");
				int bucket = hashFunction(autorID);
				hashTableAutor.put(bucket, autorID);
			}

			for (Tupla autorTupla : autorTuplas) {
				for (Tupla livroTupla : livroTuplas) {
					int bucketLivro = hashFunction((Integer) livroTupla.get("ID_AUTOR"));
					int bucketAutor = hashFunction((Integer) autorTupla.get("ID"));
					if (bucketLivro == bucketAutor) {
						Object[] valuesArray = ArrayUtils.concat(autorTupla.toArray(), livroTupla.toArray());
						resultTable.insert(resultColumns, valuesArray);
					}
				}
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return resultTable;
	}

	private Integer hashFunction(Integer i) {
		return i;
	}
}