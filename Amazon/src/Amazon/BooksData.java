package Amazon;

import java.util.*;


/**
 * This class contains the dataset
 * @author hector.franco
 *
 */
public class BooksData {
	public List<bookInfo> books = new  ArrayList<bookInfo>();
	
	public void add(bookInfo b){
		books.add(b);
	}
	
	/**
	 * 
	 * @return the list of categories. 
	 */
	public String[] getCategories(){
		Set<String> s = new HashSet<String>();
		//List<String> cat = new LinkedList<String>();
		for(bookInfo b : books){
			s.add(b.n_catName);
		}
		//s.toArray(type String)
		String[] arr = s.toArray(new String[s.size()]);
		return arr;
	}
	
	/**
	 * 
	 * @return a diccionary with words for each title. 
	 * if category = "" then do it for all categories. 
	 */
	public  HDicionario getHDicTitle(String category) {
		HDicionario hd = new HDicionario();
		for (bookInfo item : books) {
			if(category.length()>0)
				if (!category.contentEquals(item.n_catName))// if different cathegory, move to next;
					continue;
			String[]  ws = item.n_title.split(" ");
			for (String w : ws) 
				if (w.length()>0)
					hd.addWord(w);		   		
		}
		return hd;
	}
	public  HDicionario getHDicTitle() {return getHDicTitle("");}

}
