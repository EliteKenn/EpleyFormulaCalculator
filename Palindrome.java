/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

/**
 *
 * @author Kennedy.Nwoke43
 */
import java.util.Scanner;
public class Palindrome {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String word;
        System.out.print("Enter a word:");
        word = input.nextLine();
        isPalindrome(word);
        char[] arr = word.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        String newString = new String(arr);
        
        
        if(isPalindrome(word) == true){
            System.out.println(newString +" is a palindrome");
        
         }if (isPalindrome(word) == false)
            System.out.println(newString + " is not a palindrome");
    }
    
    public static boolean isPalindrome(String word){
        int low = 0;
        int high = word.length() -1;
        while(low < high){
            if(word.charAt(low) != word.charAt(high)){
            return false;   
            }
            low++;
            high--;
        }
      return true;  
        
    }
    

    
       
    
    
   
    }

