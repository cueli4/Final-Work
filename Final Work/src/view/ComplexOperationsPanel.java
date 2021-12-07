package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ComplexOperationsPanel extends JPanel {
    
    private JButton mod;
    private JButton arg;
    private JButton pow;
    private JButton exp;
    private JButton log;
    private JButton sin;
    private JButton cos;
    private JButton tan;
    
   public ComplexOperationsPanel( ){
       setLayout(new GridLayout(4,2));
       
       mod = new JButton("MOD");
       arg = new JButton("ARG");
       pow = new JButton("POW");
       exp = new JButton("EXP");
       log = new JButton("LOG");
       sin = new JButton("SIN");
       cos = new JButton("COS");
       tan = new JButton("TAN");
       
       mod.setActionCommand("mod");
       arg.setActionCommand("arg");
       pow.setActionCommand("pow");
       exp.setActionCommand("exp");
       log.setActionCommand("log");
       sin.setActionCommand("sin");
       cos.setActionCommand("cos");
       tan.setActionCommand("tan");
       
       
       mod.setForeground(Color.BLUE);
       arg.setForeground(Color.BLUE);
       pow.setForeground(Color.BLUE);
       exp.setForeground(Color.BLUE);
       log.setForeground(Color.BLUE);
       sin.setForeground(Color.BLUE);
       cos.setForeground(Color.BLUE);
       tan.setForeground(Color.BLUE);
       
       this.add(mod);
       this.add(arg);
       this.add(pow);
       this.add(exp);
       this.add(log);
       this.add(sin);
       this.add(cos);
       this.add(tan);
   }
   
   void setActionListener(ActionListener actionListener)
    {
        mod.addActionListener (actionListener);
        pow.addActionListener (actionListener);
        arg.addActionListener (actionListener);
        exp.addActionListener (actionListener);
        log.addActionListener (actionListener);
        sin.addActionListener(actionListener);
        cos.addActionListener(actionListener);
        tan.addActionListener(actionListener);
    }
    
}