package calculator;

public enum CalculatorStatus {
	OK {

		@Override
		public int value() {
			
			return 0;
		}

		@Override
		public String description() {
			// TODO Auto-generated method stub
			return "OK";
		}
		
	},
	INVALID_INPUT {

		@Override
		public int value() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public String description() {
			// TODO Auto-generated method stub
			return "Invalid math expression or command";
		}
		
	},
	ARITHMETIC_ERROR {

		@Override
		public int value() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public String description() {
			// TODO Auto-generated method stub
			return "Error when trying to solve math expression";
		}
		
	},
	SESSION_NOT_FOUND  {

		@Override
		public int value() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public String description() {
			// TODO Auto-generated method stub
			return "Requested session not found in database";
		}
		
	},
	INTERNAL_ERROR {

		@Override
		public int value() {
			// TODO Auto-generated method stub
			return 4;
		}

		@Override
		public String description() {
			// TODO Auto-generated method stub
			return "An internal error occurred";
		}
		
	};
	
	public abstract int value();
	public abstract String description();
}
