package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.model.*;

import org.apache.commons.math3.special.Erf;
/**
 * Classe relativa al calcolo della soglia di energia di un segnale
 */
public class Soglia {

	public static double soglia(Signal signal, double snr,int num_prove, double Pfa){
		double[] z = new double[signal.getLength()]; //array delle energie del segnale in ogni prova
		double soglia;
	
		for(int i = 0;i<signal.getLength();i++){
			Noise noise = new Noise(snr,signal.getLength());
			double pri_noise = noise.getParteReale()[i] + noise.getParteImmaginaria()[i];//somma parte immaginaria e reale del rumore
			z[i] = Math.pow(Math.abs(pri_noise),2);//mi salvo in z ogni elemento della sommatoria per l'energia del segnale
		}
		soglia = MeanVar.mean(z)+(Math.sqrt(2*MeanVar.var(z))*Erf.erfInv(1-2*Pfa));
		return soglia;
	}

}
