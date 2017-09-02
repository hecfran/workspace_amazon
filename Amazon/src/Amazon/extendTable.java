package Amazon;

import java.io.IOException;
import java.util.Hashtable;

public class extendTable {
	BooksData l;
	public extendTable(listMeaninfulWords lmw) throws Exception{
		l = TextAna.GetRows(-100, lmw);
		calcNonWords();
		
	/*	for(int x= l.books.size(); x>1; x--){
			if (l.books.get(x).title_non_words==0)
				l.books.remove(x);
		}*/
		for (bookInfo b: l.books){
			if (b.title_non_words>0)
				System.out.println(b.non_words);
		}

//		bookInfo[] s = (bookInfo[]) l.books.toArray();
//		System.out.println(s);
		System.out.println("hi");
	}
	
	public void calcNonWords() throws IOException{
		Hashtable<String, Integer> d = WikipediaModel.getEnglishDicc(3);
		for(bookInfo b: l.books){
			b.title_non_words = 0;
			String[] words = b.n_title.split(" ");
			for(String w: words){
				if (!d.containsKey(w))
					if (w.length()>2){
						b.title_non_words++;
						b.non_words.add(w);
					}
					
			}
		}
		
	}
	
	
	

}
