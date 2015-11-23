package calculator;

public class Result {
	private String data;
	private ResponseCode status;

	public Result(String outputMessage, ResponseCode errorCode) {
		this.setOutputMessage(outputMessage);
		this.setErrorCode(errorCode);
	}

	public String getData() {
		return data;
	}

	void setOutputMessage(String outputMessage) {
		this.data = outputMessage;
	}

	public ResponseCode getStatus() {
		return status;
	}

	void setErrorCode(ResponseCode errorCode) {
		this.status = errorCode;
	}
}
