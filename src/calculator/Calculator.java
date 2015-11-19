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
	Session currentSessionHistory = null;
	MathGrammar parser;

	public Calculator() {
		sessionStore = SessionStore.getInstance();
		currentSessionHistory = new Session();

	}

	private float evaluateMathExpression(MathExpression e) {
		return e.evaluate();
	}

	private void saveCurrentSession(String sessionId) throws SQLException {
		sessionStore.saveSession(currentSessionHistory, sessionId);
	}

	private boolean loadSessionWithId(String Id) throws SQLException {
		Session s = null;
		boolean loaded = false;
		if (sessionStore.hasSessionWithId(Id)) {
			s = sessionStore.loadSessionWithId(Id);
			loaded = true;
		}

		currentSessionHistory = s;

		return loaded;
	}

	public String processInput(String input) throws ParseException, SQLException {
		String output = "INTERNAL_ERROR";
		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		/*
		 * TODO.improve parser chooser. Now, testing purposes.
		 */

		if (isCommandInput(input)) {
			output = processCommand(input);
		} else {
			output = processMathInput(stream);

			currentSessionHistory.addCalculusToHistory(input, output);
		}
		return output;

	}

	private boolean isCommandInput(String input) {
		// TODO Auto-generated method stub
		return input.startsWith("recuperar") || input.startsWith("guardar");
	}

	private String processCommand(String input) throws NumberFormatException, SQLException {
		String output = null;

		if (input.startsWith("recuperar")) {
			String[] sessionCommand = input.split("\\ ");
			loadSessionWithId(sessionCommand[1]);
			output = printSessionHistory(currentSessionHistory);
		} else if (input.startsWith("guardar")) {
			String[] sessionCommand = input.split("\\ ");
			String sessionId = sessionCommand[1];
			saveCurrentSession(sessionId);
			StringBuffer output2 = new StringBuffer("sesion");
			output2.append(sessionCommand[1]);
			output2.append(" almacenada");
			output = output2.toString();
		}
		return output;
	}

	private String printSessionHistory(Session session) {
		StringBuffer output = new StringBuffer("");
		for (Pair<String, String> calculus : session) {
			output.append(calculus.getX());
			output.append("\n= ");
			output.append(calculus.getY());
			output.append("\n");
		}
		return output.toString();
	}

	private String processMathInput(InputStream input) throws ParseException {
		parser = new MathGrammar(input);
		Float mathResult = evaluateMathExpression(parser.one_line());
		BigDecimal value = new BigDecimal(mathResult);
		value = value.setScale(8, RoundingMode.HALF_EVEN);
		return mathResult.toString();

	}

}
