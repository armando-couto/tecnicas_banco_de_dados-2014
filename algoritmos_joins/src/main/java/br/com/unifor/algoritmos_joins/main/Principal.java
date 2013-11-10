package br.com.unifor.algoritmos_joins.main;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.unifor.algoritmos_joins.dao.AutorDAO;
import br.com.unifor.algoritmos_joins.dao.LivroDAO;
import br.com.unifor.algoritmos_joins.database.RelationalTable;
import br.com.unifor.algoritmos_joins.database.exception.DatabaseException;
import br.com.unifor.algoritmos_joins.join.BlockNestedLoopJoin;
import br.com.unifor.algoritmos_joins.join.HashJoin;
import br.com.unifor.algoritmos_joins.join.AlgoritmoJoin;
import br.com.unifor.algoritmos_joins.join.MergeJoin;
import br.com.unifor.algoritmos_joins.utils.RelacionamentoDasTabelas;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class Principal implements Serializable {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = -3632163042165281340L;
	
	private static Scanner s = new Scanner(System.in);
	private static RelationalTable autor;
	private static RelationalTable livro;

	public static void main(String[] args) throws SQLException, DatabaseException {
		LivroDAO m = new LivroDAO();
		m.search();

		AutorDAO a = new AutorDAO();
		a.search();

		autor = a.getSearch();
		livro = m.getSearch();

		menu();
	}

	public static void menu() {
		while (true) {
			showMenu();
			int command = s.nextInt();
			action(command);
		}
	}

	public static void showMenu() {
		System.out.println("ESCOLHA UMA OPÇÃO: ");
		System.out.println("0-Sair ");
		System.out.println("1-Block Nested Loop Join ");
		System.out.println("2-Hash Join");
		System.out.println("3-Merge Join ");
	}

	public static void action(int command) {
		AlgoritmoJoin ja = null;
		switch (command) {
			case 1:
				ja = new BlockNestedLoopJoin();
				break;
			case 2:
				ja = new HashJoin();
				break;
			case 3:
				ja = new MergeJoin();
				break;
			case 0:
				System.exit(1);
		}

		if (ja != null)
			join(ja, autor, livro);
	}

	public static void join(AlgoritmoJoin ja, RelationalTable t1, RelationalTable t2) {
		RelationalTable t = ja.join(t1, t2);
		RelacionamentoDasTabelas.print(t);
	}
}