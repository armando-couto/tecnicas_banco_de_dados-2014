package br.com.unifor.algoritmos_joins.utils;

import java.util.Arrays;

/**
 * @author armandocouto
 * @email coutoarmando@gmail.com
 * @date 29/10/2013
 */
public class ArrayUtils {

	public static <T> T[] concat(T[] array1, T[] array2) {
		T[] retArray = Arrays.copyOf(array1, array1.length + array2.length);
		System.arraycopy(array2, 0, retArray, array1.length, array2.length);
		return retArray;
	}
}