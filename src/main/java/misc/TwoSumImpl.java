package misc;

import java.util.*;

class TwoSumImp implements TwoSum{

    HashSet<Integer> set = new HashSet<>();
    HashSet<Integer> sumSet = new HashSet<>();
    
    //O(n)
    public void store(int input){
        for(Integer i: set){
            sumSet.add(i + input);
        }
        
        set.add(input);
    }
    
    //O(1)
    public boolean test(int val){
        return sumSet.contains(val);
        /*
        for(Integer i : set){
            if(set.contains(val - i)){
                return true;
            }
        }
        return false;
        */
    }

}