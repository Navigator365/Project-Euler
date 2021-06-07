/*
 * The MIT License
 *
 * Copyright 2021 Benjamin.
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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Benjamin Dodge
 * @problem - 2520 is the smallest number that can be divided by each of the
 * numbers from 1 to 10 without any remainder. What is the smallest positive
 * number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Problem5 {

    public static void main(String[] args) {
        smallestAbsoluteDivisor(20);
    }

    /**
     * Millennia-old algorithm for finding all prime numbers below a limit by marking all composite numbers and their multiples, leaving only primes unmarked
     * See Wikipedia here: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
     * 
     * Note: Obviously not my own idea, but my own implementation of the idea
     * 
     * @param n - number used to find all primes below it 
     * @return list of all prime numbers less than the input n 
     */
    public static List<Integer> sieveOfEratosthenes(int n) {
        boolean prime[] = new boolean[n + 1];       //Create a new boolean array with indexes from 0 to the number 
        Arrays.fill(prime, true);                   //Set these value all to true 
        for (int p = 2; p * p <= n; p++) {          //Square root rule: there are no prime number factors of a given number greater than the square root of that number. Used to reduce runtime.
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {   //Remove all multiples of each composite index - every non-prime multiple of a prime number will be removed, as well as all multiples of a given composite number
                    prime[i] = false;               //Setting non-prime index numbers as false allows them to be removed later
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();    //Where we will put the prime numbers
        for (int i = 2; i <= n; i++) {      //Iterate through list
            if (prime[i]) {                 //If the index value is still true, it's prime, so add it to the list
                primeNumbers.add(i);
            }
        }
        return primeNumbers;    //Return the list 
    }

    /**
     * Iterating through a list of prime factors of a number, this program finds their corresponding exponents to find the minimum possible number divisible by all positive integers less than the limit
     * 
     * @reasoning - First, the lowest possible number (lpn) divisible by all integers less than a given limit must be divisible by the maximum power of a prime factor of the numbers in the sequence. 
     *      For example, for the sequence 1-8, the lpn must be divisible by 8, or 2^3. However, in order for this number to be the lowest possible, it can only have an exponent of 3 for 2:
     *      any greater, and the number has an unneeded factor of 2. To find the appropriate exponent, we first seek to find the exponent that results in a given prime number p equaling the limit.
     *      We have p ^ x power = limit. We can use logarithmic properties to convert the equation into xlog(p) = log(limit). (Notice the application of the power rule). Continuing onward, 
     *      we have x = log(limit)/log(p). Since we want an integer factor, we want an integer exponent, so we take the floor of that expression. Every prime number, raised to that power, will 
     *      result in the minimum possible number of a given prime factor for the product of all such numbers to be the lpn. 
     * @evidence - For 1-10, we have the primes 2, 3, 5, and 7. We then find the largest powers of prime factors, found in 8 = 2^3 and 9 = 3^2. No other number in this sequence is composed of a prime number raised 
     *      to a power greater than 1, so our answer is the product of 2^3, 3^2, 5, and 7, resulting in 2520, the correct answer given in the problem. 
     */
    //Turning the equation primeP ^ x power = limit into xlog(primeP) = log(limit), or x = log(limit)/log(primeP).
    public static void smallestAbsoluteDivisor(int limit) {
        int finalNumber = 1;
        int counter = 0;
        List<Integer> primeList = sieveOfEratosthenes(limit);   //Calling on Eratosthenes 
        while (counter <= primeList.size() - 1) {
            int exponent = (int) Math.floor(Math.log(limit) / Math.log(primeList.get(counter)));  //Floored because we want the nearest integer value, which will produce an integer prime factor.
            finalNumber *= Math.pow(primeList.get(counter), exponent);
            counter += 1;
        }
        System.out.println(finalNumber); //Doing so displays an unaltered value to the console, while returning finalNumber would display it in scientific notation. Not wrong, but I think the number looks cleaner the other way. 
    }
}//test
