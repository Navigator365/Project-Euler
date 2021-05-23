/*
 * The MIT License
 *
 * Copyright 2021 Benjamin Dodge.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.personal.projecteuler;

/**
 * @author dodge
 */
public class Problem2 {

    public static void main(String args[]) {
        System.out.println(evenFibSum(4000000));
    }

    /**
     * Iterate through a series defined by a recursive function
     * @reasoning - The even values of the Fibonacci series form the following 
     *  recursive function: F(n) = 4F(n-1) + F(n-2)
     * @evidence - Take the sequence 2, 8, 34, 144 of the Fibonacci series.
     *  34 = 4(8) + 2 and 144 = 4(34) + 8. 
     * 
     */
    //TODO: Write a formal proof for this. 
    public static int evenFibSum(int maxVal) {
        int firstVal = 2;           //Equivalent to F(n-2)
        int secondVal = 8;          //Equivalent to 4F(n-1)
        int functionVal = 34;       //Equivalent to F(n)
        int sum = 10; 
        while (functionVal < maxVal) {
            sum += functionVal;
            firstVal = secondVal;  //Swapping values to continue the recursion
            secondVal = functionVal;
            functionVal = 4 * secondVal + firstVal; //Using the formula
        }
        return sum;
    }
}
