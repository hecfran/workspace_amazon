package Amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public class HDicionario {
	public java.util.Hashtable<String, Integer> D = new java.util.Hashtable<String, Integer>();
	public int counter = 0 ;
	public void addWord(String w){
		counter++;
		if (D.containsKey(w))
			D.put(w, D.get(w)+1);
		else D.put(w,1);
	}
	
	public double probability (String s){
		double p = 0;
		if (D.containsKey(s))
			p = D.get(s);
		return p/counter;		
	}
	
	public double perplexity(){
		double total = 0;
		int counter = 0;
		for(String w: D.keySet()){
			double p =probability(w); 
			total += - p* Math.log(p);
			counter++;
		//	System.out.println(w + " " + p);
		}
		return Math.pow(Math.E, total);
		
	}
	
	/**
	 * Sort the values in decreasing frequency and print them.
	 * @param max is the maxim amount of words shown. 
	 */
	 public void sortAndPrintValues(int max){

	       //Transfer as List and sort it
	       ArrayList<Map.Entry<String, Integer>> l = new ArrayList<Entry<String, Integer>>(D.entrySet());
	       Collections.sort(l, new Comparator<Map.Entry<?, Integer>>(){

	           public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
	              return o1.getValue().compareTo(o2.getValue());
	          }});

      
	       Collections.reverse(l);
	       System.out.println(l.subList(0, max));
	    }
	

}
