package edu.miracosta.cs113.finalproject;

/*
 * Final Project
 * File Name: MyCalculator
 * Programmer: Alex Meza
 * Date Last Modified: 5/10/15
 * Problem statement: make a gui that calculates basic math through a button panel and outputs a result
 * Overall Plan:
 * create a calculator jframe
 * set size title and layout of gui
 * create a button panel
 * create a screen label that outputs what you are typing and answer
 * add button panel and screen label to frame
 * create actionlistener that updates screen, clears screen, and does calculations depending on text within screen
 * that it can calculate otherwise sets screen to error
 * create a clear action listener as well that clears screen
 * catch divide by zero error and throw exception
 * create cos tan e^ and sin methods that returns the sum of the first several terms of the taylor series
 */
import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;;

public class MyCalculator extends JFrame{

	
	
	//screen to page start
	//button panel to page end
	public MyCalculator()
	{
		setLayout(new BorderLayout());
		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(Color.GRAY);
		add(colorPanel);
		setTitle("Calculator");
		setSize(320,350);
		setBackground(Color.BLUE);
		ButtonPanel panel = new ButtonPanel();
		add(panel.getLabel(), BorderLayout.BEFORE_FIRST_LINE);//get protected jlabel from panel
		add(panel, BorderLayout.PAGE_END);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	//button panel contains all buttons 
	//adds actionlistener to all buttons
	//buttons are arranged using gridlayout
	//set colors
	private class ButtonPanel extends JPanel
	{
		private JButton zeroB;
		private JButton c;
		private JButton mult;
		private JButton equal;
		private JButton oneB;
		private JButton twoB;
		private JButton threeB;
		private JButton add;
		private JButton fourB;
		private JButton fiveB;
		private JButton sixB;
		private JButton sub;
		private JButton sevenB;
		private JButton eightB;
		private JButton nineB;
		private JButton divide;
		private JButton cos;
		private JButton sin;
		private JButton tan;
		private JButton e;
		private JButton dec;
		public JLabel screen;
		
		public JLabel getLabel()
		{
			return screen;
		}
		public ButtonPanel()
		{
			setLayout(new GridLayout(5,5,5,5));
			screen = new JLabel();
			Event1 event = new Event1();
			
			oneB = new JButton("1");
			oneB.addActionListener(event);
			oneB.setBackground(Color.CYAN);
			twoB = new JButton("2");
			twoB.addActionListener(event);
			twoB.setBackground(Color.CYAN);
			threeB = new JButton("3");
			threeB.addActionListener(event);
			threeB.setBackground(Color.CYAN);
			add = new JButton(" + ");
			add.addActionListener(event);
			add.setBackground(Color.CYAN);
			fourB = new JButton("4");
			fourB.addActionListener(event);
			fourB.setBackground(Color.CYAN);
			fiveB = new JButton("5");
			fiveB.addActionListener(event);
			fiveB.setBackground(Color.CYAN);
			sixB = new JButton("6");
			sixB.addActionListener(event);
			sixB.setBackground(Color.CYAN);
			sub = new JButton(" - ");
			sub.addActionListener(event);
			sub.setBackground(Color.CYAN);
			sevenB = new JButton("7");
			sevenB.addActionListener(event);
			sevenB.setBackground(Color.CYAN);
			eightB = new JButton("8");
			eightB.addActionListener(event);
			eightB.setBackground(Color.CYAN);
			nineB = new JButton("9");
			nineB.addActionListener(event);
			nineB.setBackground(Color.CYAN);
			divide = new JButton(" / ");
			divide.addActionListener(event);
			divide.setBackground(Color.CYAN);
			zeroB= new JButton("0");
			zeroB.addActionListener(event);
			zeroB.setBackground(Color.CYAN);
			c = new JButton("c");
			c.addActionListener(event);
			c.setBackground(Color.CYAN);
			mult = new JButton(" * ");
			mult.addActionListener(event);
			mult.setBackground(Color.CYAN);
			equal = new JButton("= ");
			equal.addActionListener(event);
			equal.setBackground(Color.CYAN);
			sin = new JButton("sin");
			sin.addActionListener(event);
			sin.setBackground(Color.CYAN);
			cos = new JButton("cos");
			cos.addActionListener(event);
			cos.setBackground(Color.CYAN);
			tan = new JButton("tan");
			tan.addActionListener(event);
			tan.setBackground(Color.CYAN);
			e = new JButton("e^");
			e.addActionListener(event);
			e.setBackground(Color.CYAN);
			dec = new JButton(".");
			dec.addActionListener(event);
			dec.setBackground(Color.CYAN);			
			
			add(oneB);
			add(twoB);
			add(threeB);
			add(add);
			add(sin);
			add(fourB);
			add(fiveB);
			add(sixB);
			add(sub);
			add(cos);
			add(sevenB);
			add(eightB);
			add(nineB);
			add(divide);
			add(tan);
			add(zeroB);
			add(dec);
			add(mult);
			add(equal);
			add(e);
			add(c);
			setBackground(Color.BLACK);
		}
		private class Event1 implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton temp = (JButton)e.getSource();
				
				
				if(temp==c)
				{
					screen.setText("");
				}
				else if(temp == equal)
				{
					
					String s = screen.getText();
					Calculations tool = new Calculations();
					try{
						screen.setText(tool.basicPattern(s));
					}
					catch(Exception ex)
					{
						screen.setText("error");
					}
				}
				else
				{
					screen.setText(screen.getText()+temp.getText());
				}
			}
		}
	}
	
}
