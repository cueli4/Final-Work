package model;

public class Model {

	Stack<ComplexNumber> stack;
	public Model() {
		stack= new Stack<ComplexNumber>();
	}
	
	public Stack<ComplexNumber> getStack(){
		return this.stack;
	}
	
}
