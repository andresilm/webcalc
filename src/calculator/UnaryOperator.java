package calculator;

public enum UnaryOperator {
	MINUS {
		public float apply(float value) {
			return -value;
		}

	},
	LOG {
		public float apply(float value) {
			return (float)java.lang.Math.log((double)value);
		}
	};

	public abstract float apply(float value);
}
