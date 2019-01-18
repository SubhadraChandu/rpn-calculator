package com.rpn.operators.impl;

import com.rpn.utils.RPNConstants;

import java.math.BigDecimal;
import java.math.MathContext;

public class DivisionOperator extends BinaryOperator {

    private static DivisionOperator instance;

    private DivisionOperator() {
    }

    public static DivisionOperator getInstance() {
        if (instance == null) {
            instance = new DivisionOperator();
        }
        return instance;
    }

    @Override
    public String getIdentifier() {
        return RPNConstants.DIVISION;
    }

    @Override
    public BigDecimal calculate(BigDecimal firstOperand, BigDecimal secondOperand) {
        if (firstOperand.equals(BigDecimal.ZERO) && secondOperand.equals(BigDecimal.ZERO)) {
            throw new ArithmeticException("Its \"NaN\"");
        }

        return firstOperand.divide(secondOperand, MathContext.DECIMAL64);
    }
}
