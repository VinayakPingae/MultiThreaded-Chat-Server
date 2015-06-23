package primeService.util;

/**
 * Description - This class has been added to check whether the given number which has been passed to it is prime or not.
 * @author Vinayak Subhash Pingale
 * @since 1st March 2015
 * @version 1.0
 */
public class CheckPrime {
	 /**
	  * Description - isPrimeNumber is the function which accepts one number and returns the results.
	  * @param checkQueryNumber - Number to find prime or not.
	  * @return boolean value to check whether the given number is prime or not.
	  */
	 public String isPrimeNumber(int checkQueryNumber){
		 if(checkQueryNumber <= 3) 
			 return "Not Valid";
	        for(int i=2; i<=checkQueryNumber/2; i++){
	            if(checkQueryNumber % i == 0){
	                return "false";
	            }
	        }
	        return "true";
	    }
}
