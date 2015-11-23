package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import calculator.Calculator;
import calculator.Result;

public class Cli {
	public static void main(String args []) throws  IOException, SQLException
	  {
	    
	    Calculator calc = new Calculator();
	    System.out.println("Calculator v0.1");
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    while (true)
	    {
	      System.out.print("$> ");
	      String input = in.readLine();
	      
	      
	      Result output = calc.processInput(input);
	     
	      System.out.println("= " + output.getData());
	      System.err.println(output.getStatus());
	    }
	  }
}
