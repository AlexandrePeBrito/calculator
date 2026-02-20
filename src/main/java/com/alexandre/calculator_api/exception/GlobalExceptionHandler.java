package com.alexandre.calculator_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alexandre.calculator_api.domain.OperationEnum;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleEnumMismatch(MethodArgumentTypeMismatchException ex) {

	    if (ex.getRequiredType() == OperationEnum.class) {
	        return ResponseEntity
	                .badRequest()
	                .body("Invalid operation. Allowed values: SUM, SUBTRACT, MULTIPLY, DIVIDE");
	    }

	    return ResponseEntity.badRequest().body("Invalid parameter.");
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException ex) {
	    return ResponseEntity
	            .badRequest()
	            .body("Invalid request body. Check operation field.");
	}
}
