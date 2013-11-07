package br.com.unifor.algoritmos_joins.main;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.unifor.algoritmos_joins.dao.Artista;
import br.com.unifor.algoritmos_joins.dao.Musica;
import br.com.unifor.algoritmos_joins.database.RelationalTable;
import br.com.unifor.algoritmos_joins.database.exception.DatabaseException;
import br.com.unifor.algoritmos_joins.join.BlockNestedLoopJoin;
import br.com.unifor.algoritmos_joins.join.HashJoin;
import br.com.unifor.algoritmos_joins.join.JoinAlgorithm;
import br.com.unifor.algoritmos_joins.join.MergeJoin;
import br.com.unifor.algoritmos_joins.utils.RelationalTableUtils;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class Main {
	private static final int BLOCK_NESTED_LOOP_JOIN = 1;
	private static final int HASH_JOIN = 2;
	private static final int MERGE_JOIN = 3;

	private static Scanner s = new Scanner(System.in);
	private static RelationalTable artist;
	private static RelationalTable music;

	public static void main(String[] args) throws SQLException, DatabaseException {
		Musica m = new Musica();
		m.search();

		Artista a = new Artista();
		a.search();

		artist = a.getSearch();
		music = m.getSearch();

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
		System.out.println("ESCOLHA UMA OP��O: ");
		System.out.println("0-Sair ");
		System.out.println("1-Block Nested Loop Join ");
		System.out.println("2-Hash Join");
		System.out.println("3-Merge Join ");
	}

	public static void action(int command) {
		JoinAlgorithm ja = null;
		switch (command) {
		case BLOCK_NESTED_LOOP_JOIN:
			ja = new BlockNestedLoopJoin();
			break;
		case HASH_JOIN:
			ja = new HashJoin();
			break;
		case MERGE_JOIN:
			ja = new MergeJoin();
			break;
		case 0:
			System.exit(1);
		}

		if (ja != null)
			join(ja, artist, music);
	}

	public static void join(JoinAlgorithm ja, RelationalTable t1, RelationalTable t2) {
		RelationalTable t = ja.join(t1, t2);
		RelationalTableUtils.print(t);
	}
}