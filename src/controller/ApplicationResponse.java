package controller;

import calculator.Result;

public class ApplicationResponse {
	String data;
	int code;
	

	public ApplicationResponse(Result result) {
		data  = result.getOutput();
		
		
	}

}
