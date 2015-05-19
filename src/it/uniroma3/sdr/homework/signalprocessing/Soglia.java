package it.uniroma3.sdr.homework.signalprocessing;


import it.uniroma3.sdr.homework.model.*;
/**
 * Classe relativa al calcolo della soglia di energia di un segnale.
 */
public class Soglia {

//	public static double soglia(Signal signal, double snr,int num_prove, double Pfa){
//		double[] z = new double[signal.getLength()]; //array delle energie del segnale in ogni prova
//		double soglia = 0;
//		Noise noise = new Noise(snr,signal.getLength());
//	
//		for(int i = 0;i<signal.getLength();i++){
//			double pri_noise = noise.getParteReale()[i] + noise.getParteImmaginaria()[i];//somma parte immaginaria e reale del rumore
//			z[i] = Math.pow(Math.abs(pri_noise),2);//mi salvo in z ogni elemento della sommatoria per l'energia del segnale
//		}
//		
//		try {
//			soglia = MeanVar.mean(z)+(Math.sqrt(2*MeanVar.var(z))*InvErf.inverf(1-2*Pfa));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return soglia;
//	}
	


}
