package com.rpn.utils;

import com.rpn.calculator.Calculator;
import com.rpn.model.Entity;
import com.rpn.stack.UndoRedoStack;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.rpn.utils.TestUtils.getNumFromStack;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExamplesTest {

    private Calculator rpnCalculator;
    private UndoRedoStack<Entity> stack;

    @Before
    public void setUp() {
        rpnCalculator = Calculator.getInstance();
        stack = Calculator.getStack();
    }

    @After
    public void tearDown() {
        stack.clear();
    }

    @Test
    public void test_rpn_example1() {
        //given
        String expr = "5 2";

        //when
        TestUtils.calculate(rpnCalculator, stack, expr);

        //then
        int numOfElements = stack.size();
        String firstElement = getNumFromStack(stack, 1).toPlainString();
        String secondElement = getNumFromStack(stack, 2).toPlainString();

        assertThat(numOfElements, equalTo(2));
        assertThat(firstElement, equalTo("5"));
        assertThat(secondElement, equalTo("2"));
    }


    @Test
    public void test_rpn_example2() {
        //given
        String expr1 = "2 sqrt";
        String expr2 = "clear 9 sqrt";

        //when
        TestUtils.calculate(rpnCalculator, stack, expr1);

        //then
        BigDecimal firstNumber_1 = getNumFromStack(stack, 1);
        String formattedNumber1 = firstNumber_1.setScale(10, RoundingMode.DOWN).stripTrailingZeros().toPlainString();
        assertThat(formattedNumber1, equalTo("1.4142135623"));


        //when
        TestUtils.calculate(rpnCalculator, stack, expr2);

        //then
        BigDecimal firstNumber2 = getNumFromStack(stack, 1);
        String formattedNumber2 = firstNumber2.setScale(10, RoundingMode.DOWN).stripTrailingZeros().toPlainString();
        assertThat(formattedNumber2, equalTo("3"));
    }

    @Test
    public void test_rpn_example3() {
        //given
        String expr1 = "5 2 -";
        String expr2 = "3 -";
        String expr3 = "clear";

        //when
        TestUtils.calculate(rpnCalculator, stack, expr1);

        //then
        String firstNumber_1 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_1, is("3"));


        //when
        TestUtils.calculate(rpnCalculator, stack, expr2);

        //then
        String firstNumber_2 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_2, equalTo("0"));

        //when
        TestUtils.calculate(rpnCalculator, stack, expr3);

        //then
        assertThat(stack.size(), is(0));

    }

    @Test
    public void test_rpn_example4() {
        //given
        String expr1 = "5 4 3 2";
        String expr2 = "undo undo *";
        String expr3 = "5 *";
        String expr4 = "undo";

        //when
        TestUtils.calculate(rpnCalculator, stack, expr1);

        //then
        assertThat(stack.size(), is(4));

        //when
        TestUtils.calculate(rpnCalculator, stack, expr2);

        //then
        String firstNumber_1 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_1, equalTo("20"));

        //when
        TestUtils.calculate(rpnCalculator, stack, expr3);

        //then
        String firstNumber_2 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_2, equalTo("100"));

        //when
        TestUtils.calculate(rpnCalculator, stack, expr4);

        //then
        String firstNumber_3 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_3, equalTo("20"));

    }


    @Test
    public void test_rpn_example5() {
        //given
        String expr1 = "7 12 2 /";
        String expr2 = "*";
        String expr3 = "4 /";

        //when
        TestUtils.calculate(rpnCalculator, stack, expr1);

        //then
        String firstNumber_1 = getNumFromStack(stack, 1).toPlainString();
        String secondNumber_1 = getNumFromStack(stack, 2).toPlainString();
        assertThat(firstNumber_1, equalTo("7"));
        assertThat(secondNumber_1, equalTo("6"));


        //when
        TestUtils.calculate(rpnCalculator, stack, expr2);

        //then
        String firstNumber_2 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_2, equalTo("42"));

        //when
        TestUtils.calculate(rpnCalculator, stack, expr3);

        //then
        String firstNumber_3 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_3, equalTo("10.5"));

    }


    @Test
    public void test_rpn_example6() {
        //given
        String expr1 = "1 2 3 4 5";
        String expr2 = "*";
        String expr3 = "clear 3 4 -";

        //when
        TestUtils.calculate(rpnCalculator, stack, expr1);

        //then
        assertThat(stack.size(), is(5));


        //when
        TestUtils.calculate(rpnCalculator, stack, expr2);

        //then
        String firstNumber_1 = getNumFromStack(stack, 4).toPlainString();
        assertThat(firstNumber_1, equalTo("20"));
        assertThat(stack.size(), equalTo(4));

        //when
        TestUtils.calculate(rpnCalculator, stack, expr3);

        //then
        String firstNumber_2 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_2, equalTo("-1"));

    }


    @Test
    public void test_rpn_example7() {
        //given
        String expr1 = "1 2 3 4 5";
        String expr2 = "* * * *";

        //when
        TestUtils.calculate(rpnCalculator, stack, expr1);

        //then
        assertThat(stack.size(), is(5));


        //when
        TestUtils.calculate(rpnCalculator, stack, expr2);

        //then
        String firstNumber_1 = getNumFromStack(stack, 1).toPlainString();
        assertThat(firstNumber_1, equalTo("120"));

    }


    @Test
    public void test_rpn_example8() {
        //given
        String expr1 = "1 2 3 * 5 + * * 6 5";

        //when
        TestUtils.calculate(rpnCalculator, stack, expr1);

        //then
        String firstNumber = getNumFromStack(stack, 1).toPlainString();

        assertThat(stack.size(), is(1));
        assertThat(firstNumber, is("11"));


    }




}
