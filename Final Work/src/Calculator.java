import javax.swing.text.View;

import controller.Controller;
import model.ComplexNumber;
import model.Model;
import model.Stack;

public class Calculator {

	public static void main(String[] args) {
		
		Model model= new Model();
		view.View view = new view.View();
		Controller controller = new Controller(model, view);
		
		view.setVisible(true);

	}

}
