package com.alexandre.calculator_api.contoller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.alexandre.calculator_api.controller.CalculatorController;
import com.alexandre.calculator_api.domain.OperationEnum;
import com.alexandre.calculator_api.dto.CalculatorResponse;
import com.alexandre.calculator_api.exception.GlobalExceptionHandler;
import com.alexandre.calculator_api.service.CalculatorService;


/* To perform the tests, use the AAA technique(Arrange, Act, Assert).
 * 
 * Given - Initial Context
 * 
 * When  - Action Performed
 * 
 * Then  - Expected Result
 * 
 */

@WebMvcTest(CalculatorController.class)
@Import(GlobalExceptionHandler.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CalculatorService calculatorService;
    
    @Test
    void shouldReturn200AndCorrectResultThenSumIsCalled() throws Exception{
    	
    	CalculatorResponse calculatorResponse = new CalculatorResponse(15);
    	
    	when(this.calculatorService.simpleCalculations(10, 5, OperationEnum.SUM))
    		.thenReturn(calculatorResponse);
    	
    	
    	mockMvc.perform(get("/calculator")
    			.param("numberOne", "10")
    			.param("numberTwo", "5")
    			.param("operation", "SUM"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.result").value(15));
    }
    
    @Test
    void shouldReturn400WhenOperationIsInvalid() throws Exception {

        mockMvc.perform(get("/calculator")
                        .param("numberOne", "10")
                        .param("numberTwo", "5")
                        .param("operation", "INVALID"))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void shouldReturn400WhenDividingByZero() throws Exception {

        when(calculatorService.simpleCalculations(10, 0, OperationEnum.DIVIDE))
                .thenThrow(new IllegalArgumentException("Division by zero is not allowed."));

        mockMvc.perform(get("/calculator")
                        .param("numberOne", "10")
                        .param("numberTwo", "0")
                        .param("operation", "DIVIDE"))
                .andExpect(status().isBadRequest());
    }

}
