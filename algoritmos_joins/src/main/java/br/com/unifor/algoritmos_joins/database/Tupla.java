package br.com.unifor.algoritmos_joins.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class Tupla implements Serializable {
	
	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -198885783985033584L;
	
	private List<String> columnsName;
	private List<Object> values;
	private Map<String, Object> valueMap;
	private int size;

	public Tupla() {
		this.columnsName = new ArrayList<String>();
		this.values = new ArrayList<Object>();
		this.valueMap = new HashMap<String, Object>();
	}

	public Tupla(String[] columnsName, Object[] values) {
		this.columnsName = new ArrayList<String>(Arrays.asList(columnsName));
		this.values = new ArrayList<Object>(Arrays.asList(values));
		this.valueMap = new HashMap<String, Object>();
		
		
		for (int i = 0; i < this.columnsName.size(); i++) {
			String columnName = this.columnsName.get(i);
			Object value = this.values.get(i);
			
			valueMap.put(columnName, value);
		}
	}

	public Object get(int i) {
		return values.get(i);
	}

	public Object get(String columnName) {
		return valueMap.get(columnName);
	}

	public int getSize() {
		return values.size();
	}

	public Object[] toArray() {
		return values.toArray(new Object[] {});
	}
}