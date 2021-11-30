package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.Stack;

import model.ComplexNumber;
import model.Model;
import view.NumbersPanel;
import view.OperationsPanel;
import view.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controller {

	private final Model model;
	private final View view;
	
	
	public Controller(Model m, View v) {
		this.model=m;
		this.view=v;
		initController();
	}
	
	
	public void initController() {
		
		view.getBtnEnter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enterNumberStack();
				view.getTextField().requestFocus();
			}
		});
		view.getPantalla().getCalculo().addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent arg0) {
        		if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
        			enterNumberStack();
        			view.getTextField().requestFocus();
        	}
        });
		
		view.addWindowListener (new CalculadoraControladorWindowListener());
        view.setActionListener (new CalculadoraControladorActionListener());
		
	}

	private void updateVariableTable(String va) {
		
        for(int i=0;i<26;i++) {
        	if((""+view.getTable().getValueAt(i, 0)).equals(va)) {
        		view.getTable().setValueAt(model.getVariable(va).getStringNumber(), i, 1);
        	}		
        }
        
	}
	
	private void enterNumberStack() { 
		String txScreen=view.getTextFieldString();
		if(txScreen.length()==2 && Character.isLetter(txScreen.charAt(1))) {//Operations with variables
			String op=""+txScreen.charAt(0);
			String va=""+txScreen.charAt(1);
			
			switch(op) {
				case "+":
					// variable + pila
					ComplexNumber res1=addSubstractOperation(model.getStack().getElement(model.getStack().top), model.getVariable(va), "+");
					model.setVariable(va,res1);
					updateVariableTable(va);
					//updateStackGraphically();
					view.getTextField().setText("");
					break;
				case "-":
					// variable - pila
					ComplexNumber res2=addSubstractOperation(model.getStack().getElement(model.getStack().top), model.getVariable(va), "-");
					model.setVariable(va,res2);
					updateVariableTable(va);
					//updateStackGraphically();
					view.getTextField().setText("");
					break;
				case ">":
					model.setVariable(va, model.getStack().getElement(model.getStack().top));
					updateVariableTable(va);
					updateStackGraphically();
					view.getTextField().setText("");
					break;
				case "<":
					model.getStack().add(model.getVariable(va));
					updateVariableTable(va);
					updateStackGraphically();
					view.getTextField().setText("");
					break;
			}
		}
		else { //ComplexNumber
			ComplexNumber cn=new ComplexNumber(view.getTextFieldString());
			model.getStack().add(cn);
			System.out.println(cn.getReal()+" imagi="+cn.getImaginary());
			System.out.println(model.getStack().top().getReal()+" imagi="+model.getStack().top().getImaginary());
			updateStackGraphically();
			view.getTextField().setText("");
		}
		
	}

	public void multiply(ComplexNumber a, ComplexNumber b) {
		double newReal = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
        double newImag = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
        ComplexNumber result= new ComplexNumber(newReal, newImag);
        model.getStack().add(result);
		updateStackGraphically();
	}
	
	public void div(ComplexNumber a, ComplexNumber b) {
		
		double real = a.getReal();
		double real2 = b.getReal();
		double image = a.getImaginary();
		double image2 = a.getImaginary();
		
		double newReal = (real*real2 + image*image2)/(real2*real2 + image2*image2);
		double newImage = (image*real2 - real*image2)/(real2*real2 + image2*image2);
		ComplexNumber result = new ComplexNumber(newReal,newImage);
		model.getStack().add(result);
		updateStackGraphically();
	} 
	
	 public void conjugate(ComplexNumber a){
		 ComplexNumber result = new ComplexNumber(a.getReal(), -a.getImaginary());
		 model.getStack().add(result);
		 updateStackGraphically();
	  }
	 
	 public ComplexNumber addSubstractOperation(ComplexNumber ComplexNumber1, ComplexNumber ComplexNumber2, String op){
	        
	        switch (op){
	            
	           case "+":
	        	   ComplexNumber result = new ComplexNumber((ComplexNumber1.getReal()+ComplexNumber2.getReal()),(ComplexNumber1.getImaginary()+ComplexNumber2.getImaginary()));
	        	   //model.getStack().add(result);
	        	   return result;
	        	  
	           case "-":
	        	   ComplexNumber result1 = new ComplexNumber((ComplexNumber1.getReal()-ComplexNumber2.getReal()),(ComplexNumber1.getImaginary()-ComplexNumber2.getImaginary()));
	        	   //model.getStack().add(result1);
	        	   return result1;
	        	 
	        }
	        return null;
	        
	    }
	    
	    public void squareRootOperation(ComplexNumber ComplexNumber){
	        double mod = Math.sqrt(Math.pow(ComplexNumber.getReal(),2))+(Math.pow(ComplexNumber.getImaginary(),2));
	        ComplexNumber result = new ComplexNumber(mod*(Math.cos(ComplexNumber.getReal())),mod*(Math.sin(ComplexNumber.getImaginary())));
	        model.getStack().add(result);
	        updateStackGraphically();
	    }
	    public void updateStackGraphically() {
	    	view.getPanelStack().removeAll();
	    	ComplexNumber cn;
	    	if(!model.getStack().isEmpty())
		    	for (int i = 0; i <= model.getStack().top; i++) {
		    		cn=(ComplexNumber)model.getStack().getElement(i);
		    		stackGraphically(cn.getStringNumber());
				}
	    	else {
	    		view.getContentPane().validate(); 
				view.getContentPane().repaint();
	    	}
	    }
	    public void stackGraphically(String text) {
	    	
			JTextField newComplexStacked = new JTextField();;
			newComplexStacked.setEditable(false);
			newComplexStacked.setHorizontalAlignment(SwingConstants.CENTER);
			newComplexStacked.setText(text);
			newComplexStacked.setSelectedTextColor(new Color(255, 255, 255));
			view.getPanelStack().add(newComplexStacked,0);
			view.getContentPane().validate(); 
			view.getContentPane().repaint();
		}
	    
	    //NEW
	    
	    
	    

	    class CalculadoraControladorWindowListener extends WindowAdapter
	    {
	       
	        @Override
	        public void windowClosing (WindowEvent e)
	        {
	            System.out.println ("calculadora :: Cerrar ventana.");
	            System.exit(0);
	        }
	    }
	    
	     class CalculadoraControladorActionListener implements ActionListener
	    {
	       
	        @Override
	        public void actionPerformed (ActionEvent ae)
	        {
	            String command = ae.getActionCommand();
	            int top = model.getStack().top;
	            switch (command)
	            {
	                case "0":
	                	view.getTextField().setText(view.getTextField().getText()+"0");
	                	break;
	                case "1":
	                	view.getTextField().setText(view.getTextField().getText()+"1");
	                	break;
	                case "2":
	                	view.getTextField().setText(view.getTextField().getText()+"2");
	                	break;
	                case "3":
	                	view.getTextField().setText(view.getTextField().getText()+"3");
	                	break;
	                case "4":
	                	view.getTextField().setText(view.getTextField().getText()+"4");
	                	break;
	                case "5":
	                	view.getTextField().setText(view.getTextField().getText()+"5");
	                	break;
	                case "6":
	                	view.getTextField().setText(view.getTextField().getText()+"6");
	                	break;
	                case "7":
	                	view.getTextField().setText(view.getTextField().getText()+"7");
	                	break;
	                case "8":
	                	view.getTextField().setText(view.getTextField().getText()+"8");
	                	break;
	                case "9":
	                	view.getTextField().setText(view.getTextField().getText()+"9");
	                	break;
	                case ".":
	                	view.getTextField().setText(view.getTextField().getText()+".");
	                	break;
	                case "C":
	                	view.getTextField().setText("");
	                	break;
	                case "delete":
	                	String str=view.getTextFieldString();
	                	view.getTextField().setText(str.substring(0,str.length()-1));
	                	break;
	                
	                case "+":
	                	model.getStack().add(addSubstractOperation(model.getStack().getElement(top-1),model.getStack().getElement(top),"+" ));
	                	updateStackGraphically();
	                	break;
	                    
	                case "-":
	                	model.getStack().add(addSubstractOperation(model.getStack().getElement(top-1),model.getStack().getElement(top),"-" ));
	                	updateStackGraphically();
	                	break;    
	                    
	                case "*":
	                	multiply(model.getStack().getElement(top-1),model.getStack().getElement(top));
	                    break;
	                    
	                case "/":
	                	div(model.getStack().getElement(top-1),model.getStack().getElement(top));
	                    break;
	                    
	                case "sqrt":
	                	squareRootOperation(model.getStack().getElement(top));	                    
	                    break;
	                    
	                case "conj":
	                	conjugate(model.getStack().getElement(top));
	                	break;
	                	
	                case "CLEAR":
	                	if(!model.getStack().isEmpty()) {
	                		for(int i=0; i<=top;i++)
	                			model.getStack().remove();
	                	}
	                	updateStackGraphically();
	                	break;
	                	
	                case "DUP":
	                	model.getStack().add(model.getStack().top());
	                	updateStackGraphically();
	                	break;
	                	
	                case "OVER":
	                	model.getStack().add(model.getStack().getElement(top-1));
	                	updateStackGraphically();
	                	break;
	                	
	                case "DROP":
	                	model.getStack().remove();
	                	updateStackGraphically();
	                	break;
	                
	                case "SWAP":
	                	ComplexNumber ult=model.getStack().remove();
	                	ComplexNumber pnult=model.getStack().remove();
	                	model.getStack().add(ult);
	                	model.getStack().add(pnult);
	                	
	                	updateStackGraphically();
	                	break;
	                default:
	                    System.out.println ("CalculadoraController :: Not < " + command + " > no reconocido.");
	                    break;
	            }
	        }
	     }
	}
