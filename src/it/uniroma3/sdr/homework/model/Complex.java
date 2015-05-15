package it.uniroma3.sdr.homework.model;
public class Complex {
	private double pRe;
	private double pImm;

	/**COSTRUTTORE**/
	public Complex(double pRe, double pImm){
		this.pRe=pRe;
		this.pImm=pImm;
	}

	/**GETTER & SETTER**/
	public double getpImm() {
		return pImm;
	}

	public void setpImm(double pImm) {
		this.pImm = pImm;
	}

	public double getpRe() {
		return pRe;
	}

	public void setpRe(double pRe) {
		this.pRe = pRe;
	}

	@Override
	public String toString() {
		return "Complex [pRe=" + pRe + ", pImm=" + pImm + "]";
	}

	/** OPERAZIONI SUI COMPLESSI **/

	public Complex coniugato(){
		return new Complex(this.pRe, - this.pImm);
	}

	public double abs(){
		return Math.hypot(this.pRe, this.pImm);

		//alternativa: Math.sqrt(Math.pow(this.reale,2) + Math.pow(this.immaginario,2));

	}
	public static Complex somma(Complex c1, Complex c2){
		double paRe = c1.getpRe() + c2.getpRe();
		double paImm = c1.getpImm() + c2.getpImm();
		return new Complex(paRe,paImm);
	}
	
	public static Complex differenza(Complex c1, Complex c2){
		double paRe = c1.getpRe() - c2.getpRe();
		double paImm = c1.getpImm() -c2.getpImm();
		return new Complex(paRe,paImm);
	}

	public static Complex prodotto(Complex c1, Complex c2){
		double paRe = c1.getpRe()*c2.getpRe() - c1.getpImm()*c2.getpImm();
		double paImm = c1.getpRe()*c2.getpImm() + c1.getpImm()*c2.getpImm();
		return new Complex(paRe,paImm);
	}

	public static Complex rapporto(Complex c1, Complex c2){
		double paRe = ((c1.getpRe()*c2.getpRe())-(c1.getpImm()*c2.getpImm()))/(Math.pow(c2.getpRe(),2)+Math.pow(c2.getpImm(),2));
		double paImm = ((c2.getpRe()*c1.getpImm())+(c1.getpRe()*c2.getpImm()))/(Math.pow(c2.getpRe(),2)+Math.pow(c2.getpImm(),2));
		return new Complex(paRe,paImm);
	}

	public Complex prodottoScalare(double s){
		return new Complex(this.pRe*s, this.pImm*s);
	}


	public Complex reciproco(){
		double scalare = Math.pow(this.pRe,2) + Math.pow(this.pImm,2);
		Complex result = new Complex(this.pRe/scalare , - (this.pImm/scalare));
		return result;
	}


	@Override
	public boolean equals(Object o) {

		Complex c = (Complex)o;

		if(this.getpRe() == c.getpRe() && this.getpImm() == c.getpImm())
			return true;
		else 
			return false;

	}	 
}
