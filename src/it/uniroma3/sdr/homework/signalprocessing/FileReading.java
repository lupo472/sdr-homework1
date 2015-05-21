package it.uniroma3.sdr.homework.signalprocessing;

import java.io.*;
import java.util.*;

public class FileReading {
	public static double[][] fileReader(String nomeFile){
		Scanner scanner = null;
		
		//creo l'array di array;
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
            numRighe++;
        }
        scanner.close();
        double[][] m = new double[numRighe][2];
		System.out.println(numRighe);
        //riempio l'array di array;
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
	
	public static void main(String[] args) throws IOException{
		double[][] m = fileReader("Sequenze_SDR_2015/Sequenza_1/output_1.dat");
		for(int i=0; i<m.length; i++){
			System.out.println(m[i][0]+" - "+m[i][1]);
		}
	}
}
