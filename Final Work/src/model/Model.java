package model;

import java.util.ArrayList;

public class Model {

	Stack<ComplexNumber> stack;
	ArrayList<UserOperation> stackOperations;
	public Model() {
		stack= new Stack<ComplexNumber>();
		stackOperations= new ArrayList<UserOperation>();
		inizializate();
	}
	
	

	public Stack<ComplexNumber> getStack(){
		return this.stack;
	}
	
	
	
	public ArrayList<UserOperation> getStackOperations() {
		return stackOperations;
	}



	private ComplexNumber a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
	
	///cont
	
	String pant="b";
	public void setVariable(String letter,ComplexNumber cn) {
		switch(letter) {
			case "a":
				this.a=cn;
				break;
			case "b":
				this.b=cn;
				break;
			case "c":
				this.c=cn;
				break;
			case "d":
				this.d=cn;
				break;
			case "e":
				this.e=cn;
				break;
			case "f":
				this.f=cn;
				break;
			case "g":
				this.g=cn;
				break;
			case "h":
				this.h=cn;
				break;
			case "i":
				this.i=cn;
				break;
			case "j":
				this.j=cn;
				break;
			case "k":
				this.k=cn;
				break;
			case "l":
				this.l=cn;
				break;
			case "m":
				this.m=cn;
				break;
			case "n":
				this.n=cn;
				break;
			case "o":
				this.o=cn;
				break;
			case "p":
				this.p=cn;
				break;
			case "q":
				this.q=cn;
				break;
			case "r":
				this.r=cn;
				break;
			case "s":
				this.s=cn;
				break;
			case "t":
				this.t=cn;
				break;
			case "u":
				this.u=cn;
				break;
			case "v":
				this.v=cn;
				break;
			case "w":
				this.w=cn;
				break;
			case "x":
				this.x=cn;
				break;
			case "y":
				this.y=cn;
				break;
			case "z":
				this.z=cn;
				break;
		}
		
	}
	public ComplexNumber getVariable(String letter) {
		switch(letter) {
			case "a":
				return a;
			case "b":
				return b;
			case "c":
				return c;
			case "d":
				return d;
			case "e":
				return e;
			case "f":
				return f;
			case "g":
				return g;
			case "h":
				return h;
			case "i":
				return i;
			case "j":
				return j;
			case "k":
				return k;
			case "l":
				return l;
			case "m":
				return m;
			case "n":
				return n;
			case "o":
				return o;
			case "p":
				return p;
			case "q":
				return q;
			case "r":
				return r;
			case "s":
				return s;
			case "t":
				return t;
			case "u":
				return u;
			case "v":
				return v;
			case "w":
				return w;
			case "x":
				return x;
			case "y":
				return y;
			case "z":
				return z;
		}
		return null;
		
	}
	
	
	private void inizializate() {
		a=new ComplexNumber(0,0);
		b=new ComplexNumber(0,0);
		c=new ComplexNumber(0,0);
		d=new ComplexNumber(0,0);
		e=new ComplexNumber(0,0);
		f=new ComplexNumber(0,0);
		g=new ComplexNumber(0,0);
		h=new ComplexNumber(0,0);
		i=new ComplexNumber(0,0);
		j=new ComplexNumber(0,0);
		k=new ComplexNumber(0,0);
		l=new ComplexNumber(0,0);
		m=new ComplexNumber(0,0);
		n=new ComplexNumber(0,0);
		o=new ComplexNumber(0,0);
		p=new ComplexNumber(0,0);
		q=new ComplexNumber(0,0);
		r=new ComplexNumber(0,0);
		s=new ComplexNumber(0,0);
		t=new ComplexNumber(0,0);
		u=new ComplexNumber(0,0);
		v=new ComplexNumber(0,0);
		w=new ComplexNumber(0,0);
		x=new ComplexNumber(0,0);
		y=new ComplexNumber(0,0);
		z=new ComplexNumber(0,0);		
	}
	
}
