package it.uniroma3.sdr.homework.signalprocessing;


import it.uniroma3.sdr.homework.librerie.MetodiArray;
import it.uniroma3.sdr.homework.model.Noise;
import it.uniroma3.sdr.homework.model.Signal;

/**
 * Classe realtiva all'elaborazione di un segnale.
 */
public class SignalProcessor {
	
	/**
	 * Metodo di confronto dell'energia di un segnale con la soglia energetica per rilevare la presenza di
	 * Primary Users nel mezzo di comunicazione.
	 * 
	 * @param signalIN : segnale in esame
	 * @param snr : rapporto segnale/rumore al momento della ricezione del segnale
	 * @param soglia : soglia calcolata con il metodo <strong>determina()</strong> della classe <strong>Soglia</strong>
	 */
	public static boolean confronto_soglia(Signal signalIN, double snr, double soglia){
		
		Noise noise = new Noise(snr,signalIN.getLughezza());
		boolean hole;//presenza o meno di uno spectrum hole
		double z = 0;//somma delle energie
		
		double[] pri_noise = MetodiArray.sommaArray(noise.getParteImmaginaria(), noise.getParteReale());//somma parte reale e immaginaria del rumore
		double[] pri_sign = MetodiArray.sommaArray(signalIN.getReale(), signalIN.getImmaginaria());//somma parte reale e immaginaria del segnale
		
		double[] sign_noise = MetodiArray.sommaArray(pri_sign, pri_noise);//somma del segnale e rumore
		z = MetodiArray.sommaElementiArray(MetodiArray.moduloQuadrato(sign_noise));
		
		//controllo presenza spectrum hole
		if(z > soglia)
			hole = false;
		else
			hole = true;
		
//		Noise noise = new Noise(snr,signalIN.getLughezza());
//		Signal signal_noise = new Signal(signalIN.getLughezza());//segnale risultante dalla somma del segnale in studio e il rumore
//		
//		boolean hole = false;//spectrum hole
//		
//		double[] valoriRealiSignalNoise = new double[signalIN.getLughezza()];//array di supporto per l'aggiunta dei valore della somma del segnale e rumore
//		double[] valoriImmaginariSignalNoise = new double[signalIN.getLughezza()];// //  //  //
//		
//		double z = 0;//energia del segnale finale
//		double pri_signal_noise = 0;//somma parte reale e immaginaria del segnale finale
//	
//		//ciclo for per la somma del segnale in ingresso e rumore in unico segnale
//		for(int i = 0;i<signalIN.getLength();i++){
//			double parteReale = signalIN.getReale()[i] + noise.getParteReale()[i];
//			double parteImmaginaria = signalIN.getImmaginaria()[i] + noise.getParteImmaginaria()[i]; 
//			valoriRealiSignalNoise[i] = parteReale;
//			valoriImmaginariSignalNoise[i] = parteImmaginaria;
//		}
//		signal_noise.setReale(valoriRealiSignalNoise);
//		signal_noise.setImmaginaria(valoriImmaginariSignalNoise);
//		
//		//calcolo dell'energia
//		for(int i = 0;i<signalIN.getLughezza();i++){
//			pri_signal_noise = signal_noise.getReale()[i] + signal_noise.getImmaginaria()[i];
//			z += Math.pow(Math.abs(pri_signal_noise), 2);
//		}
//		
//		//controllo della soglia
//		if(z>soglia)
//			hole = false;
//		else
//			hole = true;
//		
	return hole;
	}

}
