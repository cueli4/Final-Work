package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.CardLayout;


public class ScreenPanel extends JPanel {

     private JTextField calculo;
    

    public ScreenPanel()
    {
        this.setBackground(Color.BLUE);
        this.setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.LIGHT_GRAY));
        setLayout(new CardLayout(0, 0));
        
        calculo = new JTextField ("", 17);
        
        calculo.setFont (new Font ("Consolas", Font.PLAIN, 44));
        calculo.setBackground(Color.BLUE);
        calculo.setForeground (Color.WHITE);
        calculo.setEnabled (true);
        calculo.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.blue));
        calculo.setPreferredSize (new Dimension(1050, 44));
        calculo.setMinimumSize (calculo.getPreferredSize());
        calculo.setMaximumSize (calculo.getPreferredSize());
        
        this.add (calculo, "name_1299544704577600");
    }
    
    public void setCalculo (String calculo)
    {
        this.calculo.setText (calculo);
    }
    public JTextField getCalculo ()
    {
        return this.calculo;
    }
}
