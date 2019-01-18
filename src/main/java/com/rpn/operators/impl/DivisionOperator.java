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

    /**
     * @return Operator Identifier. "/" in this case
     */
    @Override
    public String getIdentifier() {
        return RPNConstants.DIVISION;
    }

    /**
     * Perform the division operation on the given two operands
     * @param firstOperand
     * @param secondOperand
     * @return BigDecimal Number
     */
    @Override
    public BigDecimal calculate(BigDecimal firstOperand, BigDecimal secondOperand) {
        if (firstOperand.equals(BigDecimal.ZERO) && secondOperand.equals(BigDecimal.ZERO)) {
            throw new ArithmeticException("Its \"NaN\"");
        }

        return firstOperand.divide(secondOperand, MathContext.DECIMAL64);
    }
}
