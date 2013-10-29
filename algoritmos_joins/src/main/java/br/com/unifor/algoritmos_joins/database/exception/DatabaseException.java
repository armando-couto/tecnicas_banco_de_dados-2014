package br.com.unifor.algoritmos_joins.database.exception;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class DatabaseException extends Exception {
	private static final long serialVersionUID = 1L;

	public DatabaseException() {
		super();
	}

	public DatabaseException(String msg) {
		super(msg);
	}
}