package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;


public class UserDefinedOperationsPanel extends JPanel {
    
    private JButton newOperation;
    private JButton DeleteOperation;
    private JButton modifyUserOperation;
    private JButton execute;
    private JButton save;
    private JButton load;
    
   public UserDefinedOperationsPanel( ){
       setLayout(new GridLayout(6,1));
       
       newOperation = new JButton("Define a new operation");
       DeleteOperation = new JButton("Delete");
       modifyUserOperation = new JButton("Modify");
       execute = new JButton("Execute");
       
       save = new JButton("save");
       load = new JButton("load");
       
       newOperation.setActionCommand("new");
       DeleteOperation.setActionCommand("delete");
       modifyUserOperation.setActionCommand("modi");
       execute.setActionCommand("exe");
       save.setActionCommand("save");
       load.setActionCommand("load");
       
       newOperation.setForeground(Color.BLUE);
       DeleteOperation.setForeground(Color.BLUE);
       modifyUserOperation.setForeground(Color.BLUE);
       save.setForeground(Color.BLUE);
       load.setForeground(Color.BLUE);
       execute.setForeground(Color.BLUE);
       
       
       this.add(newOperation);
       this.add(execute);
       this.add(DeleteOperation);
       this.add(modifyUserOperation);
       this.add(save);
       this.add(load);
   }
   void setActionListener(ActionListener actionListener)
   {
	   newOperation.addActionListener (actionListener);
	   DeleteOperation.addActionListener (actionListener);
	   modifyUserOperation.addActionListener (actionListener);
       execute.addActionListener (actionListener);
       save.addActionListener (actionListener);
       load.addActionListener(actionListener);
   }
}