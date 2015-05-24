package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.librerie.MetodiArray;
import it.uniroma3.sdr.homework.model.Signal;

/**
 * Classe relativa al calcolo del'SNR del segnale letto
 *
 */
public class SNR {
	
	public static double calcolaSNR(Signal signal){
		double SNR = 0;
		double Ps = 0;//energia del segnale
		
		double[] pri_signal = MetodiArray.sommaArray(signal.getImmaginaria(), signal.getReale());
		
		Ps = (double)MetodiArray.sommaElementiArray(MetodiArray.moduloQuadrato(pri_signal))/(double)signal.getLughezza();//calcolo l'energia del segnale
		
		SNR = 10*Math.log10(((double)1)/(double)(1-Ps));//SNR in decibel
		
//		SNR  = Math.sqrt(z-1);
		
		return SNR;
	}

}
