package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class StackManipulationPanel extends JPanel {
    
    private JButton clear;
    private JButton DUP;
    private JButton OVER;
    private JButton DROP;
    private JButton SWAP;
    
   public StackManipulationPanel( ){
       setLayout(new GridLayout(5,1));
       
       clear = new JButton("CLEAR");
       DUP = new JButton("DUP");
       OVER = new JButton("OVER");
       DROP = new JButton("DROP");
       SWAP= new JButton("SWAP");
    		   
       clear.setActionCommand("CLEAR");
       DUP.setActionCommand("DUP");
       OVER.setActionCommand("OVER");
       DROP.setActionCommand("DROP");
       SWAP.setActionCommand("SWAP");
       
       
       clear.setForeground(Color.BLUE);
       DUP.setForeground(Color.BLUE);
       OVER.setForeground(Color.BLUE);
       DROP.setForeground(Color.BLUE);
       SWAP.setForeground(Color.BLUE);
       
       this.add(clear);
       this.add(DUP);
       this.add(OVER);
       this.add(DROP);
       this.add(SWAP);
   }
   
   void setActionListener(ActionListener actionListener)
    {
        clear.addActionListener (actionListener);
        OVER.addActionListener (actionListener);
        DUP.addActionListener (actionListener);
        DROP.addActionListener (actionListener);
        SWAP.addActionListener (actionListener);
        
    }
    
}