package it.uniroma3.sdr.homework.signalprocessing;

import it.uniroma3.sdr.homework.model.*;
import it.uniroma3.sdr.homework.librerie.*;
/**
 * Classe relativa al calcolo della probabilità di detection ricavata dopo il calcolo 
 * della soglia e l'analisi del segnale
 */
public class ProbabilitaDetection extends EnergyDetector {

	private double soglia;

	public ProbabilitaDetection(Signal signal, double SNR,int num_prove, int num_blocchi,double soglia){
		super(signal, SNR, num_prove, num_blocchi);
		this.soglia = soglia;
	}


	public double determina(){
		double Pd = 0; //probabilità di detection
		double[] z = new double[this.getNum_prove()];//array energia
		int cont = 0; //variabile contatore

		for(int i = 0;i<this.getNum_prove();i++){
			double[][] noise_blocks = new double[this.getNum_blocchi()][this.getSignal().getLughezza()];//array di array che conterrà come righe la somma degli array di parteImmaginaria e reale di  rumori
			noise_blocks = popolaBlocchiNoise(noise_blocks);

			double sign_mod = 0;//sommatoria dei moduli quadrati dei blocchi di rumore

			for(int j = 0;j<this.getNum_blocchi();j++){
				double[] sign_noise = MetodiArray.sommaArray(MetodiArray.sommaArray(this.getSignal().getImmaginaria(), this.getSignal().getReale()), noise_blocks[j]);//somma tra il segnale e un blocco di rumore
				double detector_out = MetodiArray.sommaElementiArray(MetodiArray.moduloQuadrato(sign_noise));//modulo quadrato del blocco rumore
				sign_mod = sign_mod + detector_out;
			}

			z[i] = sign_mod/this.getNum_blocchi();//calcolo dell'elemento i dell'array delle energie delle prove
			
			if(z[i] > soglia)
				cont++;

		}

		Pd = cont/this.getNum_prove();
		return Pd;
	}
	
	
	/** GETTER & SETTER **/

	public double getSoglia() {
		return soglia;
	}


	public void setSoglia(double soglia) {
		this.soglia = soglia;
	}



}
