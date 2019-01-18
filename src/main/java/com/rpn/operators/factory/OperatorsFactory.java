package com.rpn.operators.factory;

import com.rpn.operators.Operator;
import com.rpn.operators.impl.*;

public class OperatorsFactory {
    public static Operator getOperator(String symbol) {
        switch (symbol) {
            case "+":
                return AdditionOperator.getInstance();
            case "-":
                return SubtractionOperator.getInstance();
            case "*":
                return MultiplicationOperator.getInstance();
            case "/":
                return DivisionOperator.getInstance();
            case "sqrt":
                return SqrtOperator.getInstance();
            default:
                throw new IllegalArgumentException("\"" + symbol + "\"" + " is neither a Number nor an Operator. \n");
        }


    }
}
