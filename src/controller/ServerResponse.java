package controller;

import java.util.ArrayList;
import java.util.List;
import util.Pair;

class Calculation {
	public String math_expression;
	public String result;
	
	Calculation(String mathExpression, String result) {
		math_expression = mathExpression;
		this.result = result;
	}
}

public class ServerResponse {
	
	
	private List<Calculation> data;
	private ServiceStatus serviceStatus;
	

	public ServerResponse(List<Pair<String, String>> data,int code) {
		this.data= new ArrayList<Calculation>();
		for (Pair<String, String> pair: data)
			addToData(pair);
		setServiceStatus(new ServiceStatus(code));
	}




	public void addToData(Pair<String, String> calculation) {
		this.data.add(new Calculation(calculation.getX(),calculation.getY()));
	}


	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}


	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}


	
}
