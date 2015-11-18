package calculator;

public enum BinaryOperator {
	ADDITION {
		public float apply(MathExpression e1,MathExpression e2) {
			float value1 = e1.evaluate();
			float value2 = e2.evaluate();
			
			return value1 + value2;
		}
	},
	SUBSTRACTION {
		public float apply(MathExpression e1,MathExpression e2) {
			float value1 = e1.evaluate();
			float value2 = e2.evaluate();
			
			return value1 - value2;
		}
	},
	MULTIPLICATION {
		public float apply(MathExpression e1,MathExpression e2) {
			float value1 = e1.evaluate();
			float value2 = e2.evaluate();
			
			return value1 * value2;
		}
	},
	DIVISION {
		public float apply(MathExpression e1,MathExpression e2) {
			float value1 = e1.evaluate();
			float value2 = e2.evaluate();
			
			return value1 / value2;
		}
	};
	
	public abstract float apply(MathExpression e1,MathExpression e2);
}
