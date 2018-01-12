package misc;

public class PrintCommonElements {
	/**
	 * Given 3 sorted arrays
	 * a: [-10, -8, -5, -4, 0, 1, 3, 3, 3, 6, 6, 6, 10, 20, 20, 50]
	 * b: [-12, -11, -5, -3, 0, 2, 3, 3, 3, 6, 6, 10, 10, 20, 20, 50, 400]
	 * c: [-13, -10, -5, -2, -3, 0, 1, 3, 3, 3, 6, 10, 10, 10, 20, 20, 50, 4000]
	 * Output: -5, 0, 3, 3, 3, 6, 10, 20, 20, 50
	 */
	public void printCommon(int[] a, int[] b, int[] c){
	    if(a == null || b == null || c == null){
	        return;
	    }
	    int i = 0, j = 0 , k = 0;
	    while(i < a.length && j < b.length && k < c.length){
	        if(a[i] == b[j] && b[j] == c[k]){
	            print(a[i]);
	            i++;
	            j++;
	            k++;
	        }else{
	            int max = max(a[i], b[j], c[k]);
	            i = incrementCounter(a, i, max);
	            j = incrementCounter(b, j, max);
	            k = incrementCounter(c, k, max);
	        }
	    }
	}

	int max(int a, int b , int c){
	    return Math.max(Math.max(a,b),c);
	}

	int incrementCounter(int[] arr, int start, int item){
	    while(start < arr.length && arr[start] < item){
	        start++;
	    }
	    return start;
	}
	
	void print(int x){
		//todo
	}

}
