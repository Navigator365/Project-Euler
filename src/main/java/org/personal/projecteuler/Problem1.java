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
 * @author - Benjamin Dodge
 * @problem If we list all the natural numbers below 10 that are multiples of 3
 * or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23. Find the sum of
 * all the multiples of 3 or 5 below 1000.
 */
public class Problem1 {

    public static void main(String[] args) {
        System.out.println(multiplesOf3Or5(1000.0));
    }

    //TODO: Add a formal proof for the formula of the sum of arithematic sequences in the evidence section. 
    //TODO: Use divisibility rules to further improve runtime. 
    /**
     * Find the sum of all factors of 3, 5, and 15 less than the number
     *
     * @update - This originally iterated through all multiples of 3 or 5 and
     * added them together. I used an interesting mathematical relationship
     * shown below to update this method, and the program does run faster,
     * around 0.01s (10% less time) on average. Not much, but enough to make me
     * feel it was worthwhile.
     * @reasoning - The multiples of 3 appear in the following forms: 3 + 6 + 9
     * + 12 + 15 + ... + 3n = 3 * (1 + 2 + 3 + 4 + 5 + ... + n) We can use a
     * formula for the sum of an arithmetic sequence, number of terms * (first
     * term + last term) / 2. But since this sequence is in the form 1 + 2 + 3 +
     * ... + n we have number of terms = n. (first term + last term) / 2 = (1 +
     * n) /2. This results in the final formula 3 * n * (n+1) / 2. To apply this
     * to 5 and 15, simply substitute the 3 for the corresponding constant. Find
     * the sums of the sequences of the multiples of 3 and 5 and sum them. Then,
     * subtract the sum of the sequence of the multiples of 15 from this value.
     * This is because the current value overcounts by one multiples of 15 (one
     * each from the multiples of 3 and 5).
     * @evidence - 3 + 6 + 9 = 18 = (9+3)/ 2 * 3 = 3 * 3 * (4/2) = 18.
     */
    public static double multiplesOf3Or5(double range) {
        double maxFactorOf3 = 0.0;
        double maxFactorOf5 = 0.0;
        double maxFactorOf15 = 0.0;
        if (range % 3.0 == 0.0) {
            maxFactorOf3 = range;
        } else {
            while (range % 3.0 != 0.0) {
                maxFactorOf3 = (range - 1) / 3.0;
                range -= 1.0;
            }
        }

        if (range % 5.0 == 0.0) {
            maxFactorOf5 = range;
        } else {
            while (range % 5.0 != 0.0) {
                maxFactorOf5 = (range - 1) / 5.0;
                range -= 1.0;
            }
        }

        if (range % 15.0 == 0.0) {
            maxFactorOf15 = range;
        } else {
            while (range % 15.0 != 0.0) {
                maxFactorOf15 = (range - 1) / 15.0;
                range -= 1.0;
            }
        }
        double sumOfFactorsOf3 = 3 * maxFactorOf3 * ((maxFactorOf3 + 1.0) / 2.0);
        double sumOfFactorsOf5 = 5 * maxFactorOf5 * ((maxFactorOf5 + 1.0) / 2.0);
        double sumOfFactorsOf15 = 15 * maxFactorOf15 * ((maxFactorOf15 + 1.0) / 2.0);
        return sumOfFactorsOf3 + sumOfFactorsOf5 - sumOfFactorsOf15;
    }
}
