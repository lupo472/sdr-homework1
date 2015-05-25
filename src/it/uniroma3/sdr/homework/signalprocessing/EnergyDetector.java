package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.librerie.MetodiArray;
import it.uniroma3.sdr.homework.model.Noise;
import it.uniroma3.sdr.homework.model.Signal;

/**
 * Classe astratta utilizzata per semplificare e ridurre il codice delle classi 
 * <strong>Soglia</strong> e <strong>ProbabilitaDetection</Strong>
 *
 */
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
	
	/**
	 * Metodo astratto per la determinazione della Soglia/Probabilità di detection
	 * @return un double rappresentante la Soglia/probabilità di detection
	 */
	public abstract double determina();
	
	
	
	/**
	 * Metodo per il popolamento dei blocchi del rumore totale, 
	 * utilizzato per il calcolo della soglia e della probabilità
	 * di detection.
	 * 
	 * @param noise_blocks : array di array (matrice) vuoto
	 * @return noise :array di array (matrice) le cui righe (blocchi) sono costituite da rumore
	 * 
	 */
	protected double[][] popolaBlocchiNoise(){
		
		double[][] temp = new double[this.num_blocchi][];

		for(int i = 0;i<this.num_blocchi;i++){
			Noise noise_block = new Noise(this.SNR,this.signal.getLughezza());//rumore del blocco[n]
			double[] pri_noise = MetodiArray.sommaArray(noise_block.getParteReale(), noise_block.getParteImmaginaria());//array somma parte reale e immaginaria del rumore blocco[n]
			temp[i] = pri_noise;//pongo la somma dei due array parteImmaginaria e parteReale del rumore in un unico array
		}

		return temp;
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
