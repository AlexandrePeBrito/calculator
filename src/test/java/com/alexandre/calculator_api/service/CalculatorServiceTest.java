package com.alexandre.calculator_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.alexandre.calculator_api.domain.OperationEnum;
import com.alexandre.calculator_api.dto.CalculatorResponse;

/* To perform the tests, use the AAA technique(Arrange, Act, Assert).
 * 
 * Given - Initial Context
 * 
 * When  - Action Performed
 * 
 * Then  - Expected Result
 * 
 */

public class CalculatorServiceTest {

	private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void shouldReturnCorrectResultWhenOperationIsSum() {
    	
    	// Arrange
    	double numberOne = 20;
    	double numberTwo = 5;
    	OperationEnum operation = OperationEnum.SUM;
    	
    	//Act
    	CalculatorResponse responseDTO = this.calculatorService.simpleCalculations(numberOne, numberTwo, operation);
    	
    	
    	//Assert
    	assertEquals(25, responseDTO.getResult());
    }
    
    @Test
    void shouldReturnCorrectResultWhenOperationIsSubtract() {
    	// Arrange
    	double numberOne = 20;
    	double numberTwo = 5;
    	OperationEnum operation = OperationEnum.SUBTRACT;
    	
    	//Act
    	CalculatorResponse responseDTO = this.calculatorService.simpleCalculations(numberOne, numberTwo, operation);
    	
    	//Assert
    	assertEquals(15, responseDTO.getResult());
    }
    
    @Test
    void shouldReturnCorrectResultWhenOperationIsDivide() {
    	// Arrange
    	double numberOne = 20;
    	double numberTwo = 5;
    	OperationEnum operation = OperationEnum.DIVIDE;
    	
    	//Act
    	CalculatorResponse responseDTO = this.calculatorService.simpleCalculations(numberOne, numberTwo, operation);
    	
    	//Assert
    	assertEquals(4, responseDTO.getResult());
    }
    
    @Test
    void shouldReturnCorrectResultWhenOperationIsDividePerZero() {
    	//Assert
    	assertThrows(IllegalArgumentException.class, 
    			() -> this.calculatorService.simpleCalculations(20, 0, OperationEnum.DIVIDE));
    }
    
    @Test
    void shouldReturnCorrectResultWhenOperationIsMultiply() {
    	// Arrange
    	double numberOne = 4;
    	double numberTwo = 5;
    	OperationEnum operation = OperationEnum.MULTIPLY;
    	
    	//Act
    	CalculatorResponse responseDTO = this.calculatorService.simpleCalculations(numberOne, numberTwo, operation);
    	
    	//Assert
    	assertEquals(20, responseDTO.getResult());
    }
}
