package calculator;

public enum BinaryOperator {
	ADDITION {
		public float apply(float value1, float value2) {

			return value1 + value2;
		}
	},
	SUBSTRACTION {
		public float apply(float value1, float value2) {

			return value1 - value2;
		}
	},
	MULTIPLICATION {
		public float apply(float value1, float value2) {

			return value1 * value2;
		}
	},
	DIVISION {
		public float apply(float value1, float value2) {

			return value1 / value2;
		}
	};

	public abstract float apply(float value1, float value2);
}
