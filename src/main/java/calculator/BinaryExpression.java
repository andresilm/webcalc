package calculator;

public class BinaryExpression extends MathExpression {
	private MathExpression expression1;
	private MathExpression expression2;
	private BinaryOperator operator;

	public BinaryExpression(MathExpression e1, MathExpression e2, BinaryOperator op) {
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
	public
	float evaluate() {
		float value1 = expression1.evaluate();
		float value2 = expression2.evaluate();
		return operator.apply(value1, value2);
	}

	

}
