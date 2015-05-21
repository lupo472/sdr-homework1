/**
 * Classe che modella i segnali nel discreto
 * @author Antonio Tedeschi
 *
 */

package it.uniroma3.sdr.homework.model;

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

	/**
	 * Costruttore basato sull'implementazione con un array di oggetti <strong>Complex</strong>,
	 * che contiene dsia la parte reale che immaginaria
	 * 
	 * @param values : array di <strong>Complex</strong>
	 */
	public Signal(Complex[] values) {
		super();
		this.values = values;
	}
	
	/**
	 * Costruttore basato sull'implementazione con 2 array di <strong>double</strong>
	 * per la parte reale e immaginaria
	 * 
	 * @param lenght : lunghezza del segnale
	 */
	public Signal(int lenght){
		init(length);
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
	
	/*
	 * Metodi relativi alla implementazione di un segnale con array di oggetti Complex
	 */
	public Complex[] getValues() {
		return values;
	}

	public void setValues(Complex[] values) {
		this.values = values;
	}

	public String toString(){
		String s = "";
		for(int i=0; i<this.getLength(); i++)
			s+=this.values[i].toString()+", ";
		return s;
	}

	public int getLength(){
		return this.values.length;
	}
	
	public void setLength(int length) {
		this.length = length;
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
	
	
	/*
	 * Metodi relativi alla implementazione di un segnale con 2 array di double
	 */
	public int getLughezza(){
		return this.length;
	}

	public void setLunghezza(int lunghezza){
		this.length = lunghezza;
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
	
}
