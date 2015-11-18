package calculator;

public class BinaryExpression extends MathExpression {
	private MathExpression expression1;
	private MathExpression expression2;
	private BinaryOperator operator;

	BinaryExpression(MathExpression e1, MathExpression e2, BinaryOperator op) {
		expression1 = e1;
		expression2 = e2;
		operator = op;
	}

	MathExpression getExpression1() {
		return expression1;
	}

	MathExpression getExpression2() {
		return expression2;
	}
	
	@Override
	float evaluate() {
		return operator.apply(expression1, expression2);
	}

	/*@Override
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
	}*/

}
