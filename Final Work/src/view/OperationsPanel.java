package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class OperationsPanel extends JPanel {
    
    private JButton sumar;
    private JButton restar;
    private JButton multiplicar;
    private JButton dividir;
    private JButton sqrt;
    private JButton conj;
    
   public OperationsPanel( ){
       setLayout(new GridLayout(6,1));
       
       sumar = new JButton("+");
       restar = new JButton("-");
       multiplicar = new JButton("*");
       dividir = new JButton("/");
       sqrt = new JButton("SQRT");
       conj = new JButton("CONJ");
       
       sumar.setActionCommand("+");
       restar.setActionCommand("-");
       multiplicar.setActionCommand("*");
       dividir.setActionCommand("/");
       sqrt.setActionCommand("sqrt");
       conj.setActionCommand("conj");
       
       
       sumar.setForeground(Color.BLUE);
       restar.setForeground(Color.BLUE);
       multiplicar.setForeground(Color.BLUE);
       dividir.setForeground(Color.BLUE);
       sqrt.setForeground(Color.BLUE);
       conj.setForeground(Color.BLUE);
       
       this.add(sumar);
       this.add(restar);
       this.add(multiplicar);
       this.add(dividir);
       this.add(sqrt);
       this.add(conj);
   }
   
   void setActionListener(ActionListener actionListener)
    {
        sumar.addActionListener (actionListener);
        multiplicar.addActionListener (actionListener);
        restar.addActionListener (actionListener);
        dividir.addActionListener (actionListener);
        sqrt.addActionListener (actionListener);
        conj.addActionListener(actionListener);
    }
    
}