package com.alexandre.calculator_api.service;

import org.springframework.stereotype.Service;

import com.alexandre.calculator_api.domain.OperationEnum;
import com.alexandre.calculator_api.dto.CalculatorRequest;
import com.alexandre.calculator_api.dto.CalculatorResponse;

@Service
public class CalculatorService {

	public CalculatorResponse simpleCalculations(double numberOne, double numberTwo, OperationEnum operation) {
		
		double result = 0; 
		
		switch (operation) {
			case SUM -> result = numberOne + numberTwo;
			case SUBTRACT -> result = numberOne - numberTwo;
			case MULTIPLY -> result = numberOne * numberTwo;
			case DIVIDE -> { 
				if (numberTwo == 0) {
		            throw new IllegalArgumentException("Division by zero is not allowed.");
		        }
				result = numberOne / numberTwo;
			}
			default -> throw new IllegalArgumentException("Invalid operation");
		}
		return new CalculatorResponse(result);
	}
	
	public CalculatorResponse calculations(CalculatorRequest calculatorRequest) {
		
		return this.simpleCalculations(calculatorRequest.getNumberOne(), calculatorRequest.getNumberTwo(), calculatorRequest.getOperation());
	}

}
