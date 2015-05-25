package it.uniroma3.sdr.homework.signalprocessing;


import it.uniroma3.sdr.homework.librerie.*;
import it.uniroma3.sdr.homework.model.*;
/**
 * Classe relativa al calcolo della soglia di energia di un segnale.
 */
public class Soglia extends EnergyDetector {
	
	private double Pfa;

	public Soglia(Signal signal, double SNR, int num_prove, int num_blocchi, double Pfa){
		super(signal, SNR, num_prove, num_blocchi);
		this.Pfa = Pfa;
	}
	
	/**
	 * Metodo che determina la soglia a partire da un segnale.
	 * <strong>Attenzione</strong> : se il numero di blocchi è pari a 1, il 
	 * 	metodo restituisce NaN.
	 * @return soglia
	 */
	public double determina(){
		double[] z = new double[this.getNum_prove()]; //array delle energie del segnale in ogni prova
		double soglia = 0;

		for(int i = 0;i<this.getNum_prove();i++){
			double[][] noise_blocks = popolaBlocchiNoise();//array di array che conterrï¿½ come righe la somma degli array di parteImmaginaria e reale di  rumori

			double sign_mod = 0;//sommatoria dei moduli quadrati dei blocchi di rumore

			for(int j = 0;j<this.getNum_blocchi();j++){
				double detector_out = MetodiArray.sommaElementiArray(MetodiArray.moduloQuadrato(noise_blocks[j]));//modulo quadrato del blocco rumore
				sign_mod = sign_mod + detector_out;
			}

			z[i] = sign_mod/(double)this.getNum_blocchi();//calcolo dell'elemento i dell'array delle energie delle prove

		}

		try {
			soglia = MeanVar.mean(z)+(Math.sqrt(2*MeanVar.var(z))*InvErf.inverf(1-2*this.Pfa));//mi ricavo la soglia
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soglia;
	}


	/** GETTER & SETTER **/

	public double getPfa() {
		return Pfa;
	}

	public void setPfa(double pfa) {
		Pfa = pfa;
	}

	@Override
	public String toString() {
		return "Soglia [Pfa=" + Pfa + "]";
	}
	
	
}
