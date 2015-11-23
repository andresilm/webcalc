package controller;

import calculator.Result;

public class ServerResponse {
	private String data;
	private ServiceStatus serviceStatus;
	

	public ServerResponse(String calculatorOutput, int code) {
		setData(calculatorOutput);
		setServiceStatus(new ServiceStatus(code));
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}


	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}


	
}
