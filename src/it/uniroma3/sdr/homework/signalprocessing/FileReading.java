package it.uniroma3.sdr.homework.signalprocessing;

import java.io.*;
import java.util.*;

import it.uniroma3.sdr.homework.model.*;

public class FileReading {
	public static double[][] fileReader(String nomeFile){
		Scanner scanner = null;
		
		//creo l'array di array m;
		try{
			scanner = new Scanner(new File(nomeFile));
		}
		catch(Exception e){
			System.out.println("file non trovato ");
			return null;
		}
		int numRighe = 0;
        while (scanner.hasNext()) {
            scanner.next();
            scanner.next();
            numRighe++;
        }
        double[][] m = new double[numRighe][2];
        scanner.close();
        
        //inserisco i valori nell'array di array m;
        Scanner scanner2 = null;
		try{
			scanner2 = new Scanner(new File(nomeFile));
		}
		catch(Exception e){
			System.out.println("file non trovato ");
		}
		int i=0;
		while (scanner2.hasNext()) {
			String s1 = scanner2.next();
			try{
				double double1 = Double.parseDouble(s1);
				m[i][0]=double1;
			}
			catch(NumberFormatException e){
				System.out.println("errore");
			}
			String s2 = scanner2.next();
			try{
				double double2 = Double.parseDouble(s2);
				m[i][1]=double2;
			}
			catch(NumberFormatException e){
				System.out.println("errore");
			}
			i++;
		}
        scanner2.close();
        return m;
	}
	
	public static Signal creaSegnaleDaFile(String nomeFile){
		double[][] m = fileReader(nomeFile);
		Signal s = new Signal(m.length);
		double[] reale = new double[m.length];
		double[] immaginaria = new double[m.length];
		for(int i=0; i<m.length; i++){
			reale[i]=m[i][0];
			immaginaria[i]=m[i][1];
		}
		s.setReale(reale);
		s.setImmaginaria(immaginaria);
		return s;
	}
	
//	public static void main(String[] args) throws IOException{
//		double[][] m = fileReader("Sequenze_SDR_2015/Sequenza_1/output_3.dat");
//		//stampa di m;
//		for(int i=0; i<m.length; i++){
//			System.out.println("[Re: "+m[i][0]+", Im: "+m[i][1]+"]");
//		}	
//		
//		Signal segnale = FileReading.creaSegnaleDaFile("Sequenze_SDR_2015/Sequenza_1/output_3.dat");
//		for(int i=0; i<m.length; i++){
//			System.out.println("[Re: "+segnale.getReale()[i]+", Im: "+segnale.getImmaginaria()[i]+"]");
//		}	
//		System.out.println(segnale.toString());
//
//	}
	
}
