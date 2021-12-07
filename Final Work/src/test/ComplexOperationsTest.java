package test;

import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import model.ComplexNumber;
import model.Model;

class ComplexOperationsTest {

	@org.junit.jupiter.api.Test
	void testModOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("1+0i");
		double result=controller.modOperation(cn1);
		assertEquals(result,1);
		}
	
	@org.junit.jupiter.api.Test
	void testArgOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("1+0i");
		double result=controller.argOperation(cn1);
		assertEquals(result,0);
	}
	
	@org.junit.jupiter.api.Test
	void testPowOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("1+0i");
		controller.powOperation(cn1, 2);
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"1.0+0.0i");
	}
	
	
	@org.junit.jupiter.api.Test
	void testExpOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("1+0i");
		controller.conjugate(cn1);
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"2.718281828459045+0.0i");
	}
	
	void testlogOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("1+0i");
		controller.logOperation(cn1);
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"0.0+0.0i");
	}
	
	@org.junit.jupiter.api.Test
	void testSinOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("1+0i");
		ComplexNumber result=controller.sinOperation(cn1);
		assertEquals(result.getStringNumber(),"0.8414709848078965+0.0i");
	}
	
	@org.junit.jupiter.api.Test
	void testCosOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("1+0i");
		ComplexNumber result=controller.cosOperation(cn1);
		assertEquals(result.getImaginary(),"0.5403023058681398+0.0i");
	}
	
	void testTanOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("1+0i");
		controller.tanOperation(cn1);
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"1.557407724654902+0.0i");
	}
}
