package com.capgemini.app.sprinaopcalculator.calculator;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

	public Integer addition(int num1, int num2) {
		System.out.println(num1+num2);
		System.out.println();
		return num1+ num2;
	}
}
