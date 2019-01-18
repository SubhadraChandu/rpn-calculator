package com.rpn.utils;

import com.rpn.model.Entity;
import com.rpn.stack.UndoRedoStack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.rpn.utils.RPNConstants.REALNUMBER_PATTERN;

public class RPNUtils {


    /**
     * Check if the given token is real number or not. It returns true for negatives and the nubers with decimals
     * @param token
     * @return boolean
     */
    public static boolean isRealNumber(String token) {
        Pattern pattern = Pattern.compile(REALNUMBER_PATTERN);
        Matcher matcher = pattern.matcher(token);
        return matcher.find();
    }

    /**
     * Call undo method from the UndoRedoStack data structure.
     * For binary operator - 3 undo calls have to be made
     * For unary operator  - 2 calls have to be made
     * For Numbers - one undo call required
     * @param stack
     * @param i
     */
    public static void undo(UndoRedoStack<Entity> stack, int i) {
        while (i > 0) {
            stack.undo();
            i--;
        }
    }

    /**
     * Print the Numbers from the given stack in required format.
     * All numbers will have precision of 15 digits and only <=10 digits precision will be displayed to the user.
     * @param stack
     */
    public static void printStack(UndoRedoStack<Entity> stack) {
        System.out.print("Stack : ");
        for (Entity entity : stack) {
            BigDecimal number = entity.getNumber();
            String formattedNumber = number.setScale(10, RoundingMode.DOWN).stripTrailingZeros().toPlainString();
            System.out.print(formattedNumber + " ");
        }
        System.out.println();
    }

}
