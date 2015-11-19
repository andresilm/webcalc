package calculator;

import java.util.ArrayList;
import util.Pair;


public class Session extends ArrayList<Pair<String,Float>> {
	
	public void addCalculusToHistory(String input, float result) {
		this.add(new Pair<String,Float>(input,result));
	}
	
}
