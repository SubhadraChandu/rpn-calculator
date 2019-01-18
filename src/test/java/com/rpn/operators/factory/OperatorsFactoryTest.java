package com.rpn.operators.factory;

import com.rpn.operators.Operator;
import com.rpn.operators.impl.*;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OperatorsFactoryTest {


    @Test
    public void test_getOperator_add() {
        //when
        Operator operator = OperatorsFactory.getOperator("+");

        //then
        assertThat(operator, new IsInstanceOf(AdditionOperator.class));
        assertThat(operator, new IsInstanceOf(BinaryOperator.class));
        assertThat(operator.getIdentifier(), is("+"));
    }

    @Test
    public void test_getOperator_subtract() {
        //when
        Operator operator = OperatorsFactory.getOperator("-");

        //then
        assertThat(operator, new IsInstanceOf(SubtractionOperator.class));
        assertThat(operator, new IsInstanceOf(BinaryOperator.class));
        assertThat(operator.getIdentifier(), is("-"));
    }

    @Test
    public void test_getOperator_multiply() {
        //when
        Operator operator = OperatorsFactory.getOperator("*");

        //then
        assertThat(operator, new IsInstanceOf(MultiplicationOperator.class));
        assertThat(operator, new IsInstanceOf(BinaryOperator.class));
        assertThat(operator.getIdentifier(), is("*"));
    }

    @Test
    public void test_getOperator_divide() {
        //when
        Operator operator = OperatorsFactory.getOperator("/");

        //then
        assertThat(operator, new IsInstanceOf(DivisionOperator.class));
        assertThat(operator, new IsInstanceOf(BinaryOperator.class));
        assertThat(operator.getIdentifier(), is("/"));
    }

    @Test
    public void test_getOperator_sqrt() {
        //when
        Operator operator = OperatorsFactory.getOperator("sqrt");

        //then
        assertThat(operator, new IsInstanceOf(SqrtOperator.class));
        assertThat(operator, new IsInstanceOf(UnaryOperator.class));
        assertThat(operator.getIdentifier(), is("sqrt"));
    }


}