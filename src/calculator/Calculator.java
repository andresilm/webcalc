package calculator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import parser.MathGrammar;
import parser.ParseException;
import util.Pair;

public class Calculator {
	SessionStore sessionStore = null;
	Calculations currentSessionCalculations = null;
	MathGrammar parser;

	public Calculator() {
		sessionStore = SessionStore.getInstance();
		currentSessionCalculations = new Calculations();

	}

	private float evaluateMathExpression(MathExpression e) {
		return e.evaluate();
	}

	private void saveCurrentSession(String sessionId) throws SQLException {
		sessionStore.saveSession(currentSessionCalculations, sessionId);
	}

	private boolean loadSessionWithId(String Id) throws SQLException {

		boolean loaded = false;
		if (sessionStore.hasSessionWithId(Id)) {
			currentSessionCalculations = sessionStore.loadSessionWithId(Id);
			loaded = true;
		}

		return loaded;
	}

	public CalculatorResponse processInput(String input) throws SQLException {
		CalculatorResponse result=null;

		if (!input.isEmpty()) {

			if (isCommandInput(input)) {
				result = processCommand(input);
			} else {
				result = processMathInput(input);
				if (result.getStatus() == CalculatorStatus.OK)
					currentSessionCalculations.addCalculusToHistory(input, result.getUserCommand(0).getY());
			}
		} else {
			result = new CalculatorResponse();
			result.setStatus(CalculatorStatus.INVALID_INPUT);
		}
		return result;

	}

	private boolean isCommandInput(String input) {
		// TODO Auto-generated method stub
		return input.startsWith("recuperar") || input.startsWith("guardar");
	}

	private CalculatorResponse processCommand(String input) throws NumberFormatException {
		CalculatorResponse output = new CalculatorResponse();

		if (input.startsWith("recuperar")) {
			/*
			 * TODO: separate method
			 */
			String[] sessionCommand = input.split("\\ ");
			boolean session_found=false;
			try {
				session_found = loadSessionWithId(sessionCommand[1]);
			} catch (SQLException e) {
				output.setStatus(CalculatorStatus.INTERNAL_ERROR);
			}
			if (session_found) {
				output.addSolvedOperations(this.currentSessionCalculations);
				output.setStatus(CalculatorStatus.OK);
			}
			else
				output.setStatus(CalculatorStatus.SESSION_NOT_FOUND);
		} else if (input.startsWith("guardar")) {
			/*
			 * TODO: separate method
			 */
			String[] sessionCommand = input.split("\\ ");
			String sessionId = sessionCommand[1];
			try {
				saveCurrentSession(sessionId);
				output.setStatus(CalculatorStatus.OK);
			} catch (SQLException e) {
				output.setStatus(CalculatorStatus.INTERNAL_ERROR);
			}
			
			
		}
		return output;
	}

	private CalculatorResponse processMathInput(String input) {
		InputStream stream = new ByteArrayInputStream((input + "\n").getBytes(StandardCharsets.UTF_8));
		parser = new MathGrammar(stream);
		CalculatorResponse result = new CalculatorResponse();
		Float mathResult;

		try {
			mathResult = evaluateMathExpression(parser.one_line());
			
			result.addSolvedOperation(input, mathResult.toString());
			result.setStatus(CalculatorStatus.OK);
		} catch (ParseException e) {

			result.setStatus(CalculatorStatus.INVALID_INPUT);
		} catch (parser.TokenMgrError e) {
			result.setStatus(CalculatorStatus.INVALID_INPUT);
		} catch (NumberFormatException e) { // float division by zero is
											// "allowed" --> result: Infinite or
											// NAN
			result.setStatus(CalculatorStatus.ARITHMETIC_ERROR);
		}
		return result;
	}

}
