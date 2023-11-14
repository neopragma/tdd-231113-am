package com.neopragma.rpn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RPNTest
{
    private RPN rpn;

    @BeforeEach
    public void beforeEachTestCase() {
        rpn = new RPN();
    }

    @Test
    public void entering_a_number_adds_the_number_to_the_stack() {
        assertEquals("3.75", rpn.enter("3.75"));
    }

    @ParameterizedTest
    @MethodSource("provideValuesForRPN")
    public void entering_an_operator_places_the_result_at_the_top_of_the_stack(
            String value1, String value2, String operator, String expected) {
        rpn.enter(value1);
        rpn.enter(value2);
        assertEquals(expected, rpn.enter(operator));
    }

    private static Stream<Arguments> provideValuesForRPN() {
        return Stream.of(
                Arguments.of("5.5", "7.2", "+", "12.7"),
                Arguments.of("3.5", "15.0", "-", "11.5"),
                Arguments.of("2", "5", "*", "10.0"),
                Arguments.of("2", "10", "/", "5.0")
        );
    }

    @Test
    public void it_handles_a_complex_expression() {
        rpn.enter("5");
        rpn.enter("4");
        rpn.enter("+");
        rpn.enter("2");
        assertEquals("18.0", rpn.enter("*"));
    }
    @Test
    public void when_there_are_too_few_operands_it_throws_an_exception() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            rpn.enter("6.5");
            rpn.enter("+");
        });
        assertEquals("Please enter another number", exception.getMessage());
    }
    @Test
    public void when_there_are_too_many_operands_it_throws_an_exception() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            rpn.enter("6.5");
            rpn.enter("5.0");
            rpn.enter("4.0");
        });
        assertEquals("Please enter an operator", exception.getMessage());
    }

}
