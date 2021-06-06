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
 * @problem - 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Problem5 {
    
    public static void main(String[] args) {
        System.out.println(smallestAbsoluteDivisor(20));
        //System.out.println(sieveOfEratosthenes(20));
    }
    
   public static List<Integer> sieveOfEratosthenes(int n) {
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    
    public static double smallestAbsoluteDivisor(int limit) {
        int finalNumber = 1; 
        int counter = 0; 
        boolean check = true; 
        int searchLimit = (int) Math.sqrt(limit);
        List<Integer> primeList = sieveOfEratosthenes(limit);
        while(primeList.get(counter) <= limit && counter <= primeList.size() - 1) {
            long exponent = 1;
            if(check) {
                if(primeList.get(counter) <= searchLimit) 
                {
                    exponent = (int) Math.floor(Math.log(limit) / Math.log(primeList.get(counter)));
                }
                else {
                    check = false; 
                }
            }
            finalNumber *= Math.pow(primeList.get(counter), exponent);
            //TODO: Figure out why this is necessary for the program to compile
            if(counter == primeList.size() -1) {
                break;
            }
            else {
                counter += 1; 
            }
        }
        return finalNumber;
    }
}
