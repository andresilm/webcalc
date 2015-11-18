package calculator;

public class BinaryExpression extends ArithmeticExpression {
	private ArithmeticExpression expression1;
	private ArithmeticExpression expression2;
	private BinaryOperator operator;

	BinaryExpression(ArithmeticExpression e1, ArithmeticExpression e2, BinaryOperator op) {
		expression1 = e1;
		expression2 = e2;
		operator = op;
	}

	ArithmeticExpression getExpression1() {
		return expression1;
	}

	ArithmeticExpression getExpression2() {
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
