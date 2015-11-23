package calculator;

public class Result {
	private String output;
	private int status;

	public Result(String outputMessage, int errorCode) {
		output = outputMessage;
		status = errorCode;
			}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
