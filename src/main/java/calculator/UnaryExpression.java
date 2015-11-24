package calculator;

public class UnaryExpression extends MathExpression {
	private MathExpression expression;
	private UnaryOperator operator;

	public UnaryExpression(MathExpression e, UnaryOperator op) {
		expression = e;
		operator = op;
	}

	MathExpression getExpression() {
		return expression;
	}

	UnaryOperator getOperator() {
		return operator;
	}
	
	@Override
	public
	float evaluate() {
		float value = expression.evaluate();
		return operator.apply(value);
	}


}
