package br.com.unifor.algoritmos_joins.database;

import java.util.ArrayList;
import java.util.List;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class Page {

	private final int maxSize;
	private int size;
	private List<Tuple> tuples;
	
	public Page(int maxSize){
		this.maxSize = maxSize;
		this.size = 0;
		this.tuples = new ArrayList<Tuple>();
	}

	public int getSize() {
		return size;
	}

	public List<Tuple> getTuples() {
		return tuples;
	}

	public void setTuples(List<Tuple> tuples) {
		this.tuples = tuples;
	}
	
	public int getMaxSize(){
		return maxSize;
	}
	
	public void addTuple(Tuple tuple){
		size++;
		tuples.add(tuple);
	}
}