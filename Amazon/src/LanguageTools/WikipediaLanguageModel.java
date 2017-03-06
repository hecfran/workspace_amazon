package LanguageTools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import Amazon.TextAna;

public class WikipediaLanguageModel implements langMod {
	private String file = "..\\wikipedia_model.txt" ; // File containing the table of words that apear at least 2 times.
	private java.util.Hashtable<String, Integer> D = new java.util.Hashtable<String, Integer>();
	public final int counter;
	
	/**
	 * 
	 * @param w word the user wants to check if exist
	 * @return true if the word apears at least 2 times in the language model
	 */
	public boolean exist(String w){
		return D.containsKey(w);
	}
	public WikipediaLanguageModel() throws FileNotFoundException, IOException{
		String line;
		try (
				InputStream fis = new FileInputStream(file);
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);				
				){
				    while ((line = br.readLine()) != null) {
				    	//System.out.println(line);
						String[]  l = line.split("\t");
						if (l.length!=2) continue;
						D.put(l[0],Integer.parseInt(l[1]));	    	
				    }			
				}
		counter = D.size();
		
	}
}
