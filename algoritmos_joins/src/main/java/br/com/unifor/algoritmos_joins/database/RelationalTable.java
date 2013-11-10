package br.com.unifor.algoritmos_joins.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.unifor.algoritmos_joins.database.exception.DatabaseException;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class RelationalTable implements Serializable {

	/**
	 * Serial UID. 
	 */
	private static final long serialVersionUID = -2488513465577324063L;
	
	private List<String> columnName;
	private List<Pagina> pages;
	private Pagina lastPage;
	private int totalMaxPageSize;
	private int totalPagesNum = 0;
	private int totalTuplesNum = 0;

	private RelationalTable(int pageSize) {
		this.totalMaxPageSize = pageSize;
	}

	public static RelationalTable newTable(int pageSize, String... columnNames) {
		RelationalTable table = new RelationalTable(pageSize);
		table.columnName = new ArrayList<String>();
		for (String columnName : columnNames) {
			table.columnName.add(columnName);
		}

		table.pages = new ArrayList<Pagina>();

		return table;
	}

	public void insert(String[] columns, Object[] values) throws DatabaseException {
		if (columns == null || values == null) {
			throw new DatabaseException("The parametes of a insert operation must not to be null");
		}

		if (columns.length > columnName.size()) {
			throw new DatabaseException("There are more coluns then the table have.");
		}

		if (!verifyColumnNames(columns)) {
			throw new DatabaseException("The column names must match the name of the table columns");
		}

		Tupla tuple = new Tupla(columns, values);

		if (lastPage == null || lastPage.getSize() == totalMaxPageSize) {
			this.addPage();
		}

		lastPage.addTuple(tuple);
		totalTuplesNum++;
	}

	public List<Tupla> orderBy(String column) {
		List<Tupla> tuples = getAllTuples();
		Collections.sort(tuples, new Comparator<Tupla>() {
			public int compare(Tupla o1, Tupla o2) {

				Integer t1aID = (Integer) o1.get("ARTIST_ID");
				Integer t2aID = (Integer) o2.get("ARTIST_ID");

				if (t1aID == null)
					return -1;
				if (t2aID == null)
					return 1;

				return t1aID.compareTo(t2aID);
			}
		});
		return tuples;
	}

	public boolean verifyColumnNames(String[] columns) {
		boolean ret = true;
		for (String column : columns) {
			ret &= columnName.contains(column);
		}
		return ret;
	}

	private void addPage() {
		Pagina newPage = new Pagina(totalMaxPageSize);
		pages.add(newPage);
		lastPage = newPage;
		totalPagesNum++;
	}

	public int getTuplesNum() {
		return totalTuplesNum;
	}

	public int getPagesNum() {
		return totalPagesNum;
	}

	public List<Pagina> getAllPages() {
		return this.pages;
	}

	public List<Tupla> getAllTuples() {
		List<Tupla> returnList = new ArrayList<Tupla>();
		for (Pagina p : pages) {
			returnList.addAll(p.getTuples());
		}
		return returnList;
	}

	public String[] getColumnsNames() {
		return columnName.toArray(new String[] {});
	}
}