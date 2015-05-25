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
		
	return hole;
	}
	
	/*
	 * Main che contiene parte grafica di richiesta di parametri e inizializzazione dei calcoli
	 */
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
		System.out.println("Lettura avvenuta con successo. ");
		
		//stampa sequenza
//		System.out.println("Inizio stampa segnale da file...");
//		System.out.println(segnale.toString());
//		System.out.println("Fine stampa segnale da file.");

//        JFrame testFrame = new JFrame();
//        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        SignalGraph graph = new SignalGraph(segnale);
//        testFrame.add(graph);
//        testFrame.setBounds(100, 100, 764, 470);
//        testFrame.setVisible(true);
		
		//calcolo soglia
        //inserisco da input il numero di prove da effettuare
		String numeroProve = JOptionPane.showInputDialog ( "Digita il numero di prove che vuoi effettuare per il calcolo della soglia" );
		int nP = 0;//numero delle prove
		check=false;
		while(true){
			try{
				nP = Integer.parseInt(numeroProve);
			}
			catch(NumberFormatException e){
				check=true;
			}
			if((nP>-1) && (check==false)){
				break;
			}
			check=false;
			numeroProve = JOptionPane.showInputDialog ( "Valore inserito errato.\nDigita il numero di prove che vuoi effettuare per il calcolo della soglia" );
		}
		String numeroBlocchi = JOptionPane.showInputDialog ( "Digita il numero di blocchi per il calcolo della soglia" );
		int nB = 0;
		check=false;
		while(true){
			try{
				nB = Integer.parseInt(numeroBlocchi);
			}
			catch(NumberFormatException e){
				check=true;
			}
			if((nB>-1) && (check==false)){
				break;
			}
			check=false;
			numeroBlocchi = JOptionPane.showInputDialog ( "Valore inserito errato.\nDigita il numero di blocchi per il calcolo della soglia" );
		}
		String valPfa = JOptionPane.showInputDialog ( "Digita il valore della PFA per il calcolo della soglia (compreso tra 0 e 1)" );
		double nPfa = 0;
		check=false;
		while(true){
			try{
				nPfa = Double.parseDouble(valPfa);
			}
			catch(NumberFormatException e){
				check=true;
			}
			if((nPfa>=0.0 && nPfa<=1.0) && (check==false)){
				break;
			}
			check=false;
			valPfa = JOptionPane.showInputDialog ( "Valore inserito errato.\nDigita il valore della PFA per il calcolo della soglia (compreso tra 0 e 1)" );
		}
		double Snr = SNR.calcolaSNR(segnale);
		Soglia soglia = new Soglia(segnale, Snr, nP, nB, nPfa);
		System.out.println("");
		System.out.print("Numero prove = "+nP+", ");
		System.out.print("numero blocchi = "+nB+", ");
		System.out.print("PFA = "+nPfa+", ");
		System.out.print("SNR = "+Snr+".\n");
		System.out.println("Sto calcolando la soglia...");
		double sogliaVal = soglia.determina();
		System.out.println("Valore soglia = "+sogliaVal);
		System.out.println("");
		
		//confronto soglia, spectrum hole detection
		boolean confrontoSoglia = confronto_soglia(segnale,Snr, sogliaVal);
		if(confrontoSoglia==true){
			System.out.println("E' presente lo spectrum hole.");
		}
		else{
			System.out.println("NON e' presente lo spectrum hole.");
		}
		ProbabilitaDetection proDet = new ProbabilitaDetection(segnale, Snr, nP, nB, sogliaVal);
		System.out.println("");
		System.out.println("Calcolo della probabilita' di detection...");
		System.out.println("Probabilita' di detection = "+proDet.determina());
	}

}
