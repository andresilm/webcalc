package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import util.Pair;
import calculator.Calculator;
import calculator.CalculatorResponse;

public class Cli {
	public static void main(String args []) throws  IOException, SQLException
	  {
	    
	    Calculator calc = new Calculator();
	    System.out.println("Calculator v0.99");
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    while (true)
	    {
	      System.out.print("$> ");
	      String input = in.readLine();
	      
	      
	      CalculatorResponse output = calc.processInput(input);
	     
	      for (Pair<String,String> c: output.getCalculations()) {
	    	  System.out.println(c.getX()+"\n= " + c.getY());
	    	  
	      }
	      System.out.println(output.getStatus().description());
	    }
	  }
}
