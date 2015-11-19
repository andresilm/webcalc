package calculator;

import java.util.ArrayList;
import util.Pair;


public class Session extends ArrayList<Pair<String,String>> {
	
	public void addCalculusToHistory(String input, String result) {
		this.add(new Pair<String,String>(input,result));
	}
	
}
