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
 * @author Benjamin Dodge
 * @problem - Find the difference between the sum of the squares of the first
 * one hundred natural numbers and the square of the sum.
 */
public class Problem6 {

    //Note: This works only for even number inputs, though it solves the given problem.
    public static void main(String[] args) {
        System.out.println(sumSquareDifference(100));
    }

    //TODO: Make this work for odd numbers. 
    /**
     * Group numbers into this sequence into squares, and exploit relationships
     * between them to quickly sum and solve.
     *
     * @reasoning - This problem has two parts: the sum of squares (1^2 +
     * 2^2+...+n^2) and the square of the sum (1+2+...+n)^2. We'll start with
     * the square of the sum, where we can group pairs of numbers into sums of n
     * + 1. We have 1 + n, 2 + n-1, and so on. Since every number has a pair
     * (there's an even number of numbers), there are n/2 pairs, each with the
     * value of n+1. So, the square of the sum is (n/2 * n+1)^2. Moving on to
     * the sum of squares, we can do the same pair grouping, but the values will
     * not all be the same. The middle pair, being the numbers that are closest
     * together, have the smallest value. Every other value in this series is
     * greater than the next-lowest by a multiple of 4, which increases by 1 for
     * every pair. We can use this relationship to find the value of these
     * pairs, reducing our calculating work by more than half. Then, subtract
     * the two numbers, and return the answer.
     * @evidence - Using the problem's sample series of 1-10, we have the square
     * of the sum as (n/2 * n+1)^2 = (5*11)^2 = 3025, which is correct according
     * to the problem's description. We have the sum of the squares as 5^2 + 6^2
     * = 61, and so on, for a sequence of sums of 61, 65, 73, 85, and 101. These
     * numbers increase by 4, 8, 12, and 16, all multiples of 4 increased from
     * the previous one by 4.
     */
    public static int sumSquareDifference(int maxNumber) {
        int sumSquare = (int) (Math.pow(maxNumber / 2, 2) + Math.pow((maxNumber / 2) + 1, 2));
        int storer = sumSquare;         //Used to increase the delta between sumSquare pairs by 4, according to the reasoning and description above. 
        int squareSum = (int) Math.pow(((maxNumber / 2) * (maxNumber + 1)), 2);
        for (int i = 1; i < maxNumber / 2; i++) { //50 - n/2
            if (i != 1) {        //Needed, as the following results in storer being set to 0, as 4 * 0 = 0.
                storer += 4 * (i - 1);    //Used to increase the delta between pairs by 4. 
            }
            sumSquare += storer + 4 * i;   //Adds the next highest sumSquare pair. 
        }
        return squareSum - sumSquare;
    }
}
