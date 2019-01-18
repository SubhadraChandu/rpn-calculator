package com.rpn.utils;

import com.rpn.model.Entity;
import com.rpn.stack.UndoRedoStack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.rpn.utils.RPNConstants.REALNUMBER_PATTERN;

public class RPNUtils {
    public static boolean isRealNumber(String token) {
        Pattern pattern = Pattern.compile(REALNUMBER_PATTERN);
        Matcher matcher = pattern.matcher(token);
        return matcher.find();
    }

    public static void undo(UndoRedoStack<Entity> stack, int i) {
        while (i > 0) {
            stack.undo();
            i--;
        }
    }

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
