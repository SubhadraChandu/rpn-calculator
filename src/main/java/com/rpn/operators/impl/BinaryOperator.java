package com.rpn.operators.impl;

import com.rpn.calculator.Calculator;
import com.rpn.exceptions.RPNCalculatorException;
import com.rpn.exceptions.RPNInsufficientParamsException;
import com.rpn.model.Entity;
import com.rpn.model.OperatorType;
import com.rpn.operators.Operator;
import com.rpn.stack.UndoRedoStack;

import java.math.BigDecimal;
import java.util.EmptyStackException;

public abstract class BinaryOperator implements Operator<Entity, BigDecimal> {

    @Override
    public void process(UndoRedoStack<Entity> stack, int counter) throws RPNCalculatorException {
        try {
            checkOperands(stack, counter, 2);

            Entity secondEntity = stack.pop();
            Entity firstEntity = stack.pop();

            BigDecimal result = calculate(firstEntity.getNumber(), secondEntity.getNumber());
            stack.push(new Entity(result, OperatorType.BINARY));

        } catch (RPNInsufficientParamsException exc) {
            Calculator.setCanPush(false);
            System.out.println(exc.getMessage() + "\n" +
                    "Elements cannot be added to the stack. Please reset the application.");
        } catch (ArithmeticException | EmptyStackException exc) {
            System.out.println("Cannot perform the operation: " + exc.getMessage());
        }
    }


}
