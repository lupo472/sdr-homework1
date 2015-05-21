package it.uniroma3.sdr.homework.signalprocessing;


import java.io.IOException;

import it.uniroma3.sdr.homework.librerie.MetodiArray;
import it.uniroma3.sdr.homework.model.Noise;
import it.uniroma3.sdr.homework.model.Signal;
import javax.swing.*;

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
	
	public static void main(String[] args) throws IOException{
		//carico valori da analizzare da input
		double d = 0.0;
		String numeroSeq = JOptionPane.showInputDialog ( "Digita il numero della sequenza che vuoi analizzare (da 1 a 3)" );
		boolean check = false;
		while(true){
			try{
				d = Double.parseDouble(numeroSeq);
			}
			catch(NumberFormatException e){
				check=true;
			}
			if((d>0 && d<4) && (check==false)){
				break;
			}
			check=false;
			numeroSeq = JOptionPane.showInputDialog ( "Valore inserito errato.\nDigita il numero della sequenza che vuoi analizzare (da 1 a 3)" );
		}
		check = false;
		String numeroOutput = JOptionPane.showInputDialog ( "Digita il numero del segnale che vuoi analizzare (da 1 a 4)" );
		while(true){
			try{
				d = Double.parseDouble(numeroOutput);
			}
			catch(NumberFormatException e){
				check=true;
			}
			if((d>0 && d<5) && (check==false)){
				break;
			}
			check=false;
			numeroOutput = JOptionPane.showInputDialog ( "Valore inserito errato.\nDigita il numero del segnale che vuoi analizzare (da 1 a 4)" );
		}
		//valori corretti inseriti nel path corrispondente
		String path = "Sequenze_SDR_2015/Sequenza_"+numeroSeq+"/output_"+numeroOutput+".dat";
		//lettura sequenza
		System.out.println("Sto leggendo la Sequenza "+numeroSeq+", Output numero "+numeroOutput+"...");
		Signal segnale = FileReading.creaSegnaleDaFile(path);
//		System.out.println("Inizio stampa segnale da file...");
//		System.out.println(segnale.toString());
//		System.out.println("Fine stampa segnale da file.");
		//calcolo soglia
		System.out.println("");
		System.out.println("Calcolo della soglia...");
		Soglia soglia = new Soglia(segnale, 3.1, 10, 13, 0.001);
		double sogliaVal = soglia.determina();
		System.out.println("Valore soglia = "+sogliaVal);
		System.out.println("");
		//confronto soglia, spectrum hole detection
		boolean confrontoSoglia = confronto_soglia(segnale, 3.1, sogliaVal);
		if(confrontoSoglia==true){
			System.out.println("e' presente lo spectrum hole.");
		}
		else{
			System.out.println("NON e' presente lo spectrum hole.");
		}
		ProbabilitaDetection proDet = new ProbabilitaDetection(segnale, 3.1, 10, 18, sogliaVal);
		System.out.println("");
		System.out.println("Calcolo della probabilita' di detection...");
		System.out.println("Probabilita' di detection = "+proDet.determina());
	}

}
