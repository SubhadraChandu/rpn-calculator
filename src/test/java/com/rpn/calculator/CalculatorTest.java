package com.rpn.calculator;

import com.rpn.exceptions.RPNCalculatorException;
import com.rpn.model.Entity;
import com.rpn.stack.UndoRedoStack;
import com.rpn.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    private Calculator calculator;
    private UndoRedoStack<Entity> stack;

    @Before
    public void setUp() {
        calculator = Calculator.getInstance();
        stack = Calculator.getStack();
    }

    @After
    public void tearDown() {
        stack.clear();
    }

    @Test
    public void test_parseToken_number() throws RPNCalculatorException {
        //when
        calculator.parseToken("1", 1);

        //then
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), isA(Entity.class));
    }

    @Test
    public void test_parseToken_operator() throws RPNCalculatorException {
        //when
        calculator.parseToken("1", 1);
        calculator.parseToken("sqrt", 2);

        //then
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), isA(Entity.class));
    }

    @Test
    public void test_parseToken_undo() throws RPNCalculatorException {

        //when
        calculator.parseToken("1", 1);
        calculator.parseToken("2", 2);
        calculator.parseToken("undo", 3);

        //then
        String numFromStack = TestUtils.getNumFromStack(stack, 1).toPlainString();
        assertThat(numFromStack, is("1"));
        assertThat(stack.peek(), isA(Entity.class));
    }

    @Test
    public void test_parseToken_clear() throws RPNCalculatorException {

        //when
        calculator.parseToken("1", 1);
        calculator.parseToken("clear", 2);

        //then
        assertThat(stack.size(), is(0));
    }


    @Test
    public void test_parseToken_char() throws RPNCalculatorException {

        //when
        calculator.parseToken("&", 1);

        //then
        assertThat(stack.size(), is(0));
    }


    @Test
    public void test_parseToken_sqrt() throws RPNCalculatorException {

        //when
        calculator.parseToken("-1", 1);
        calculator.parseToken("sqrt", 2);

        //then
        assertThat(stack.size(), is(0));
    }


    @Test
    public void test_parseToken_0by0() throws RPNCalculatorException {

        //when
        calculator.parseToken("0", 1);
        calculator.parseToken("0", 2);
        calculator.parseToken("/", 3);

        //then
        assertThat(stack.size(), is(0));
    }

    @Test
    public void test_parseToken_1by0() throws RPNCalculatorException {

        //when
        calculator.parseToken("1", 1);
        calculator.parseToken("0", 2);
        calculator.parseToken("/", 3);

        //then
        assertThat(stack.size(), is(0));
    }
}