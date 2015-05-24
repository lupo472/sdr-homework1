package it.uniroma3.sdr.homework.signalprocessing;

import java.io.*;
import java.util.*;

import it.uniroma3.sdr.homework.model.*;

/**
 * Classe che si occupa della lettora delle sequenze
 * dei segnali da elaborare, da cui poi forma un oggetto <strong>Signal</strong>
 *
 */
public class FileReading {
	
	/**
	 * Metodo che legge un segnale da file.
	 * @param nomeFile : stringa della locazione in memoria del segnale da leggere
	 * @return m : array di array rappresentante il segnale letto
	 */
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
	
	/**
	 * Metodo che crea un oggetto <strong>Signal</strong> da un array di array di double
	 * letti da file.
	 * @param nomeFile : stringa della locazione in memoria del segnale da leggere
	 * @return s : segnale letto
	 */
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
	
	
}
