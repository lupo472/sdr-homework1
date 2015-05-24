package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.librerie.*;
import it.uniroma3.sdr.homework.model.Signal;

/**
 * Classe relativa al calcolo del'SNR del segnale letto
 *
 */
public class SNR {
	public static double calcolaSNR(Signal signal){
		double[] pri_signal = MetodiArray.sommaArray(signal.getImmaginaria(), signal.getReale());
		double[] modulesPow = MetodiArray.moduloQuadrato(pri_signal);
		double z = MetodiArray.sommaElementiArray(modulesPow);
		double ps = z/pri_signal.length; //potenza del segnale;
        return (double)(10*Math.log10(1/(ps-1))); //calcolo l'SNR
	}
	
//	public static void main(String[] args){
//		String path = "Sequenze_SDR_2015/Sequenza_2/output_2.dat";
//		Signal segnale = FileReading.creaSegnaleDaFile(path);
//		System.out.println(SNR.calcolaSNR(segnale));
//	}

}
