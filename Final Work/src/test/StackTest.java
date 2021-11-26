package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import controller.Controller;
import model.ComplexNumber;
import model.Model;
import model.Stack;

class StackTest {
	
	Model model;
	
	@Test
	void testAdd() {
		model = new Model();
		ComplexNumber cn= new ComplexNumber("1+2i");
		model.getStack().add(cn);
		assertEquals(model.getStack().getElement(0).getStringNumber(), "1.0+2.0i");
	}
	
	
	@Test
	void testRemove() {
		model = new Model();
		ComplexNumber cn= new ComplexNumber("1+2i");
		model.getStack().add(cn);
		ComplexNumber cn1=model.getStack().remove();
		assertEquals(cn1.getStringNumber(), cn.getStringNumber());
	}

	 @Test
	    public void testSize() {
		 	model = new Model();
	        int expected = -1;
	        int result = model.getStack().top;
	        assertEquals(expected, result);
	        model.getStack().add(new ComplexNumber(1,0));
	        int expected2 = 0;
	        int result2 = model.getStack().top;
	        assertEquals(expected2, result2);
	    }
	 

	 @Test
	    public void testIsEmpty() {
		 	model = new Model();
	        boolean expected = true;
	        boolean result = model.getStack().isEmpty();
	        assertEquals(expected, result);
	    }
	
}
