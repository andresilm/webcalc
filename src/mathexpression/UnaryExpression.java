package mathexpression;

public class UnaryExpression extends Expression {
	private Expression expression;
	private UnaryOperator operator;

	UnaryExpression(Expression e, UnaryOperator op) {
		expression = e;
		operator = op;
	}

	Expression getExpression() {
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
