package calculator;

public class UnaryExpression extends ArithmeticExpression {
	private ArithmeticExpression expression;
	private UnaryOperator operator;

	UnaryExpression(ArithmeticExpression e, UnaryOperator op) {
		expression = e;
		operator = op;
	}

	ArithmeticExpression getExpression() {
		return expression;
	}

	UnaryOperator getOperator() {
		return operator;
	}

	@Override
	float evaluate() {
		Float result = null;

		Float value = expression.evaluate();
		switch (operator) {
		case MINUS: {
			result = -value;
		}
		case LOG: {
			result = (float) Math.log(value);
		}
		}
		return result;
	}

}
