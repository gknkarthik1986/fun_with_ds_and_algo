package misc;

import java.util.*;

import javafx.util.Pair;

import java.io.*;


/**
 * Takes input from the STDIN and prints the output back to STDOUT
 * Given N integers determines all non-decreasing subranges for all possible windows of size W
 * @author navank
 *
 */
public class NonDecreasingRangesInWindow {

    int N;
    int K;
    long[] input;
    
    //both the following maps are keyed 
    //by the end offset of the range
    Deque<Pair<Integer, Integer>>  nonDecreasingSubranges;

    Scanner scanner;
    
    NonDecreasingRangesInWindow(){
        
        nonDecreasingSubranges = new LinkedList<Pair<Integer, Integer>>();
        
        scanner = new Scanner(System.in);
        
        System.out.println("Enter N:");
        N = scanner.nextInt();
        
        System.out.println("Enter K:");
        K = scanner.nextInt();
        
        System.out.println("Enter " + N +" numbers:" );
        
        //input = new long[N];
        
        long[] itemsInFirstWindow = readInput(K); 
        computeRangeListFromScratch(itemsInFirstWindow);
        System.out.println(nonDecreasingSubranges);
        long lastItem = itemsInFirstWindow[K-1];
        
        //for each subsequent window after the start window
        for(int windowStart=1; windowStart <= N-K; windowStart++){
        	long nextItem = readInput();
            computeRangeListFromPreviousWindow(windowStart, nextItem, lastItem);
            lastItem = nextItem;
            System.out.println(nonDecreasingSubranges);
        }
    }
    
    public long readInput(){        
        return scanner.nextLong();
    }
    
    public long[] readInput(int n){
        long[] out = new long[n];
        for(int i=0;i<n;i++){
            out[i] = readInput();
        }
        return out;
    }
    
    //computes subranges from scratch
    void computeRangeListFromScratch(long[] input){
    	int startPos = -1;
    	int endPos = 0;
    	for(int i = 1 ; i < K; i++){
    		if(input[i] >= input[i-1]){
    			if(startPos == -1){
    				startPos = i-1;
    			}
    			endPos = i;
    		}else{
    			if(startPos != -1){
    				Pair<Integer, Integer> p = new Pair<>(startPos, endPos);
    				//System.out.println("Adding range : " + p);
    				nonDecreasingSubranges.add(p);
    				//resetting range
        			startPos = -1;
    			}    			
    		}
    	}
    	
    	//add last open range
    	if(startPos != -1){
    		Pair<Integer, Integer> p = new Pair<>(startPos, endPos);
			//System.out.println("Adding range : " + p);
			nonDecreasingSubranges.add(p);
		}    	
    }
    
    void computeRangeListFromPreviousWindow(int curWindowStart, long curWindowLastElement, long prevWindowLastElement){
    	System.out.println("Cur window start " + curWindowStart + " , prev win last " + prevWindowLastElement + ", cur wind last "+ curWindowLastElement);
    	
    	int curWindowEnd = curWindowStart + K - 1;
    	if(curWindowEnd == curWindowStart) return;
        Pair<Integer, Integer> head = nonDecreasingSubranges.poll();
        
        //since the window slides one item at a time we at the most 
        //have to fix/remove the 1st item from the queue
        if(head != null){
        	if(head.getKey() < curWindowStart){
	        	System.out.println("chk1");
	        	
	        	if(head.getValue() > curWindowStart){//to prevent addition of ranges with length 1
	        		System.out.println("chk2");
	        		Pair<Integer, Integer> p = new Pair<>(curWindowStart, head.getValue());
	        		nonDecreasingSubranges.offerFirst(p);
	        	}
        	}else{
        		System.out.println("chk2.1");
        		nonDecreasingSubranges.offerFirst(head);
        	}
    	}
        
        Pair<Integer, Integer> tail = nonDecreasingSubranges.pollLast();
        boolean tailFixed = false;
        if(tail != null){
        	System.out.println("chk3");
        	if(tail.getValue() == curWindowEnd - 1 ){
        		System.out.println("chk4");
        		if(prevWindowLastElement <= curWindowLastElement){
        			System.out.println("chk5");
        			nonDecreasingSubranges.offerLast(new Pair<>(tail.getKey(), tail.getValue()+1));
        			tailFixed = true;
        		}else{
        			System.out.println("chk6");
            		nonDecreasingSubranges.offerLast(tail);
        		}
        	}else{
        		System.out.println("chk7");
        		nonDecreasingSubranges.offerLast(tail);
        	}
        	
        }
        
        if(!tailFixed && prevWindowLastElement <= curWindowLastElement){
        	System.out.println("chk7");
        	//handles the case when the last element in prev window was not part of the queue
        	nonDecreasingSubranges.offerLast(new Pair<>(curWindowEnd - 1, curWindowEnd));   	
        }
    }
    
    public static void main (String[] args) throws java.lang.Exception
    {
    	NonDecreasingRangesInWindow sol = new NonDecreasingRangesInWindow();
    }

}

