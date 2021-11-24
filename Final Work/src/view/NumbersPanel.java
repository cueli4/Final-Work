package view;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NumbersPanel extends JPanel{
    
    private final JButton[] botones = new JButton[12];
    
    public NumbersPanel()
    {
        this.setLayout (new GridLayout (3,4));
        
        String[] botones_name = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "C"};
        
        for (int i = 0; i < 12; i++)
        {
            botones[i] = new JButton(botones_name[i]);
            botones[i].setActionCommand (botones_name[i]);
            this.add (botones[i]);
        }
    }
    
    public void setActionListener (ActionListener actionlistener)
    {
        for (int i = 0; i < 12; i++)
            botones[i].addActionListener (actionlistener);
    }
}

