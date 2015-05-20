package it.uniroma3.sdr.homework.librerie;

/**
 * Classe contenente tutte le operazioni sugli array necessarie per poter calcolare
 * la soglia e la probability detection
 */

public class MetodiArray {
	
	/**
	 * Metodo per la somma di tutti gli elementi di un array (java API non contiene un metodo equivalente)
	 * 
	 * @param array : array di double
	 * @return sum : un singolo double risultante dalla somma di tutti gli elementi dell'array
	 * 
	 */
	public static double sommaElementiArray(double[] array){
		double sum = 0;

		for(double i : array)
			sum += i;

		return sum;

	}

	/**
	 * Metodo per il modulo al quadrato dell'array delle repliche del rumore
	 * 
	 * @param array : array di double 
	 * @return stesso array di double, i cui elementi però sono stati messi a modulo e alla potenza di 2
	 */
	public static double[] moduloQuadrato(double[] array){

		for(double i : array)
			Math.pow(Math.abs(i),2);

		return array;
	}
	
	/**
	 * Metodo per la somma di 2 array
	 * 
	 * @param a1 : primo array
	 * @param a2 : secondo array
	 * @return somma : array di double risultato della somma
	 * @
	 * 
	 */
	public static double[] sommaArray(double[] a1, double[] a2){
		
		double[] somma = new double[a1.length];
		
		for(int i = 0;i<a1.length;i++)
			somma[i] = a1[i] + a2[i];
		
		return somma;
	}


}
