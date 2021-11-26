package test;

import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import model.ComplexNumber;
import model.Model;

class OperationsTest {

	@org.junit.jupiter.api.Test
	void testAddAndSub() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("2+2i");
		ComplexNumber cn2=new ComplexNumber("2+2i");
		controller.addSubstractOperation(cn1, cn2, "+");
		controller.addSubstractOperation(cn1, cn2, "-");
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"4.0+4.0i");
		str=model.getStack().getElement(1).getStringNumber();
		assertEquals(str,"0.0+0.0i");
	}
	
	@org.junit.jupiter.api.Test
	void testMultiply() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("2+2i");
		ComplexNumber cn2=new ComplexNumber("2+2i");
		controller.multiply(cn1, cn2);
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"0.0+8.0i");
	}
	
	@org.junit.jupiter.api.Test
	void testDiv() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("2+2i");
		ComplexNumber cn2=new ComplexNumber("2+2i");
		controller.div(cn1, cn2);
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"1.0+0.0i");
	}
	
	
	@org.junit.jupiter.api.Test
	void testConjugate() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("2+2i");
		controller.conjugate(cn1);
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"2.0-2.0i");
	}
	
	void testSqrt() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		ComplexNumber cn1=new ComplexNumber("2+2i");
		controller.squareRootOperation(cn1);
		String str=model.getStack().getElement(0).getStringNumber();
		assertEquals(str,"10192828546+45578456095409i");
	}
}
