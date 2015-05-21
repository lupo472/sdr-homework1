/**
 * Classe che modella i segnali nel discreto
 * @author Antonio Tedeschi
 *
 */

package it.uniroma3.sdr.homework.model;

import it.uniroma3.sdr.homework.signalprocessing.FileReading;

public class Signal {
	
	/*
	 * variabili di istanza per il segnale tipo
	 */
	//<--
	private int length;
	private double[] reale;
	private double[] immaginaria;
	//-->
	
	public Complex[] values;

	public Signal(Complex[] values) {
		super();
		this.values = values;
	}
	
	//<-- SEGNALE TIPO -->
	public Signal(int lenght){
		init(lenght);
	}
	
	/*
	 * Metodo di inizializzazione del segnale
	 */
	private void init(int length){
		
		this.length = length;
		this.reale = new double[length];
		this.immaginaria = new double[length];
		
		for(int i = 0;i<this.length;i++){
			double v = Math.random();
			if(v < 0.5)
				this.reale[i] = 1/Math.sqrt(2);
			else
				this.reale[i] = -1/Math.sqrt(2);
			
			double p = Math.random();
			if(p<0.5)
				this.immaginaria[i]  = 1/Math.sqrt(2);
			else
				this.immaginaria[i] = -1/Math.sqrt(2);
		}
	}
	

	/**** GETTER & SETTER ****/
	
	public Complex[] getValues() {
		return values;
	}

	public void setValues(Complex[] values) {
		this.values = values;
	}
	
	/**
	 * Metodo per ottenere la lunghezza del segnale e quindi il numero di campioni del segnale
	 * @return
	 */
	public int getLength(){
		return this.values.length;
	}
	
	public int getLughezza(){
		return this.length;
	}
	public int hashCode() {
		return this.values.hashCode();
	}

	
	public boolean equals(Object o) {

		boolean equals = true;
		Signal s = (Signal)o;

		if(!(this.values.length == s.getLength()))
			return equals = false;

		for(int i=0; i<this.values.length; i++ )
			if(!this.values[i].equals(s.getValues()[i]))
				return equals = false;

		return equals;

	}

	public double[] getReale() {
		return reale;
	}

	public void setReale(double[] reale) {
		this.reale = reale;
	}

	public double[] getImmaginaria() {
		return immaginaria;
	}

	public void setImmaginaria(double[] immaginaria) {
		this.immaginaria = immaginaria;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	@Override
	public String toString(){
		String s = "";
		for(int i=0; i<this.length; i++){
			System.out.println("[Re: "+getReale()[i]+", Im: "+getImmaginaria()[i]+"]");
		}
		return s;
	}

	
}
