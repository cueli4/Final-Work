package model;

import java.util.ArrayList;

public class Stack<E> {

	protected E stack[];
	public int top;
	protected static final int INITIAL_CAPACITY=20;
	
	public Stack() {
		stack=(E[]) new Object[INITIAL_CAPACITY];
		top=-1;
	}
	public E getElement(int position) {
		return stack[position];
	}
	
	public void add(E x) {
		if(top+1==stack.length) duplicateArray();
		top++; stack[top]=x;
	}
	
	public E remove() {
		E elUltimo=stack[top];
		top--;
		return elUltimo;
	}
	
	public E top() {
		return stack[top];
	}
	
	
	public boolean isEmpty() {
		
		return (top==-1);
	}
	
	public String toString() {
		String res="";
		for(int i=0; i<stack.length;i++) {
			res+=stack[i];
		}
		return res;
	}
	
	private void duplicateArray() {
		E nuevoArray[]=(E[]) new Object[stack.length*2];
		for(int i=0; i<=top;i++) {
			nuevoArray[i]=stack[i];
		}
		stack=nuevoArray;
	}
}
