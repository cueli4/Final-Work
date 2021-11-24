package model;

import java.util.Iterator;

public class ComplexNumber {
	double real;
	double imaginary;
	
	//Constructors
	public ComplexNumber(double real, double imaginary) {
		this.real=real;
		this.imaginary=imaginary;
	}
	
	public ComplexNumber(String numberString) {
		if(numberString.contains("i")) {
			//2i-2 => 2i + -2
			//split (+)
			String addAdd = numberString;
			for (int i = 1; i < numberString.length(); i++) {
				if(numberString.charAt(i)=='-') {
					addAdd=numberString.substring(0, i)+"+-"+numberString.substring(i+1, numberString.length());
				}
			}
			numberString=addAdd;
			
			//split
			String[] split = null;
			split=numberString.split("\\+");
			
			if(split[0].contains("i")) { //5i+2 //-5i+2 //5i-2 
				String imaginaryString=split[0].substring(0, split[0].length()-1);
				this.imaginary=Double.parseDouble(imaginaryString);
				this.real=Double.parseDouble(split[1]);
			}
			else{
				if(split[1].contains("i")) { //5+2i //-2+3i //5-2i
					String imaginaryString=split[1].substring(0, split[1].length()-1);
					this.imaginary=Double.parseDouble(imaginaryString);
					this.real=Double.parseDouble(split[0]);
					}	
				}
			
		}
		else { //basecase
			this.imaginary=0;
			this.real=Double.parseDouble(numberString);
		}
	}
	
	//Getters and Setters
	public double getReal() {
		return this.real;
	}
	public double getImaginary() {
		return this.imaginary;
	}
	public String getStringNumber() {
		if(this.imaginary>=0) return this.real+"+"+this.imaginary+"i";
		else return this.real+""+this.imaginary+"i";
	}
	
}