package algo.sort;

import java.util.Arrays;

public class MergeSort {
	static void sort(int[] in, int start, int end){
		if(end - start < 1){
			return;
		}
		int mid = start + (end - start) / 2;
		System.out.println("Sorting range " + start + " , " + mid);
		sort(in, start, mid);
		System.out.println("Sorting range " + ((int)mid+1) + " , " + end);
		sort(in, mid+1, end);
		System.out.println("Merge ranges [" + start + " , " + mid + "] , [" + ((int)mid+1) + " , " + end + "]");
		merge(in, start, mid, end);
	}
	
	static void merge(int[] in, int start, int mid, int end){
		
		int LIMIT = end - start + 1;
		int[] copy = new int[LIMIT];
		int seg_1_counter = start;
		int seg_2_counter = mid+1;
		
		int i;
		
		for(i = 0 ; i < LIMIT && seg_1_counter <= mid && seg_2_counter <= end ; i++ ){
			if(in[seg_1_counter] <= in[seg_2_counter]){
				copy[i] = in[seg_1_counter++]; 
			}else{
				copy[i] = in[seg_2_counter++];
			}
		}
		
		if(i < LIMIT){
			//copy leftovers
			if(seg_1_counter <= mid){//copy remaining elements from 1st segment
			    System.out.println("Copying " + ((int)LIMIT - i) +" left over elements from seg 1 starting from offset " + seg_1_counter);
				copy(in, seg_1_counter, copy, i, LIMIT - i);
			}else{
			    System.out.println("Copying " + ((int)LIMIT - i) + " left over elements from seg 2 starting from offset " + seg_2_counter);
				copy(in, seg_2_counter, copy, i, LIMIT - i);
			}
		}
		
		copy(copy, 0, in, start, LIMIT);
		
	}
	
	private static void copy(int[] from, int fromStart, int[] to, int toStart, int length){
		for(int i = 0 ; i < length; i++){
			to[toStart++] = from[fromStart++];
		}
	}
	
	
	public static void main(String[] args) throws Exception{
	    int[] toSort = new int[]{1,2,3,4,-1,25,14,99,54,639,4,68,7};
	    sort(toSort, 0, toSort.length - 1 );
	    System.out.println("Sorted array:");
	    System.out.println(Arrays.toString(toSort));
	}
	/*
	public static void main(String[] args) throws Exception{
		
		Console c = System.console();
		if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
		String count = c.readLine("Count of numbers:");
		int countInt = Integer.parseInt(count);
		
		int[] in = new int[countInt];
		
		for(int i=0; i < countInt; i++){
			in[i] = Integer.parseInt(c.readLine());
		}
		
		sort(in, 0, in.length);
		
	}
	*/
}
