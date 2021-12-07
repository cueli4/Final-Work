package test;

import static org.junit.jupiter.api.Assertions.*;

import controller.Controller;
import model.ComplexNumber;
import model.Model;
import model.UserOperation;

class DefineOperationsTest {

	@org.junit.jupiter.api.Test
	void testDefineOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		UserOperation uo=new UserOperation("Operacion1", "+ - *");
		model.getStackOperations().add(uo);
		assertEquals(model.getStackOperations().get(0).getNameOperation(),"Operacion1");
		assertEquals(model.getStackOperations().get(0).getOperations(),"+ - *");
	}
	
	@org.junit.jupiter.api.Test
	void testDeleteOperation() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		UserOperation uo=new UserOperation("Operacion1", "+ - *");
		model.getStackOperations().add(uo);
		uo=new UserOperation("Operacion2", "- - -");
		model.getStackOperations().add(uo);
		model.getStackOperations().remove(0);
		assertEquals(model.getStackOperations().get(0).getNameOperation(),"Operacion2");
		assertEquals(model.getStackOperations().get(0).getOperations(),"- - -");
	}
		
	@org.junit.jupiter.api.Test
	void testload() {
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		controller.loadFile();
		assertEquals(model.getStackOperations().get(0).getNameOperation(),"Hola");
		assertEquals(model.getStackOperations().get(0).getOperations(),"+ -");
		
	}
	}
