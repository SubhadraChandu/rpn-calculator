package com.rpn.operators.impl;

import com.rpn.utils.RPNConstants;

import java.math.BigDecimal;
import java.math.MathContext;

public class SubtractionOperator extends BinaryOperator {

    private static SubtractionOperator instance;

    private SubtractionOperator() {
    }

    public static SubtractionOperator getInstance() {
        if (instance == null) {
            instance = new SubtractionOperator();
        }
        return instance;
    }

    @Override
    public String getIdentifier() {
        return RPNConstants.SUBSTRACTION;
    }

    @Override
    public BigDecimal calculate(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.subtract(secondOperand, MathContext.DECIMAL64);
    }

}
