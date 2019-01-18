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

    /**
     * @return Operator Identifier. "*" in this case
     */
    @Override
    public String getIdentifier() {
        return RPNConstants.MULTIPLICATION;
    }

    /**
     * Perform the multiplication operation on the given two operands
     * @param firstOperand
     * @param secondOperand
     * @return BigDecimal Number
     */
    @Override
    public BigDecimal calculate(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.multiply(secondOperand, MathContext.DECIMAL64);
    }
}
