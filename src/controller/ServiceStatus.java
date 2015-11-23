package controller;

public class ServiceStatus {
	private int code;
	private String message;
	
	ServiceStatus(int calculatorStatusCode) {
		this.setCode(calculatorStatusCode);
		message = setErrorMessage(calculatorStatusCode);
		
	}
	public String setErrorMessage(int calculatorStatusCode) {
		return null;
		
	}

	public int getCode() {
		return code;
	}

	void setCode(int code) {
	
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	void setMessage(String message) {
		this.message = message;
	}

}
