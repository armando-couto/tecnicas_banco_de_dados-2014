package br.com.unifor.algoritmos_joins.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class Pagina implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 8045974622328514159L;
	
	private final int maxSize;
	private int size;
	private List<Tupla> tuples;
	
	public Pagina(int maxSize){
		this.maxSize = maxSize;
		this.size = 0;
		this.tuples = new ArrayList<Tupla>();
	}

	public int getSize() {
		return size;
	}

	public List<Tupla> getTuples() {
		return tuples;
	}

	public void setTuples(List<Tupla> tuples) {
		this.tuples = tuples;
	}
	
	public int getMaxSize(){
		return maxSize;
	}
	
	public void addTuple(Tupla tuple){
		size++;
		tuples.add(tuple);
	}
}