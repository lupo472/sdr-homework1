package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.librerie.MetodiArray;
import it.uniroma3.sdr.homework.model.Signal;

public class SNR {
	
	public static double calcolaSNR(Signal signal){
		double SNR = 0;
		double z = 0;//energia del segnale
		
		double[] pri_signal = MetodiArray.sommaArray(signal.getImmaginaria(), signal.getReale());
		
		z = MetodiArray.sommaElementiArray(MetodiArray.moduloQuadrato(pri_signal));//calcolo l'energia del segnale
		SNR = 10*Math.log10(1/(z-1));
		
		return SNR;
	}

}
