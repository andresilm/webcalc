package controller;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import calculator.Calculator;
import calculator.Result;
import calculator.ResponseCode;

@RestController
public class Controller {
    private Calculator calculator = new Calculator();
    
    Controller(){
    	calculator = new Calculator();
    }

    @RequestMapping("/calculator")
    public CalculatorResponse greeting(@RequestParam(value="input", defaultValue="") String input) throws SQLException {
    	System.err.println(input);
    	Result result = calculator.processInput(input+"\n");
    	CalculatorResponse response = new CalculatorResponse(result);
        return response;
    }
}