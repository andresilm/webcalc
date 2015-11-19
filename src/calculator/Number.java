package calculator;

public class Number extends MathExpression {
	private float value;
	public Number(float value) {
		this.value = value;
	}
	
	@Override
	float evaluate() {
		return value;
	}

}
