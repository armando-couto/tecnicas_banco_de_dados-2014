package br.com.unifor.algoritmos_joins.join;

import br.com.unifor.algoritmos_joins.database.RelationalTable;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public interface AlgoritmoJoin {
	public RelationalTable join(RelationalTable t1, RelationalTable t2);
}