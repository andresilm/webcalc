package mathexpression;

public class NumberExpression extends Expression {
    public float value;

    public NumberExpression(float value) {
        this.value = value;
    }


	@Override
	float evaluate() {
		return value;
	}
}