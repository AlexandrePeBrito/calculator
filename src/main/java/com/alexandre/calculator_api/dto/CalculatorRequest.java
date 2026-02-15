package com.alexandre.calculator_api.dto;

import com.alexandre.calculator_api.domain.OperationEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculatorRequest {
	private double numberOne;
	
	private double numberTwo;
	
	private OperationEnum operation;
}
