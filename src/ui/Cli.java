package ui;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import calculator.Calculator;
import calculator.MathExpression;
import parser.MathGrammar;

public class Cli {
	public static void main(String args []) throws ParseException, parser.ParseException, SQLException, IOException
	  {
	    
	    Calculator calc = new Calculator();
	    System.out.println("Calculator v0.1");
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    while (true)
	    {
	      System.out.print("$> ");
	      String input = in.readLine();
	      
	      
	      String output = calc.processInput(input);
	     
	      System.out.println("= " + output);
	    }
	  }
}
