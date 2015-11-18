package calculator;

public class BinaryExpression extends Expression {
	private Expression expression1;
	private Expression expression2;
	private BinaryOperator operator;

	BinaryExpression(Expression e1, Expression e2, BinaryOperator op) {
		expression1 = e1;
		expression2 = e2;
		operator = op;
	}

	Expression getExpression1() {
		return expression1;
	}

	Expression getExpression2() {
		return expression2;
	}

	@Override
	float evaluate() {
		Float result = null;
		Float value1 = expression1.evaluate();
		Float value2 = expression2.evaluate();

		switch (operator) {
		case ADDITION: {
			result = value1 + value2;
			break;
		}
		case MULTIPLICATION: {
			result = value1 * value2;
			break;
		}

		case SUBSTRACTION: {
			result = value1 - value2;
			break;
		}

		case DIVISION: {
			if (value2 != 0f) {
				result = value1 / value2;
			}
			break;
		}

		}
		return result;
	}

}
