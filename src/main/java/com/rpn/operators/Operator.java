package com.rpn.operators;

import com.rpn.exceptions.RPNCalculatorException;
import com.rpn.exceptions.RPNInsufficientParamsException;
import com.rpn.stack.UndoRedoStack;

import java.util.List;

public interface Operator<E, T> {
    String getIdentifier();

    void process(UndoRedoStack<E> stack, int counter) throws RPNCalculatorException;

    default T calculate(T operand) throws RPNCalculatorException {
        return null;
    }

    default T calculate(T firstOperand, T secondOperand) throws RPNCalculatorException {
        return null;
    }

    default T calculate(List<T> operands) throws RPNCalculatorException {
        return null;
    }


    default void checkOperands(UndoRedoStack<E> stack, int counter, int numOfOperands) throws RPNInsufficientParamsException {
        int numOfEntities = stack.size();
        if (numOfEntities == 0 || numOfEntities < numOfOperands) {
            throw new RPNInsufficientParamsException("operator " + getIdentifier() + " (position: " + counter + "): insufficient parameters");
        }
    }


}
