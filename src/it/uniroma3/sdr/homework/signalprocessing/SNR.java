package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.librerie.*;
import it.uniroma3.sdr.homework.model.Signal;

public class SNR {
	
	public static double calcolaSNR(Signal signal){
		double[] pri_signal = MetodiArray.sommaArray(signal.getImmaginaria(), signal.getReale());
		double z = 0; 
		for(int i=0; i<pri_signal.length; i++){
			double nAbs = Math.abs(pri_signal[i]);
			double nPot = Math.pow(nAbs, 2);
			z += nPot;
		}
		double ps = z/pri_signal.length; //potenza del segnale;
        return (double)(10*Math.log10(1/(ps-1))); //calcolo l'SNR
	}
	
	public static void main(String[] args){
		String path = "Sequenze_SDR_2015/Sequenza_1/output_3.dat";
		Signal segnale = FileReading.creaSegnaleDaFile(path);
		System.out.println(SNR.calcolaSNR(segnale));
	}

}
