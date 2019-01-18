/**
 * RPN Calculator console application
 * @author Subhadra Chandu
 */



package com.rpn.application;

import com.rpn.calculator.Calculator;
import com.rpn.exceptions.RPNCalculatorException;

import java.util.Scanner;

import static com.rpn.utils.RPNUtils.printStack;


public class CalculatorApplication {

    //to keep tracking the position number of operator and operand
    private static int counter;

    public static void main(String[] args) throws RPNCalculatorException {
        System.out.print("Please enter the RPN expression : ");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String readString = scanner.nextLine();

            if (!readString.isEmpty()) {
                String[] tokens = readString.split("\\s+");
                for (String token : tokens) {
                    counter++;
                    Calculator.getInstance().parseToken(token, counter);
                }

                printStack(Calculator.getStack());
            }
        }
    }
}

