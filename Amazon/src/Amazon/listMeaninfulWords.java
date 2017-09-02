/**
 * 
 */
package Amazon;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author hector.franco
 *
 */
public class listMeaninfulWords {
	//public java.util.Hashtable<String, Integer> D = new java.util.Hashtable<String, Integer>();
	Set<String> setA = new HashSet<String>();

	public listMeaninfulWords() throws FileNotFoundException, IOException{
		String line;
		try (
		    InputStream fis = new FileInputStream("C:\\AAA\\POS.txt");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
			br.readLine();//dismiss first line;
		    while ((line = br.readLine()) != null) {
		        String[] s = line.split("\t");
		        if (s.length<9) continue;
		        if (s[9].contentEquals("Noun") ||
		        	s[9].contentEquals("Verb") ||
		        	s[9].contentEquals("Adverb") ||
		        	s[9].contentEquals("Adjective")) 
		        {
		        	setA.add(s[0]);
		        }
		    }
		  }
		}
	

}
