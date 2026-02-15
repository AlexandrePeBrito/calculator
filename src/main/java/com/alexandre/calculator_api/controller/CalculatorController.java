package com.alexandre.calculator_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.calculator_api.domain.OperationEnum;
import com.alexandre.calculator_api.dto.CalculatorRequest;
import com.alexandre.calculator_api.dto.CalculatorResponse;
import com.alexandre.calculator_api.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

	private final CalculatorService calculatorService;
	
	@Autowired
	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}
	
	@GetMapping
	public CalculatorResponse simpleCalculations(@RequestParam double numberOne, @RequestParam double numberTwo, @RequestParam OperationEnum operation){
		
		return this.calculatorService.simpleCalculations(numberOne, numberTwo, operation); 
	}
	
	@PostMapping("/calculation")
	public CalculatorResponse calculations(@RequestBody CalculatorRequest dto){
		
		return this.calculatorService.calculations(dto);
	}
}