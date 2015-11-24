package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import calculator.Calculator;
import calculator.CalculatorResponse;
import calculator.CalculatorStatus;
import util.Pair;

@RestController
public class Controller {
    private Calculator calculator = new Calculator();
    
    Controller(){
    	calculator = new Calculator();
    }

    @RequestMapping("/calculator")
    public ServerResponse greeting(@RequestParam(value="input", defaultValue="") String input) throws SQLException {
    	
    	CalculatorResponse result = calculator.processInput(input);
    	
    	ServerResponse response = new ServerResponse(result.getCalculations(),result.getStatus().value(),result.getStatus().description());
        return response;
    }
}