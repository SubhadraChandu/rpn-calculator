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

    /**
     * @return Operator Identifier. "-" in this case
     */
    @Override
    public String getIdentifier() {
        return RPNConstants.SUBSTRACTION;
    }

    /**
     * Perform the subtraction operation on the given two operands
     * @param firstOperand
     * @param secondOperand
     * @return BigDecimal Number
     */
    @Override
    public BigDecimal calculate(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.subtract(secondOperand, MathContext.DECIMAL64);
    }

}
