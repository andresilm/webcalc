package calculator;

public class Calculator {
	SessionStore sessionStore=null;
	Session currentSessionHistory=null;
	
	Calculator() {
		sessionStore = SessionStore.getInstance();
		currentSessionHistory = new Session();
	}
	
	private float evaluateMathExpression(ArithmeticExpression e) {
		return e.evaluate();
	}
	
	private float calculate(String input) {
		ArithmeticExpression e = parseInput(input);
		float result = evaluateMathExpression(e);
		currentSessionHistory.addCalculusToHistory(input, result);
		
		return result;
	}
	
	public void saveCurrentSession() {
		sessionStore.saveSession(currentSessionHistory);
	}
	
	public boolean loadSessionWithId(int Id) {
		Session s=null;
		boolean loaded = false;
		if (sessionStore.hasSessionWithId(Id)) {
			s = sessionStore.loadSessionWithId(Id);
			loaded = true;
		}
		
		currentSessionHistory = s;
		
		return loaded;
	}
	
	public ArithmeticExpression parseInput(String input) {
		return null;
		
	}
}
