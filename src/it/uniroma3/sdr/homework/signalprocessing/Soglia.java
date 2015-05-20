package it.uniroma3.sdr.homework.signalprocessing;


import it.uniroma3.sdr.homework.model.*;
/**
 * Classe relativa al calcolo della soglia di energia di un segnale.
 */
public class Soglia {
	private double SNR;
	private int num_prove;
	private int num_blocchi;//numero dei blocchi rumore (repliche N)
	private Signal signal;
	private double Pfa;
	
	public Soglia(Signal signal, double SNR, int num_prove, int num_blocchi, double Pfa){
		this.signal = signal;
		this.SNR = SNR;
		this.num_prove = num_prove;
		this.num_blocchi = num_blocchi;
		this.Pfa = Pfa;
	}

	public double determina(){
		double[] z = new double[this.num_prove]; //array delle energie del segnale in ogni prova
		double soglia = 0;
	
		for(int i = 0;i<this.num_prove;i++){
			double[][] noise_blocks = new double[this.num_blocchi][this.signal.getLughezza()];//array di array che conterrà come righe la somma degli array di parteImmaginaria e reale di  rumori
			noise_blocks = popolaBlocchiNoise(noise_blocks);
			
			double sign_mod = 0;//sommatoria dei moduli quadrati dei blocchi di rumore
			
			for(int j = 0;j<this.num_blocchi;j++){
				double detector_out = sommaElementiArray(moduloQuadrato(noise_blocks[j]));//modulo quadrato del blocco rumore
				sign_mod = sign_mod + detector_out;
			}
			
		z[i] = sign_mod/num_blocchi;//calcolo dell'elemento i dell'array delle energie delle prove
		
		}
		
		try {
			soglia = MeanVar.mean(z)+(Math.sqrt(2*MeanVar.var(z))*InvErf.inverf(1-2*Pfa));//mi ricavo la soglia
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soglia;
	}
	
	/*
	 * Metodo per il popolamento dei blocchi del rumore totale, utilizzato per il calcolo della soglia
	 */
	private double[][] popolaBlocchiNoise(double[][] noise_blocks){
		
		for(int i = 0;i<this.num_blocchi;i++){
			Noise noise_block = new Noise(this.SNR,this.signal.getLughezza());//rumore del blocco[n]
			double[] pri_noise = new double[this.signal.getLughezza()];//array somma parte reale e immaginaria del rumore blocco[n]
			for(int j = 0;j<pri_noise.length;j++)
				pri_noise[j] = noise_block.getParteImmaginaria()[j] + noise_block.getParteReale()[j];
			noise_blocks[i] = pri_noise;//pongo la somma dei due array parteImmaginaria e parteReale del rumore in un unico array
		}
		
		return noise_blocks;
	}
	
	/*
	 * Metodo per la somma di tutti gli elementi di un array (java API non contiene un metodo equivalente)
	 */
	private double sommaElementiArray(double[] array){
		double sum = 0;
		
		for(double i : array)
			sum += i;
		
		return sum;
			
	}
	
	/*
	 * Metodo per il modulo al quadrato dell'array delle repliche del rumore
	 */
	private double[] moduloQuadrato(double[] array){
		
		for(double i : array)
			Math.pow(Math.abs(i),2);
		
		return array;
	}
}
