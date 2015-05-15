package it.uniroma3.sdr.homework.signalprocessing;

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
		
		mean = sum/array.length;
		
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
			
		var = sum/(array.length-1);
		
		return var;
		
	}
}
