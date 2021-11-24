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

	public JButton getBtnEnter() {
		return btnEnter;
	}

	/**
	 * Create the frame.
	 */
	public View() {

		super("Calculator");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 500);
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
	public void setActionListener (ActionListener actionListener)
    {
        numeros.setActionListener (actionListener);
        operaciones.setActionListener (actionListener);
    }
}
