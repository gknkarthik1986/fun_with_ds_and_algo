package misc;

import java.util.*;

/* This class will be given a list of words (such as might be tokenized
 * from a paragraph of text), and will provide a method that takes two
 * words and returns the shortest distance (in words) between those two
 * words in the provided text.
 * Example:
 *   WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
 *   assert(finder.distance("fox", "the") == 3);
 *   assert(finder.distance("quick", "fox") == 1);
 *
 * "quick" appears twice in the input. There are two possible distance values for "quick" and "fox":
 *     (3 - 1) = 2 and (4 - 3) = 1.
 * Since we have to return the shortest distance between the two words we return 1.
 */
public class WordDistanceFinder {

    HashMap<String, List<Integer>> wordOffsets = new HashMap<>();
    public WordDistanceFinder (List<String> words) {
        // implementation here
        int offset = 0;
        for(String w : words){
            List<Integer> lst = wordOffsets.get(words);
            if(lst == null){
                wordOffsets.put(w, lst = new ArrayList<>());
            }
            
            lst.add(offset++);
            
        }
    }
    
    public int distance (String wordOne, String wordTwo) {
        
        // implementation here
        List<Integer> offsetsWord1 = wordOffsets.get(wordOne);
        List<Integer> offsetsWord2 = wordOffsets.get(wordTwo);
        
        int min = Integer.MAX_VALUE, dist;
        for(int i=0, j = 0; i < offsetsWord1.size() && j < offsetsWord2.size();){
            dist = Math.abs(offsetsWord1.get(i) - offsetsWord2.get(j));
            if(dist < min){
                min = dist;
            }
            
            if(offsetsWord1.get(i) > offsetsWord2.get(j)){
                j++;

            }else{
                i++;
            }
        }

        return min;
        
    }
}
