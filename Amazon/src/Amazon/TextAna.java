package Amazon;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		
		//extendTable e = new extendTable();

		WikipediaLanguageModel wlm = new WikipediaLanguageModel();
		
		listMeaninfulWords lmw = new listMeaninfulWords();
		
		BooksData l = GetRows(-100,lmw);
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
			System.out.println("Category: \t"+ cat);
			System.out.println("Examples: \t" + di.counter );
			di.sortAndPrintValues(20);
			System.out.println("Perplexity: \t"+ di.perplexity());
			System.out.println("averge words title: \t" + l.avgLengthWords(cat));
			System.out.println("averge characters title: \t" + l.avgCharactersTitle(cat));
			System.out.println("averge meaninful words in title: \t" + l.avgMeaninfulWordsTitle(cat));
			System.out.println("averge meaninful characters in title: \t" + l.avgMeaninfulCharactersTitle(cat));
			//System.out.println("DESCRIPTION");
			System.out.println("averge words description: \t" + l.avgLengthWordsDES(cat));
			System.out.println("averge characters description: \t" + l.avgCharactersDES(cat));
			System.out.println("averge meaninful words in description: \t" + l.avgMeaninfulWordsDES(cat));
			System.out.println("averge meaninful characters in description: \t" + l.avgMeaninfulCharactersDES(cat));			
			
			
			
			
			
			
			System.out.println();
		}
		
		showLanguageDist(l);
		l.saveFile();
		System.out.println("END");

		
		
		
		
/*		System.out.println("Prob of " + 1000*hd.probability(""));
		System.out.println("Prob of currents" + 1000*hd.probability("currents"));
		System.out.println("Prob of Facebook" + 1000*hd.probability("facebook"));
		System.out.println("perplexity" + hd.perplexity());
*/
	}
	
	private static void showLanguageDist(BooksData bd){
		java.util.Hashtable<String, Integer> D = new java.util.Hashtable<String, Integer>();
		for(bookInfo b: bd.books){
			String lang = b.lang;
			int counter = 0;
			if (D.containsKey(lang))
				counter = D.get(lang);
			D.put(lang, counter+1);			
		}
		
		for ( Map.Entry<String, Integer> entry : D.entrySet() ) {
			System.out.println(entry.getKey() + " books:"+entry.getValue() + " percentage:" +100*entry.getValue()/(double)bd.books.size()+ "%");

		}
		

		/*for(String l: languages){
			System.out.println(l + ":" +(double)D.get(l)/(double)bd.books.size());
		}*/
		
		
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
		
		int englishBooks = 0;
		int titlesWithNewWords = 0;
		for(bookInfo b :bd.books){
			if (!b.lang.contentEquals("en")) continue;
			boolean neww = false;
			englishBooks++;
			String[]  ws = b.n_title.split(" ");
			for (String w : ws) 
				if (w.length()>0)
				{
					if (!lm.exist(w)){
						System.out.println(w);
						neww = true;
					}
					
				}
			if (neww) titlesWithNewWords++;
						
		}
		System.out.println("Percentage of books with new words: " + (double)titlesWithNewWords/(double)englishBooks*100.0);
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
		s = s.replace("'"," ");
		

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
			else s2+= " ";
		}
		for (int x= 1; x<10;x++){
		s2 = s2.replace("  ", " ");
		s2 = s2.replace("NUM NUM","NUM");
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
	public static BooksData GetRows(int limit,listMeaninfulWords lmw) throws Exception, IOException{
		
		// TODO Auto-generated method stub
		//List<bookInfo> res= new LinkedList<bookInfo>();
		BooksData res =  new BooksData();
		String line;
		//0      1           2        3        4          5                   6          7         8                9         10                11           12        13    14      15      16      17						18		19		          20
	//	"id"	"catName"	"title"	"descr"	"rating"	"nRatings"	"positionInList"	"size"	"pageNum"	"paperPrice"	"kindlePrice"	"hardcPrice"	"r1"	"r2"	"r3"	"r4"	"r5"	"daysSinseRelease"	"Language"	"Reliable"	"Confidence"

		
		try (
		    InputStream fis = new FileInputStream("..\\oneTableData.csv");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
			String t = br.readLine();
			System.out.println(t);
		    while ((line = br.readLine()) != null) {
		    	limit--;
		    
		    	bookInfo b = new bookInfo();		
		    	
		    	
		    	String s[] = line.split("\t");
		    	b.id = s[0];
		    	
//		    	System.out.println(clean(s[2]));
		    	b.n_catName = clean(s[1]);
		    	b.n_title = clean(s[2]);
		    	b.descr = clean(s[3]);
		    	b.rating  = Double.parseDouble(s[4]);
		    	b.nRatings  = Double.parseDouble(s[5]);
		    	
		    	b.lang = s[18];
		    	b.langReliable = s[19];
		    	b.langConf = Double.parseDouble(s[20]);

		    	String[] title = b.n_title.split(" ");
		    	b.title_amount_words = title.length;
		    	
		    	for (int i = 0 ; i<title.length; i++)
		    		if (lmw.setA.contains(title[i]))		    		
		    			b.n_title_meaninfull = b.n_title_meaninfull+ " " + title[i];		    				    	
    			b.n_title_meaninfull = b.n_title_meaninfull.trim();	    		
		    	
    			b.title_amount_meaninful_words =  b.n_title_meaninfull.split(" ").length;
		    	//////now description;
    			
    			String[] des = b.descr.split(" ");
		    	b.descr_amount_words = des.length;
		    	
		    	for (int i = 0 ; i<des.length; i++)
		    		if (lmw.setA.contains(des[i]))		    		
		    			b.descr_meaninfull = b.descr_meaninfull + " " + des[i];		    				    	
    			b.descr_meaninfull = b.descr_meaninfull.trim();	    		
		    	
    			b.descr_amount_meaninful_words =  b.descr_meaninfull.split(" ").length;
    			
    			
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
