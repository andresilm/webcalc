package calculator;

import java.util.ArrayList;
import java.util.List;

import util.Pair;

public class CalculatorResponse {
	private List<Pair<String,String>> calculations;
	private CalculatorStatus status;

	public CalculatorResponse() {
		setCalculations(new ArrayList<Pair<String,String>>());
		
	}

	public void addSolvedOperation(String input, String solution) {
		getCalculations().add(new Pair<String,String>(input, solution));
	}
	
	public void addSolvedOperations(Calculations calculations) {
		this.calculations = calculations;
	}

	public CalculatorStatus getStatus() {
		return status;
	}

	public void setStatus(CalculatorStatus status) {
		this.status = status;
	}

	public Pair<String,String> getUserCommand(int index) {
		return getCalculations().get(index);
	}

	public List<Pair<String,String>> getCalculations() {
		return calculations;
	}

	private void setCalculations(List<Pair<String,String>> calculations) {
		this.calculations = calculations;
	}

}
