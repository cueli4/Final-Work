package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.Stack;
import model.UserOperation;
import model.ComplexNumber;
import model.Model;
import view.NumbersPanel;
import view.OperationsPanel;
import view.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller {

	private final Model model;
	private final View view;
	private int lastSelectedKey;
	DefaultTableModel modelo;
	
	
	public Controller(Model m, View v) {
		this.model=m;
		this.view=v;
		lastSelectedKey=-1;
		initController();
		modelo = (DefaultTableModel) view.getTableOperations().getModel();
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
		view.setActionListener (new CalculadoraControladorActionListener(), new OperationsDefinedControladorActionListener());
		
	}

	private void updateVariableTable(String va) {
		
        for(int i=0;i<26;i++) {
        	if((""+view.getTable().getValueAt(i, 0)).equals(va)) {
        		view.getTable().setValueAt(model.getVariable(va).getStringNumber(), i, 1);
        		
        	}		
        }
        
	}
	

	//Cada vez que se define una nueva operacion hay que crear una nueva fila
	
	private void updateTableOperaions() {
		for(int i=1; i<view.getTableOperations().getRowCount();i++) {
			view.getTableOperations().setValueAt(model.getStackOperations().get(i-1).getNameOperation(), i, 0);
		}
	}
	
	private void deleteOperation() {
		this.lastSelectedKey=view.getTableOperations().getSelectedRow();
		System.out.println(lastSelectedKey);
		if(lastSelectedKey!=-1 && lastSelectedKey!=0) {
			model.getStackOperations().remove(lastSelectedKey-1);
			modelo.removeRow(modelo.getRowCount()-1);
			updateTableOperaions();
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
	    
		public double modOperation(ComplexNumber cn) {
			double result =Math.sqrt(cn.getReal()*cn.getReal()+cn.getImaginary()*cn.getImaginary());
			
			return result;

		}
		
		public double argOperation(ComplexNumber cn) {
			double angulo=Math.atan2(cn.getImaginary(), cn.getReal());     //devuelve el ángulo entre -PI y +PI
		     if(angulo<0)  angulo=2*Math.PI+angulo;
		     double result=angulo*180/Math.PI;
		     //model.getStack().add(new ComplexNumber(result,0.0));
		     return result;
		}
		
		
		public void powOperation(ComplexNumber cn, int exponente) {
				double x=0.0,y=0.0;
				int signo;
				for(int i =0; i<=exponente;i++) {
					signo=(i%2==0)?+1:-1;
					//Real
					x+=combinatorio(exponente, 2*i)*potencia(cn.getReal(), exponente-2*i)*potencia(cn.getImaginary(), 2*i)*signo;
					if(exponente==2*i)  break;
					//Imaginaty
					y+=combinatorio(exponente, 2*i+1)*potencia(cn.getReal(), exponente-(2*i+1))*potencia(cn.getImaginary(), 2*i+1)*signo;
				}
				model.getStack().add(new ComplexNumber(x, y));
		}
		// auxiliary function for the power of a complex number 
		private static double combinatorio(int m, int n){
		    long num=1;
		    long den=1;
		    for(int i=m; i>m-n; i--){
		        num*=i;
		    }
		    for(int i=2; i<=n; i++){
		        den*=i;
		    }
		    return (double)num/den;
		  }
		// auxiliary function for the power of a complex number 
		 private static double potencia(double base, int exponente){
			    double resultado=1.0;
			    for(int i=0; i<exponente; i++){
			        resultado*=base;
			    }
			    return resultado;
			  }
		
		 
		public void expOperation(ComplexNumber cn) {
			double x=Math.cos(cn.getImaginary())*Math.exp(cn.getReal());
		    double y=Math.sin(cn.getImaginary())*Math.exp(cn.getReal());
		    model.getStack().add(new ComplexNumber(x, y));
		}
		
		public void logOperation(ComplexNumber cn) {
			double x= Math.log(modOperation(cn));
			double y=argOperation(cn);
		    model.getStack().add(new ComplexNumber(x, y));
			
		}
		
		public ComplexNumber sinOperation(ComplexNumber cn) {
			double real=Math.cosh(cn.getImaginary())*Math.sin(cn.getReal());
			double imaginary=Math.cos(cn.getReal())*Math.sinh(cn.getImaginary());
			ComplexNumber result=new ComplexNumber(real, imaginary);
			//model.getStack().add(result);
			return result;
			
		}
		
		public ComplexNumber cosOperation(ComplexNumber cn) {
			double real=Math.cosh(cn.getImaginary())*Math.cos(cn.getReal());
			double imaginary=Math.sin(cn.getReal())*Math.sinh(cn.getImaginary());
			ComplexNumber result=new ComplexNumber(real, imaginary);
			//model.getStack().add(result);
			return result;
		}
		
		public void tanOperation(ComplexNumber cn) {
			model.getStack().add(divAux(sinOperation(cn), cosOperation(cn)));
		}
		
		//Ya esta implementada esto no se copia
	public ComplexNumber divAux(ComplexNumber a, ComplexNumber b) {
			
			double real = a.getReal();
			double real2 = b.getReal();
			double image = a.getImaginary();
			double image2 = a.getImaginary();
			
			double newReal = (real*real2 + image*image2)/(real2*real2 + image2*image2);
			double newImage = (image*real2 - real*image2)/(real2*real2 + image2*image2);
			ComplexNumber result = new ComplexNumber(newReal,newImage);
			return result;
			
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
	    
	    class OperationsDefinedControladorActionListener implements ActionListener
	    {
	    	@Override
	        public void actionPerformed (ActionEvent ae)
	        {
	    		String command = ae.getActionCommand();
	    		
	            switch (command) {
	            	case "new":
	            		String name=JOptionPane.showInputDialog(view, "Enter a name for the operation ");
	            		String operations= JOptionPane.showInputDialog(view, "Enter the series of operations that the defined operation will do ");
	            		UserOperation uo=new UserOperation(name, operations);
	            		model.getStackOperations().add(uo);
	            		//crear fila más tabla y actualiza
	            		modelo.addRow(new Object[]{""});
	            		updateTableOperaions();
	            		break;
	            	case "delete":
	            		deleteOperation();
	            		updateTableOperaions();
	            		break;
	            	case "modi":
	            		lastSelectedKey = view.getTableOperations().getSelectedRow();
	            		UserOperation uo3=model.getStackOperations().get(lastSelectedKey-1);;
	            		String name_=JOptionPane.showInputDialog(view, "Modify the name for the operation ",uo3.getNameOperation() );
	            		String operations_= JOptionPane.showInputDialog(view, "Modify the series of operations that the defined operation will do ",uo3.getOperations());
	            		model.getStackOperations().get(lastSelectedKey-1).setNameOperation(name_);
	            		model.getStackOperations().get(lastSelectedKey-1).setOperations(operations_);
	            		updateTableOperaions();
	            		break;
	            	case "exe":
	            		lastSelectedKey = view.getTableOperations().getSelectedRow();
	            		UserOperation uo2=model.getStackOperations().get(lastSelectedKey-1);
	            		String operationsString=uo2.getOperations();
	            		String[] operationsArray=operationsString.split(" ");
	                	for (int i = 0; i < operationsArray.length; i++) {
	            			executeSingleOperation(operationsArray[i]);
	            		}
	                	ComplexNumber cn =model.getStack().remove();
	                	for (int i = 1; i < operationsArray.length; i++) {
	            			model.getStack().remove();
	            		}
	                	model.getStack().add(cn);
	                	updateStackGraphically();
	                	break;
	            	case "save":
	            			saveToFile();
	            		break;
	            	case "load":
	            		loadFile();
	            		updateTableOperaions();
	            		break;
	            }
	        }

			private void executeSingleOperation(String operation) {
				int top = model.getStack().top;
				switch(operation) {
				case "+":
                	model.getStack().add(addSubstractOperation(model.getStack().getElement(top-1),model.getStack().getElement(top),"+" ));
                	//updateStackGraphically();
                	break;
                    
                case "-":
                	model.getStack().add(addSubstractOperation(model.getStack().getElement(top-1),model.getStack().getElement(top),"-" ));
                	//updateStackGraphically();
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
                	//updateStackGraphically();
                	break;
                	
                case "DUP":
                	model.getStack().add(model.getStack().top());
                	//updateStackGraphically();
                	break;
                	
                case "OVER":
                	model.getStack().add(model.getStack().getElement(top-1));
                	//updateStackGraphically();
                	break;
                	
                case "DROP":
                	model.getStack().remove();
                	//updateStackGraphically();
                	break;
                
                case "SWAP":
                	ComplexNumber ult=model.getStack().remove();
                	ComplexNumber pnult=model.getStack().remove();
                	model.getStack().add(ult);
                	model.getStack().add(pnult);
                	
                	//updateStackGraphically();
                	break;
                case "mod":
                	double result = modOperation(model.getStack().getElement(top));
                	model.getStack().add(new ComplexNumber(result,0.0));
                	//updateStackGraphically();
                	break;
                case "arg":
                	double result1 = argOperation(model.getStack().getElement(top));
                	model.getStack().add(new ComplexNumber(result1,0.0));
                	//updateStackGraphically();
                	break;	                	
                case "pow":
                	powOperation(model.getStack().getElement(top), 2);
                	//updateStackGraphically();
                	break;
                case "exp":
                	expOperation(model.getStack().getElement(top));
                	//updateStackGraphically();
                	break;
                case "log":
                	logOperation(model.getStack().getElement(top));
                	//updateStackGraphically();
                	break;
                case "sin":
                	model.getStack().add(sinOperation(model.getStack().getElement(top)));
                	//updateStackGraphically();
                	break;
                case "cos":
                	model.getStack().add(cosOperation(model.getStack().getElement(top)));
                	//updateStackGraphically();
                	break;
                case "tan":
                	tanOperation(model.getStack().getElement(top));
                	//updateStackGraphically();
                	break;
				}
				
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
	                	updateStackGraphically();
	                	break;
	                case "mod":
	                	double result = modOperation(model.getStack().getElement(top));
	                	model.getStack().add(new ComplexNumber(result,0.0));
	                	updateStackGraphically();
	                	break;
	                case "arg":
	                	double result1 = argOperation(model.getStack().getElement(top));
	                	model.getStack().add(new ComplexNumber(result1,0.0));
	                	updateStackGraphically();
	                	break;	                	
	                case "pow":
	                	powOperation(model.getStack().getElement(top), 2);
	                	updateStackGraphically();
	                	break;
	                case "exp":
	                	expOperation(model.getStack().getElement(top));
	                	updateStackGraphically();
	                	break;
	                case "log":
	                	logOperation(model.getStack().getElement(top));
	                	updateStackGraphically();
	                	break;
	                case "sin":
	                	model.getStack().add(sinOperation(model.getStack().getElement(top)));
	                	updateStackGraphically();
	                	break;
	                case "cos":
	                	model.getStack().add(cosOperation(model.getStack().getElement(top)));
	                	updateStackGraphically();
	                	break;
	                case "tan":
	                	tanOperation(model.getStack().getElement(top));
	                	updateStackGraphically();
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
	     private void saveToFile() {
	    		FileWriter fichero = null;
	            PrintWriter pw = null;
	            JFileChooser fileChooser = new JFileChooser();
	            fileChooser.showOpenDialog(fileChooser);
	            try
	            {
	            	
	            	String ruta = fileChooser.getSelectedFile().getAbsolutePath();
	                fichero = new FileWriter(ruta);
	                pw = new PrintWriter(fichero);
	                
	                for (int i = 0; i < model.getStackOperations().size(); i++) {
		                pw.printf("********************** NEXT OPERATION %d**************************\n", i+1);
	                	pw.println(model.getStackOperations().get(i).getNameOperation());
		                pw.println(model.getStackOperations().get(i).getOperations());}
	                
	                

	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	               try {
	               // Nuevamente aprovechamos el finally para 
	               // asegurarnos que se cierra el fichero.
	               if (null != fichero)
	                  fichero.close();
	               } catch (Exception e2) {
	                  e2.printStackTrace();
	               }
	            }
	    	}
	     
	     public void loadFile() {
	 		Scanner entrada = null;
	         JFileChooser fileChooser = new JFileChooser();
	         fileChooser.showOpenDialog(fileChooser);
	         try {
	             String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
	             File f = new File(ruta);
	             entrada = new Scanner(f);
	             String name="";
	             String operations="";
	             while(entrada.hasNext()) {
	 	            if(entrada.hasNext()) name=entrada.nextLine();
	 	            if(entrada.hasNext()) name=entrada.nextLine();
	 	            if(entrada.hasNext()) operations=entrada.nextLine();
	 	            UserOperation uo=new UserOperation(name, operations);
	 	            model.getStackOperations().add(uo);
	 	            modelo.addRow(new Object[]{""});
	             }
	             
	         } catch (FileNotFoundException e) {
	             System.out.println(e.getMessage());
	         } catch (NullPointerException e) {
	         	JOptionPane.showMessageDialog(fileChooser, "No file selected", null, 0, null);
	             System.out.println("No file selected");
	         } catch (Exception e) {
	             System.out.println(e.getMessage());
	         } finally {
	             if (entrada != null) {
	                 entrada.close();
	             }
	         }
	 	   }
	     
	}
