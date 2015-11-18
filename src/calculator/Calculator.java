package calculator;

public class Calculator {
	SessionStore sessionStore = null;
	Session currentSessionHistory = null;

	Calculator() {
		sessionStore = SessionStore.getInstance();
		currentSessionHistory = new Session();
	}

	public String calculate(String input) {
		ParsedInput calcInput = parse(input);
		String messageToDisplay = null;
		/**
		 * tratar cada caso de input: ArithmeticExpression Command
		 */
		return messageToDisplay;

	}

	private float evaluateMathExpression(MathExpression e) {
		return e.evaluate();
	}

	private float processArithMeticInput(MathExpression e, String originalInput) {

		float result = evaluateMathExpression(e);
		currentSessionHistory.addCalculusToHistory(originalInput, result);

		return result;
	}

	public void saveCurrentSession() {
		sessionStore.saveSession(currentSessionHistory);
	}

	public boolean loadSessionWithId(int Id) {
		Session s = null;
		boolean loaded = false;
		if (sessionStore.hasSessionWithId(Id)) {
			s = sessionStore.loadSessionWithId(Id);
			loaded = true;
		}

		currentSessionHistory = s;

		return loaded;
	}

	public ParsedInput parse(String input) {
		ParsedInput parsedInput = null;
		/**
		 * invocar al parser
		 */
		return parsedInput;
	}
}
