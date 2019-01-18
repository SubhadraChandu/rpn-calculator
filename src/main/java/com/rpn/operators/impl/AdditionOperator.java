package com.rpn.operators.impl;

import com.rpn.utils.RPNConstants;

import java.math.BigDecimal;
import java.math.MathContext;

public class AdditionOperator extends BinaryOperator {

    private static AdditionOperator instance;

    private AdditionOperator() {
    }

    public static AdditionOperator getInstance() {
        if (instance == null) {
            instance = new AdditionOperator();
        }
        return instance;
    }

    /**
     * @return Operator Identifier. "+" in this case
     */
    @Override
    public String getIdentifier() {
        return RPNConstants.ADDITION;
    }

    /**
     * Perform the addition operation on the given two operands
     * @param firstOperand
     * @param secondOperand
     * @return BigDecimal Number
     */
    @Override
    public BigDecimal calculate(BigDecimal firstOperand, BigDecimal secondOperand) {
        return firstOperand.add(secondOperand, MathContext.DECIMAL64);
    }
}
