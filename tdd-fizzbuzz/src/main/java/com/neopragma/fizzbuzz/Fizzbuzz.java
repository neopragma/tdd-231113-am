package com.neopragma.fizzbuzz;

public class Fizzbuzz 
{
    public String processNumber(int number) {
        if (number % 3 ==0) {
            return "Fizz";
        }
        return String.valueOf(number);
    }
}
