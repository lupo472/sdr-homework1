package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.librerie.MetodiArray;
import it.uniroma3.sdr.homework.model.Noise;
import it.uniroma3.sdr.homework.model.Signal;

public abstract class EnergyDetector {
	
	private Signal signal;
	private double SNR;
	private int num_prove;
	private int num_blocchi;//numero dei blocchi rumore (repliche N)
	
	public EnergyDetector(Signal signal, double SNR,int num_prove, int num_blocchi){
		this.signal = signal;
		this.SNR = SNR;
		this.num_prove = num_prove;
		this.num_blocchi = num_blocchi;
	}
	
	/** METODI **/
	
	public abstract double determina();
	
	
	
	/**
	 * Metodo per il popolamento dei blocchi del rumore totale, utilizzato per il calcolo della soglia
	 * 
	 * @param noise_blocks : array di array (matrice) vuoto
	 * @return noise :array di array (matrice) le cui righe (blocchi) sono costituite da rumore
	 * 
	 */
	protected double[][] popolaBlocchiNoise(double[][] noise_blocks){

		for(int i = 0;i<this.getNum_blocchi();i++){
			Noise noise_block = new Noise(this.getSNR(),this.getSignal().getLughezza());//rumore del blocco[n]
			double[] pri_noise = MetodiArray.sommaArray(noise_block.getParteReale(), noise_block.getParteImmaginaria());//array somma parte reale e immaginaria del rumore blocco[n]
			noise_blocks[i] = pri_noise;//pongo la somma dei due array parteImmaginaria e parteReale del rumore in un unico array
		}

		return noise_blocks;
	}
	
	
	
	/** GETTER & SETTER **/
	
	public double getSNR() {
		return SNR;
	}

	public void setSNR(double sNR) {
		SNR = sNR;
	}

	public int getNum_prove() {
		return num_prove;
	}

	public void setNum_prove(int num_prove) {
		this.num_prove = num_prove;
	}

	public int getNum_blocchi() {
		return num_blocchi;
	}

	public void setNum_blocchi(int num_blocchi) {
		this.num_blocchi = num_blocchi;
	}

	public Signal getSignal() {
		return signal;
	}

	public void setSignal(Signal signal) {
		this.signal = signal;
	}
	
}
