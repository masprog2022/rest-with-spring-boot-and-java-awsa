package com.masprogtechs;

//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masprogtechs.converter.NumberConverter;
import com.masprogtechs.exceptions.UnsupportedMathOperationException;
import com.masprogtechs.math.SimpleMath;

@RestController
public class MathController {
	
	
	
	//private static final AtomicLong counter = new AtomicLong();
	
	private SimpleMath math = new SimpleMath();
	
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("please set a numeric value");
		}
		
		return math.sum(NumberConverter.converterToDouble(numberOne), NumberConverter.converterToDouble(numberTwo));
	}
	
	
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double subtraction(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("please set a numeric value");
		}
		
		return math.subtraction(NumberConverter.converterToDouble(numberOne), NumberConverter.converterToDouble(numberTwo));
	}
	
	
	@RequestMapping(value = "/multiplcation/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double multiplcation(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("please set a numeric value");
		}
		
		return math.multiplcation(NumberConverter.converterToDouble(numberOne), NumberConverter.converterToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/division/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double division(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("please set a numeric value");
		}
		
		return math.division(NumberConverter.converterToDouble(numberOne), NumberConverter.converterToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double mean(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("please set a numeric value");
		}
		
		return math.mean(NumberConverter.converterToDouble(numberOne), NumberConverter.converterToDouble(numberTwo));
	}
	@RequestMapping(value = "/squareRoot/{number}",
			method=RequestMethod.GET)
	public Double squareRoot(
			@PathVariable(value = "number") String number
			) throws Exception {
		
		
		if(!NumberConverter.isNumeric(number)) {
			throw new UnsupportedMathOperationException("please set a numeric value");
		}
		
		return math.squareRoot(NumberConverter.converterToDouble(number));
	}




}
