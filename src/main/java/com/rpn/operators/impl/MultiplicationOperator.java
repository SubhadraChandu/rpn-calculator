package com.rpn.operators.impl;

import com.rpn.utils.RPNConstants;

import java.math.BigDecimal;
import java.math.MathContext;

public class MultiplicationOperator extends BinaryOperator {

    private static MultiplicationOperator instance;

    private MultiplicationOperator() {
    }

    public static MultiplicationOperator getInstance() {
        if (instance == null) {
            instance = new MultiplicationOperator();
        }
        return instance;
    }

    @Override
    public String getIdentifier() {
        return RPNConstants.MULTIPLICATION;
    }

    @Override
    public BigDecimal calculate(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.multiply(secondOperand, MathContext.DECIMAL64);
    }
}
