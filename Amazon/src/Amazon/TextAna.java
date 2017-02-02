package Amazon;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import LanguageTools.WikipediaLanguageModel;
import LanguageTools.langMod;

public class TextAna {

	public static void main(String[] args) throws Exception {
		
		//WikipediaModel.buildModel();
		// TODO Auto-generated method stub
		//System.out.println(clean("32 hola adfadsf 32 hector 33 44"));
	//	GetTitles();
		
	/*	java.util.Hashtable<String, Integer> D=  new java.util.Hashtable<String, Integer>();
		D.put("hi", 33)		;
		System.out.println(D.containsKey("hiaa"));
		System.out.println(D.containsKey("hi"));
*/
//		listFolther();
		
		WikipediaLanguageModel wlm = new WikipediaLanguageModel();
		
		
		BooksData l = GetRows(-100);
		checkNewWordsTitle(l,wlm);
		
		HDicionario hd = l.getHDicTitle();		
		//hd.sortAndPrintValues(20);
		//hd.saveFile("..\\test.txt");
		System.out.println("Perplexity: "+ hd.perplexity());
		System.out.println();
		
		String[] list = l.getCategories();
		System.out.println(list);

		for(String cat: list){
			HDicionario di = l.getHDicTitle(cat);
			System.out.println("Category: "+ cat);
			System.out.println("Examples: " + di.counter );
			di.sortAndPrintValues(20);
			System.out.println("Perplexity: "+ di.perplexity());
			System.out.println();
		}
		
		System.out.println("END");

		
		
		
		
/*		System.out.println("Prob of " + 1000*hd.probability(""));
		System.out.println("Prob of currents" + 1000*hd.probability("currents"));
		System.out.println("Prob of Facebook" + 1000*hd.probability("facebook"));
		System.out.println("perplexity" + hd.perplexity());
*/
	}

	/// this is garbage waiting to be removed. 
	private static void old() throws FileNotFoundException, IOException{
		String line;
		//0      1           2        3        4          5                   6          7         8                9         10                11           12        13    14      15      16      17
		//"id"	"catName"	"title"	"descr"	"rating"	"nRatings"	"positionInList"	"size"	"pageNum"	"paperPrice"	"kindlePrice"	"hardcPrice"	"r1"	"r2"	"r3"	"r4"	"r5"	"daysSinseRelease"
		try (
		    InputStream fis = new FileInputStream("C:\\Users\\hector.franco\\workspace_amazon\\oneTableDataForHector.txt");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
			br.readLine();
		    while ((line = br.readLine()) != null) {
		    	String s[] = line.split("\t");
		    	
		    	for(int i=0;i<s.length;i++)
		    		System.out.println(s[i]);
		    }
		}		
	}
	
	private static boolean isNum(char c){
		return (c >= '0' && c <='9');
	}
	
	
	
	public static void checkNewWordsTitle (BooksData bd,langMod lm){
		for(bookInfo b :bd.books){
			String[]  ws = b.title.split(" ");
			for (String w : ws) 
				if (w.length()>0)
				{
					if (!lm.exist(w))
						System.out.println(w);		
				}
						
		}
	}
			
			
	/**
	 * Changes numbers to a tag NUM
	 * @param s
	 * @return
	 */
	private static String reduceNumbers(String s){
		String l="";
		boolean Number= false;
		boolean current = false;
		for(int i = 0 ; i<s.length();i++){			
			current = isNum(s.charAt(i));
			if (current == false && Number == true){
				l += "NUM";
				Number = false;				
			}
			if (current)
				Number = true;
			else
				l = l+s.charAt(i);			
		}
		if (current)
			l += "NUM";
		return l;
	}
	
	/**
	 * Clean a string from strange characters
	 * @param s original string
	 * @return
	 */
	public static String clean (String s){
		
		s = s.toLowerCase();
		s = s.replace("'"," "); 
		s = s.replace(":","");
		s = s.replace("("," ");
		s = s.replace(")"," ");
		s = s.replace("\""," ");

		s = reduceNumbers(s);
		s = s.replace(" one ","NUM");		
		s = s.replace(" two ","NUM");
		s = s.replace(" three ","NUM");
		s = s.replace(" four ","NUM");
		s = s.replace(" five ","NUM");
		s = s.replace(" six ","NUM");
		s = s.replace(" seven ","NUM");
		s = s.replace(" nine ","NUM");
		s = s.replace(" ten ","NUM");
		s = s.replace(" eleven ","NUM");
		s = s.replace(" twelve ","NUM");
		
		s = s.replace("NUM/NUM","NUM");
		s = s.replace("NUM\\NUM","NUM");		
		
		s = s.replace("-NUM","NUM");
		s = s.replace(".NUM","NUM");
		s = s.replace("NUM.","NUM");
		s = s.replace(",NUM","NUM");
		s = s.replace("NUM,","NUM");

		
		
		
		s = s.replace("NUMNUM","NUM");
		s = s.replace("NUMNUM","NUM");
		s = s.replace("NUMNUM","NUM");
		s = s.replace("NUMNUM","NUM");
		
		s = s.replace("NUM NUM","NUM");		s = s.replace("NUM NUM","NUM");		s = s.replace("NUM NUM","NUM");
		s = s.replace("NUM&NUM,","NUM");
		s = s.replace("NUM & NUM,","NUM");
		
		s = s.replace("NUMth","NUM");
		s = s.replace("NUM"," NUM ");

		s = s.replace("  "," ");		s = s.replace("  "," ");		s = s.replace("  "," ");
		s = s.replace("  "," ");
		s = s.replace("  "," ");
		s = s.replace("  "," ");
		s = s.replace("  "," ");

		s = s.trim();
		s = s.trim();
		s = s.trim();
		s = s.trim();
		
		
		String s2 = "";
		for(int i = 0 ; i<s.length();i++){
			char c = s.charAt(i);
			if (c>= 'a' && c<='z'|| c>= 'A' && c<='Z'|| c == ' '|| c == '\'')
				s2 += c;
		}

		
		
		return s2;
		
	}
	
	
	
		
	
	public static void GetTitles() throws Exception, IOException{
		// TODO Auto-generated method stub

		String line;
		//0      1           2        3        4          5                   6          7         8                9         10                11           12        13    14      15      16      17
		//"id"	"catName"	"title"	"descr"	"rating"	"nRatings"	"positionInList"	"size"	"pageNum"	"paperPrice"	"kindlePrice"	"hardcPrice"	"r1"	"r2"	"r3"	"r4"	"r5"	"daysSinseRelease"
		try (
		    InputStream fis = new FileInputStream("C:\\Users\\hector.franco\\workspace_amazon\\oneTableDataForHector.txt");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
			br.readLine();
		    while ((line = br.readLine()) != null) {
		    	String s[] = line.split("\t");
		    	System.out.println(clean(s[2]));
		    }
		}
	}
	
	
	//public static List<bookInfo> GetRows() throws Exception, IOException{
	public static BooksData GetRows(int limit) throws Exception, IOException{
		
		// TODO Auto-generated method stub
		//List<bookInfo> res= new LinkedList<bookInfo>();
		BooksData res =  new BooksData();
		String line;
		//0      1           2        3        4          5                   6          7         8                9         10                11           12        13    14      15      16      17
		//"id"	"catName"	"title"	"descr"	"rating"	"nRatings"	"positionInList"	"size"	"pageNum"	"paperPrice"	"kindlePrice"	"hardcPrice"	"r1"	"r2"	"r3"	"r4"	"r5"	"daysSinseRelease"
		try (
		    InputStream fis = new FileInputStream("..\\oneTableDataForHector.txt");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
			br.readLine();
		    while ((line = br.readLine()) != null) {
		    	limit--;
		    
		    	bookInfo b = new bookInfo();		    	
		    	String s[] = line.split("\t");
//		    	System.out.println(clean(s[2]));
		    	b.catName = clean(s[1]);
		    	b.title = clean(s[2]);
		    	b.descr = clean(s[3]);
		    	b.rating  = Double.parseDouble(s[4]);
		    	b.nRatings  = Double.parseDouble(s[5]);
		    	res.add(b);
		    	if (limit == 0) break;
		    }
		}
		return res;
	}

	private static void listFolther(){
		File folder = new File("..");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }	
	}
}
