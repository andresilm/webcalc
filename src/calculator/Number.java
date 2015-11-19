package calculator;

public class Number extends MathExpression {
	private float value;
	public Number(float value) {
		this.value = value;
	}
	
	@Override
	public
	float evaluate() {
		return value;
	}

}
