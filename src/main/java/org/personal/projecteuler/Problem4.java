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
 * @author Bengamin Dodge
 * @problem - A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */

public class Problem4 {
    
   public static void main(String args[]) {
        System.out.println(palindromicNumberFromDigits(3));
    }

   
   /**
    * Simple method to see if a given number is a palindrome (i.e. that its value is the same if its digits are reversed)
    * 
    * @param n - number to be checked to see if it is a palindrome
    * @return whether the number in question is a palindrome
    */
   
   public static boolean isPalindrome(int n) {
       int reversedNumber = 0; 
       int originalNumber = n;
       while(n > 0) {                                     //Since this uses Java's ints, this is not infinite, as values less than 1 will default to 0
           reversedNumber = 10 * reversedNumber + n % 10; //Reversing the digits
           n /= 10;                                       //Done so the next lowest digit can be reversed
       }
       return (reversedNumber == originalNumber);
   }
   
   /**
    * Iterates through numbers of a given digit length that are multiples of 11, from largest to smallest
    * @param nDigits
    * @return 
    */
   
   public static int palindromicNumberFromDigits(int nDigits) {
       int largestPalindrome = 0;                         //Variable initialization
       int maxValue = 99; 
       int lowestValue = 11;
       int counter = 2; 
       int modifier = 0; 
       while(nDigits > counter) {                         //Done to find the highest and lowest values of a given number of digits (999 and 100 for 3 digits, for example)
           maxValue += 9 * Math.pow(10, counter);   
           //Subtraction done to prevent a series of 1s, and give zeros in all digits but the one furtherst to the left (100 instead of 111, for example)
           lowestValue += Math.pow(10, counter) - 11 * Math.pow(10, counter - 2); 
           counter++;
       } 
       
       //Necessary to create storer variables, as the original variables themselves are changed throughout the program but their original values are still needed
       int lowestValueStorer = lowestValue;
       int maxValueStorer = maxValue;
       
       while(maxValue >= lowestValueStorer) {
           if(maxValue % 11 == 0) {
               lowestValue = maxValueStorer; 
               modifier = 1;                           //lowestValue may or may not be a multiple of 11, so need to analyze every value between them
           }
           else {
               int maxValueCounter = maxValueStorer;
               while(maxValueCounter % 11 != 0) {      //Find the largest number less than the maxValue and divisible by 11
                   lowestValue = maxValue - 1; 
                   maxValueCounter--;
               }
               
               modifier = 11;                          //Iterate through all other multiples of 11
           }
           while(lowestValue >= maxValue) {
               if(lowestValue * maxValue <= largestPalindrome) { //Saves time, as those values can't be the answer (too small) even if their product is a palindromes
                   break;
               }
               if(isPalindrome(lowestValue * maxValue)) {
                   largestPalindrome = lowestValue * maxValue;
               }
               lowestValue -= modifier;                //Analyze next lowest value
           }
           maxValue -= 1; 
       }
       return largestPalindrome; 
   }
}
