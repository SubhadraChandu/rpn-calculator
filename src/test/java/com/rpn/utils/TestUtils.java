package com.rpn.utils;

import com.rpn.calculator.Calculator;
import com.rpn.exceptions.RPNCalculatorException;
import com.rpn.model.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TestUtils {

    public static String[] splitStringIntoTokens(String string) {
        return string.split("\\s+");
    }

    public static void calculate(Calculator calculator, Stack stack, String expr) {
        int counter = 0;
        String[] tokens = expr.split("\\s+");
        for (String token : tokens) {
            try {
                calculator.parseToken(token, ++counter);
            } catch (RPNCalculatorException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static BigDecimal getNumFromStack(Stack<Entity> stack, int pos) {
        List<BigDecimal> numbers = new ArrayList<>();
        for (Entity entity : stack) {
            numbers.add(entity.getNumber());
        }
        return numbers.get(pos - 1);
    }

    public static void clearStack(Stack stack) {
        stack.clear();
    }
}
