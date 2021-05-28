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
 *
 * @author Benjamin Dodge
 * @problem - The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */

public class Problem3 {
    
    public static void main(String[] args) {
        //Necessary to use a double value as the test value is too large to be an int
        System.out.println(largestPrimeFactor(600851475143.0));
    }
    
    /**
     * Iterate through all odd numbers between 3 and n, finding the greatest prime factor
     * @reasoning - All even numbers but 2 are not prime, so they don't have to be evaluated.
     *      Whenever a prime factor is found, divide it out to reduce runtime. 
     *      The greatest prime factor is found when dividing the input number by it results in 1. 
     * @evidence - Using the example, dividing out 5, 7, and 13 from 13195, one is left with simply 29. 
     *          Dividing it by 29 confirms that it is the largest prime factor. 
     */
    
    public static double largestPrimeFactor(double n) {
        double primeFactor = 3.0;
        double maxPrimeFactor = 0.0; 
        if(n > 3.0) {                               //Since all even numbers other than 2 are composite, they don't need to be searched
           while(n != 1) {                          //Once n/primeFactor = 1, the maximum prime factor has been found
               if(n % primeFactor != 0.0) {         //Find if value is prime
                   primeFactor += 2.0;              //Incrementing it by 2 to avoid even numbers
               }
               else {                               //Number is composite
                   maxPrimeFactor = n;              //Storing n in case the following line finds n = 1, ending the loop
                   n /= primeFactor;
               }
           }
           return maxPrimeFactor; 
        }
        if(n <= 2.0){                               //Reliability insurance if n is actually smaller than 3 
            return 2.0; 
        }
        else if(n <= 1) throw new RuntimeException("n must be positive and greater than 1 (1 is neither prime or composite, so can't be tested)");
        return 3.0; 
    }
}
