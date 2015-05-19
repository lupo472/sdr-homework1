package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.model.Noise;
import it.uniroma3.sdr.homework.model.Signal;
/**
 * Classe relativa al calcolo della probabilità di detection ricavata dopo il calcolo 
 * della soglia e l'analisi del segnale
 *
 */
public class ProbabilitaDetection {
	
	public static double probDetection(Signal signalIN, double SNR,int num_prove, int num_blocchi,double soglia){
		double Pd = 0; //probabilità di detection
		double[] z = new double[num_prove];//array energia
		int cont = 0; //variabile contatore
		Noise noise = new Noise(SNR,signalIN.getLughezza());
		
		for(int i = 0;i<num_prove;i++){
			double pri_noise = noise.getParteReale()[i] + noise.getParteImmaginaria()[i];//somma parte reale e immaginaria del rumore
			double sign_mod=0;
			
			for(int j = 0;j<num_blocchi;j++){
				sing_noise
			}
		}
		
		return Pd;
	}

}
