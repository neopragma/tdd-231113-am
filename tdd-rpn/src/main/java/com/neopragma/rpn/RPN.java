package com.neopragma.rpn;

import java.util.Stack;

public class RPN
{
    private final Stack<String> stack;
    public RPN() {
        stack = new Stack<String>();
    }

    public String enter(String token) {
        if (!isOperator(token) && stack.size() > 1) {
            throw new RuntimeException("Please enter an operator");
        }
        if (isOperator(token)) {
            if (stack.size() == 2) {
                switch(token) {
                    case "+":
                        stack.push(String.valueOf(Double.parseDouble(stack.pop())
                                + Double.parseDouble(stack.pop())));
                        break;
                    case "-":
                        stack.push(String.valueOf(Double.parseDouble(stack.pop())
                                - Double.parseDouble(stack.pop())));
                        break;
                    case "*":
                        stack.push(String.valueOf(Double.parseDouble(stack.pop())
                                * Double.parseDouble(stack.pop())));
                        break;
                    case "/":
                        stack.push(String.valueOf(Double.parseDouble(stack.pop())
                                / Double.parseDouble(stack.pop())));
                        break;
                }
                 return stack.peek();
            } else {
                throw new RuntimeException("Please enter another number");
            }
        }
        stack.push(token);
        return stack.peek();
    }

    private boolean isOperator(String token) {
        return token.equals("+") ||
                token.equals("-") ||
                token.equals("*") ||
                token.equals("/");
    }
}
