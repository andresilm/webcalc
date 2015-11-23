package calculator;

public class Result {
	private String output;
	private ResultCode status;

	public Result(String outputMessage, ResultCode errorCode) {
			}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public ResultCode getStatus() {
		return status;
	}

	public void setStatus(ResultCode status) {
		this.status = status;
	}


}
