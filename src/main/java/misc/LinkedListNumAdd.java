package misc;

import java.util.*;
/**
 * Input :
 * 1 -> 2 -> 3
 * 4 -> 5 -> 6 
 * Output : 5 -> 7 -> 9
 * 
 */
public class LinkedListNumAdd {

	LinkedList<Integer> add(LinkedList<Integer> a, LinkedList<Integer> b){
	    if(a == null || a.isEmpty()){
	        return b;
	    }else if( b == null || b.isEmpty()){
	        return a;
	    }
	    
	    Iterator<Integer> it1 = a.iterator();
	    Iterator<Integer> it2 = b.iterator();
	    
	    LinkedList<Integer> result =  new LinkedList<>();
	    
	    int carry = 0;
	    int aDigit, bDigit, sum;
	    while(it1.hasNext() || it2.hasNext()){
	        if(it1.hasNext()){
	            aDigit = it1.next();
	        }else{
	            aDigit = 0;
	        }
	        
	        if(it2.hasNext()){
	            bDigit = it2.next();
	        }else{
	            bDigit = 0;
	        }
	        //aDigit = it1.next();
	        //bDigit = it2.next();
	        
	        sum = aDigit + bDigit + carry;
	        
	        if(sum >= 10){
	            carry = 1;
	            sum = sum % 10;
	        }else{
	            carry = 0;
	        }
	        
	        result.add(sum);
	    }
	    
	    //handle left overs
	    /*if(it1.hasNext()){
	        copyRemaining(result, it1, carry);
	    }else if(it2.hasNext()){
	        copyRemaining(result, it2, carry);
	    }else */
	    if(carry == 1){
	        result.add(1);
	    }
	    
	    return result;
	}
	
	void copyRemaining(LinkedList<Integer> result, Iterator<Integer> it, int carry){
	
	    int d, sum;
	    while(it.hasNext()){
	        d = it.next();
	        sum = d + carry;
	        
	        if(sum >= 10){
	            carry = 1;
	            sum = sum % 10;
	        }else{
	            carry = 0;
	        }
	        result.add(sum);
	        
	    }//end while
	    
	    if(carry == 1){
	        result.add(1);
	    }
	
	}
}

