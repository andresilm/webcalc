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

		boolean loaded = false;
		if (sessionStore.hasSessionWithId(Id)) {
			currentSessionHistory = sessionStore.loadSessionWithId(Id);
			loaded = true;
		}

		return loaded;
	}

	public Result processInput(String input) throws SQLException {
		Result output = null;
		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

		if (isCommandInput(input)) {
			output = processCommand(input);
		} else {
			output = processMathInput(stream);

			currentSessionHistory.addCalculusToHistory(input, output.getOutput());
		}
		return output;

	}

	private boolean isCommandInput(String input) {
		// TODO Auto-generated method stub
		return input.startsWith("recuperar") || input.startsWith("guardar");
	}

	private Result processCommand(String input) throws NumberFormatException, SQLException {
		Result output = null;

		if (input.startsWith("recuperar")) {
			/*
			 * TODO: separate method
			 */
			String[] sessionCommand = input.split("\\ ");
			if (loadSessionWithId(sessionCommand[1]))
				output = new Result(printSessionHistory(currentSessionHistory), ResultCode.SESSION_LOADED_OK);
			else
				output = new Result(null, ResultCode.SESSION_LOAD_FAIL);
		} else if (input.startsWith("guardar")) {
			/*
			 * TODO: separate method
			 */
			String[] sessionCommand = input.split("\\ ");
			String sessionId = sessionCommand[1];
			saveCurrentSession(sessionId);
			/*
			 * StringBuffer message = new StringBuffer();
			 * message.append(sessionCommand[1]); message.append(" almacenada."
			 * );
			 */
			output = new Result(null, ResultCode.SESSION_SAVED_OK);
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

	private Result processMathInput(InputStream input) {
		parser = new MathGrammar(input);

		Float mathResult;
		try {
			mathResult = evaluateMathExpression(parser.one_line());
			BigDecimal value = new BigDecimal(mathResult);
			value = value.setScale(8, RoundingMode.HALF_EVEN);
			return new Result(mathResult.toString(), ResultCode.EXPRESSION_SOLVED);
		} catch (ParseException e) {

			return new Result(null, ResultCode.BAD_EXPRESSION);
		} catch (parser.TokenMgrError e) {
			return new Result(null, ResultCode.BAD_EXPRESSION);
		} catch (NumberFormatException e) { // float division by zero is
											// "allowed" --> result: Infinite or
											// NAN
			return new Result(null, ResultCode.ARITHMETIC_ERROR);
		}

	}

}
