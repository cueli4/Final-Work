package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.ComplexNumber;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

public class View extends JFrame {

	private JPanel contentPane;
	static View frame;
	JPanel panelStack;
	JButton btnEnter;
	
	/*
	 * 
	 * 
	 */
    private NumbersPanel numeros;
    private OperationsPanel operaciones;
    private ScreenPanel pantalla;
    private StackManipulationPanel manipulation;
    private JTable table;

	public JButton getBtnEnter() {
		return btnEnter;
	}

	/**
	 * Create the frame.
	 */
	public View() {

		super("Calculator");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1199, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnEnter = new JButton("Enter");
		btnEnter.setBounds(352, 21, 98, 62);
		contentPane.add(btnEnter);
		
		JLabel lblStack = new JLabel("STACK");
		lblStack.setFont(new Font("Consolas", Font.BOLD, 24));
		lblStack.setBounds(529, 34, 76, 30);
		contentPane.add(lblStack);
		numeros = new NumbersPanel();
		numeros.setBounds(10, 114, 290, 266);
		contentPane.add (numeros);
		
		panelStack = new JPanel();
		panelStack.setBounds(481, 75, 191, 306);
		panelStack.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		contentPane.add(panelStack);
		panelStack.setLayout(new BoxLayout(panelStack, BoxLayout.Y_AXIS));
		pantalla = new ScreenPanel();
		pantalla.getCalculo().setColumns(12);
		pantalla.setBounds(10, 21, 332, 62);
		contentPane.add (pantalla);
		pantalla.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
        
        operaciones = new OperationsPanel();
        operaciones.setBounds(327, 114, 123, 266);
        contentPane.add (operaciones, BorderLayout.SOUTH);
        contentPane.setSize(400, 400);
        
        manipulation=new StackManipulationPanel();
        manipulation.setBounds(696, 75, 98, 306);
        contentPane.add (manipulation, BorderLayout.SOUTH);
        
        table = new JTable();
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setFont(new Font("Sitka Small", Font.PLAIN, 18));
        table.setForeground(new Color(0, 0, 0));
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		
        		{"a", "0+0i"},
        		{"b", "0+0i"},
        		{"c", "0+0i"},
        		{"d", "0+0i"},
        		{"e", "0+0i"},
        		{"f", "0+0i"},
        		{"g", "0+0i"},
        		{"h", "0+0i"},
        		{"i", "0+0i"},
        		{"j", "0+0i"},
        		{"k", "0+0i"},
        		{"l", "0+0i"},
        		{"m", "0+0i"},
        		{"n", "0+0i"},
        		{"o", "0+0i"},
        		{"p", "0+0i"},
        		{"q", "0+0i"},
        		{"r", "0+0i"},
        		{"s", "0+0i"},
        		{"t", "0+0i"},
        		{"u", "0+0i"},
        		{"v", "0+0i"},
        		{"w", "0+0i"},
        		{"x", "0+0i"},
        		{"y", "0+0i"},
        		{"z", "0+0i"},
        		
        	},
        	new String[] {
        		"Variables", "Value"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		String.class, String.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        	boolean[] columnEditables = new boolean[] {
        		false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(55);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.setBounds(864, 75, 313, 416);
        contentPane.add(table);
        
        
        
        pantalla.requestFocus();
	}
	
	public String getTextFieldString() {
		return pantalla.getCalculo().getText();
	}
	public JTextField getTextField() {
		return pantalla.getCalculo();
	}
	
	public View getframe() {
		return this.frame;
	}
	

	public JPanel getPanelStack() {
		// TODO Auto-generated method stub
		return this.panelStack;
	}
	
	public NumbersPanel getNumeros() {
		return numeros;
	}

	public OperationsPanel getOperaciones() {
		return operaciones;
	}
	
	public ScreenPanel getPantalla() {
		return pantalla;
	}
	public JTable getTable() {
		return this.table;
	}
	public void setActionListener (ActionListener actionListener)
    {
        numeros.setActionListener (actionListener);
        operaciones.setActionListener (actionListener);
        manipulation.setActionListener(actionListener);
    }
}
