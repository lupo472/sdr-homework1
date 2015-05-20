package it.uniroma3.sdr.homework.model;

import java.util.Random;


/**
 * Classe che rappresenta un rumore bianco gaussiano additivo
 * 
 *
 */
public class Noise {

	private double varianza;
	private int length;
	private double[] parteReale;
	private double[] parteImmaginaria;
	private double pot_rumore;
	
	public Noise(double snr, int length){
		init(snr,length);
	}
	
	/*
	 * Metodo di inizializzazione del rumore bianco gaussiano additivo
	 */
	private void init(double snr,int length){
		Random campione = null;
		double snr_linearizzato = Math.pow(10, (snr/10));
		this.pot_rumore = (1/snr_linearizzato);
		this.length = length;
		
		this.parteReale = new double[length];
		for(int i = 0;i<this.length;i++){
			campione = new Random();
			parteReale[i] = campione.nextGaussian() * Math.sqrt(pot_rumore/2);
		}
		
		this.parteImmaginaria = new double[length];
		for(int i = 0;i<this.length;i++){
			campione = new Random();
			parteImmaginaria[i] = campione.nextGaussian() * Math.sqrt(pot_rumore/2);
		}
	}

	/*
	 *  GETTER & SETTER
	 */
	
	public double getVarianza() {
		return varianza;
	}

	public void setVarianza(double varianza) {
		this.varianza = varianza;
	}

	public double[] getParteReale() {
		return parteReale;
	}

	public void setParteReale(double[] parteReale) {
		this.parteReale = parteReale;
	}

	public double[] getParteImmaginaria() {
		return parteImmaginaria;
	}

	public void setParteImmaginaria(double[] parteImmaginaria) {
		this.parteImmaginaria = parteImmaginaria;
	}

	public double getPot_rumore() {
		return pot_rumore;
	}

	public void setPot_rumore(double pot_rumore) {
		this.pot_rumore = pot_rumore;
	}

}
