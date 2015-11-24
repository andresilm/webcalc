package controller;

import java.util.ArrayList;
import java.util.List;
import util.Pair;

class Calculation {
	private String math_expression;
	private String result;

	Calculation(String mathExpression, String result) {
		setMath_expression(mathExpression);
		this.setResult(result);
	}

	public String getMath_expression() {
		return math_expression;
	}

	public void setMath_expression(String math_expression) {
		this.math_expression = math_expression;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}

public class ServerResponse {

	private List<Calculation> data;
	private ServiceStatus service_status;

	public ServerResponse(List<Pair<String, String>> data, int code, String statusMessage) {
		this.setData(new ArrayList<Calculation>());
		for (Pair<String, String> pair : data)
			addToData(pair);
		setService_status(new ServiceStatus(code,statusMessage));
	}

	private void addToData(Pair<String, String> calculation) {
		this.getData().add(new Calculation(calculation.getX(), calculation.getY()));
	}

	public ServiceStatus getService_status() {
		return service_status;
	}

	public void setService_status(ServiceStatus serviceStatus) {
		this.service_status = serviceStatus;
	}

	public List<Calculation> getData() {
		return data;
	}

	private void setData(List<Calculation> data) {
		this.data = data;
	}

}
