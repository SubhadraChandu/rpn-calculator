package com.rpn.calculator;

import com.rpn.exceptions.RPNCalculatorException;
import com.rpn.model.Entity;
import com.rpn.model.OperatorType;
import com.rpn.operators.factory.OperatorsFactory;
import com.rpn.stack.UndoRedoStack;

import java.math.BigDecimal;
import java.util.EmptyStackException;

import static com.rpn.utils.RPNUtils.isRealNumber;
import static com.rpn.utils.RPNUtils.undo;

public class Calculator {

    private static volatile UndoRedoStack<Entity> stack;
    private static Calculator instance;
    private static boolean canPush = true;

    private Calculator() {
        stack = new UndoRedoStack<>();
    }

    public static Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    public static void setCanPush(boolean canPush) {
        Calculator.canPush = canPush;
    }

    public static UndoRedoStack<Entity> getStack() {
        return stack;
    }

    public void parseToken(String token, int counter) throws RPNCalculatorException {
        token = token.toLowerCase();

        switch (token) {
            case "undo":
                performUndo();
                break;
            case "clear":
                performClear();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                performOperation(token, counter);
        }

    }


    private void performOperation(String token, int counter) throws RPNCalculatorException {
        if (isRealNumber(token)) {
            if (canPush) {
                BigDecimal number = new BigDecimal(token);
                stack.push(new Entity(number, OperatorType.NONE));
            }
        } else {
            try {
                OperatorsFactory.getOperator(token).process(stack, counter);
            } catch (IllegalArgumentException exc) {
                System.out.println(exc.getMessage());
            }
        }
    }


    private void performClear() {
        stack.clear();
    }


    private void performUndo() {
        try {
            OperatorType operatorType = stack.peek().getOperatorType();
            switch (operatorType) {
                case BINARY:
                    undo(stack, 3);
                    break;
                case UNARY:
                    undo(stack, 2);
                    break;
                case NONE:
                    undo(stack, 1);
                    break;
                default:
                    System.out.println("Undo cannot be performed on " + operatorType + " operator.");
            }
        } catch (EmptyStackException exc) {
            System.out.println("Cannot perform \"undo\" operation on empty stack.");
        }

    }


}
