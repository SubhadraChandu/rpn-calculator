package com.rpn.operators.impl;

import com.rpn.utils.RPNConstants;

import java.math.BigDecimal;
import java.math.MathContext;

public class SqrtOperator extends UnaryOperator {

    private static SqrtOperator instance;

    private SqrtOperator() {
    }

    public static SqrtOperator getInstance() {
        if (instance == null) {
            instance = new SqrtOperator();
        }
        return instance;
    }

    /**
     * @return Operator Identifier. "sqrt" in this case
     */
    @Override
    public String getIdentifier() {
        return RPNConstants.SQUAREROOT;
    }

    /**
     * Perform the square root operation on the given operand
     * @param operand
     * @return BigDecimal number
     */
    @Override
    public BigDecimal calculate(BigDecimal operand) {

        switch (operand.signum()) {
            case 0:
                return BigDecimal.ZERO;
            case -1:
                throw new ArithmeticException("Illegal Sqrt(x) for x < 0 : x =" + operand);
        }


        double sqrtDouble = Math.sqrt(operand.doubleValue());
        BigDecimal sqrtBigDecimal = new BigDecimal(sqrtDouble);
        return sqrtBigDecimal.round(MathContext.DECIMAL64);
    }
}
