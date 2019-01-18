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

        MathContext mc = MathContext.DECIMAL64;
        BigDecimal TWO = new BigDecimal(2);

        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal(Math.sqrt(operand.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = operand.divide(x0, mc);
            x1 = x1.add(x0);
            x1 = x1.divide(TWO, mc);
        }
        return x1.round(MathContext.DECIMAL64);
    }
}
