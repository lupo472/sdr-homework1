package it.uniroma3.sdr.homework.signalprocessing;


import it.uniroma3.sdr.homework.model.*;

import org.apache.commons.math3.special.Erf;
/**
 * Classe relativa al calcolo della soglia di energia di un segnale e del confronto di essa.
 */
public class Soglia {

	public static double soglia(Signal signal, double snr,int num_prove, double Pfa){
		double[] z = new double[signal.getLength()]; //array delle energie del segnale in ogni prova
		double soglia;
		Noise noise = new Noise(snr,signal.getLength());
	
		for(int i = 0;i<signal.getLength();i++){
			double pri_noise = noise.getParteReale()[i] + noise.getParteImmaginaria()[i];//somma parte immaginaria e reale del rumore
			z[i] = Math.pow(Math.abs(pri_noise),2);//mi salvo in z ogni elemento della sommatoria per l'energia del segnale
		}
		soglia = MeanVar.mean(z)+(Math.sqrt(2*MeanVar.var(z))*Erf.erfInv(1-2*Pfa));
		return soglia;
	}
	
	public static boolean confronto_soglia(Signal signalIN, double snr, double soglia){
		Noise noise = new Noise(snr,signalIN.getLughezza());
		Signal signal_noise = new Signal(signalIN.getLughezza());//segnale risultante dalla somma del segnale in studio e il rumore
		
		boolean hole = false;//spectrum hole
		
		double[] valoriRealiSignalNoise = new double[signalIN.getLughezza()];//array di supporto per l'aggiunta dei valore della somma del segnale e rumore
		double[] valoriImmaginariSignalNoise = new double[signalIN.getLughezza()];// //  //  //
		
		double z = 0;//energia del segnale finale
		double pri_signal_noise = 0;//somma parte reale e immaginaria del segnale finale
	
		//ciclo for per la somma del segnale in ingresso e rumore in unico segnale
		for(int i = 0;i<signalIN.getLength();i++){
			double parteReale = signalIN.getReale()[i] + noise.getParteReale()[i];
			double parteImmaginaria = signalIN.getImmaginaria()[i] + noise.getParteImmaginaria()[i]; 
			valoriRealiSignalNoise[i] = parteReale;
			valoriImmaginariSignalNoise[i] = parteImmaginaria;
		}
		signal_noise.setReale(valoriRealiSignalNoise);
		signal_noise.setImmaginaria(valoriImmaginariSignalNoise);
		
		//calcolo dell'energia
		for(int i = 0;i<signalIN.getLughezza();i++){
			pri_signal_noise = signal_noise.getReale()[i] + signal_noise.getImmaginaria()[i];
			z += Math.pow(Math.abs(pri_signal_noise), 2);
		}
		
		//controllo della soglia
		if(z>soglia)
			hole = false;
		else
			hole = true;
		
		return hole;
	}

}
