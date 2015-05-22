package it.uniroma3.sdr.homework.librerie;

/**
 * Classe relativa al calcolo della media di un array di valori e della sua
 * varianza
 *
 */
public class MeanVar {

	/*
	 * Calcolo della media
	 */
	public static double mean(double[] array){
		double mean;
		double sum = 0;
		
		//somma di tutti i valori dell'array
		for(int i = 0;i<array.length;i++)
			sum += array[i];
		
		mean = (double)sum/(double)array.length;
		
		return mean;

	}
	
	/*
	 * Calcolo della varianza
	 */
	public static double var(double[] array){
		double var;
		double mean = mean(array);
		double sum = 0;
		
		for(int i = 0;i<array.length;i++)
			sum += Math.pow(array[i] - mean, 2);
			
		var = (double)sum/(double)((double)array.length-1);
		
		return var;
		
	}
}
