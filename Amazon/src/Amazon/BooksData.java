package Amazon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;


/**
 * This class contains the dataset
 * @author hector.franco
 *
 */
public class BooksData {
	public List<bookInfo> books = new  ArrayList<bookInfo>();
	
	public void saveFile() throws IOException{
		File fout = new File("C:\\AAA\\Table2.csv");
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		bw.write("id\t");
		bw.write("descr\t");
		bw.write("descr_amount_meaninful_words\t");
		bw.write("descr_amount_words\t");
		bw.write("descr_meaninfull\t");
		bw.write("lang\t");		
		bw.write("n_catName\t");
		bw.write("title\t");
		bw.write("n_title_meaninfull\t");
		bw.write("title_amount_meaninful_words\t");
		bw.write("title_amount_words\t");
		bw.write("title_non_words\t");
		bw.newLine();


		for (int i = 0; i < books.size(); i++) {
			bw.write(books.get(i).id+"\t");
			bw.write(books.get(i).descr+"\t");
			bw.write(books.get(i).descr_amount_meaninful_words+"\t");
			bw.write(books.get(i).descr_amount_words+"\t");
			bw.write(books.get(i).descr_meaninfull+"\t");
			bw.write(books.get(i).lang+"\t");
			
			bw.write(books.get(i).n_catName+"\t");
			bw.write(books.get(i).n_title+"\t");
			bw.write(books.get(i).n_title_meaninfull+"\t");
			bw.write(books.get(i).title_amount_meaninful_words+"\t");
			bw.write(books.get(i).title_amount_words+"\t");
			bw.write(books.get(i).title_non_words+"\t");

			
			bw.newLine();
			
		}
	 
		bw.close();
	}
	
	public void add(bookInfo b){
		books.add(b);
	}
	
	public  double avgLengthWords(String category) {
		double counter = 0;
		double avg = 0;
		for (int i = 0 ; i<books.size();i++){
			if (!books.get(i).n_catName.contentEquals(category)) continue;
			avg += books.get(i).title_amount_words;
			counter++;
		}
		if (counter>0)
			return avg/counter;
		else return -1;
	}

	public  double avgCharactersTitle(String category) {
		double counter = 0;
		double avg = 0;
		for (int i = 0 ; i<books.size();i++){
			if (!books.get(i).n_catName.contentEquals(category)) continue;
			avg += books.get(i).n_title.length();
			counter++;
		}
		if (counter>0)
			return avg/counter;
		else return -1;
	}
	
	public  double avgMeaninfulWordsTitle(String category) {
		double counter = 0;
		double avg = 0;
		for (int i = 0 ; i<books.size();i++){
			if (!books.get(i).n_catName.contentEquals(category)) continue;
			avg += books.get(i).title_amount_meaninful_words;
			counter++;
		}
		if (counter>0)
			return avg/counter;
		else return -1;
	}

	public  double avgMeaninfulCharactersTitle(String category) {
		double counter = 0;
		double avg = 0;
		for (int i = 0 ; i<books.size();i++){
			if (!books.get(i).n_catName.contentEquals(category)) continue;
			avg += books.get(i).n_title_meaninfull.length();
			counter++;
		}
		if (counter>0)
			return avg/counter;
		else return -1;
	}
//////////////////////////////////////////////////////
	
	
	public  double avgLengthWordsDES(String category) {
		double counter = 0;
		double avg = 0;
		for (int i = 0 ; i<books.size();i++){
			if (!books.get(i).n_catName.contentEquals(category)) continue;
			avg += books.get(i).descr_amount_words;
			counter++;
		}
		if (counter>0)
			return avg/counter;
		else return -1;
	}

	public  double avgCharactersDES(String category) {
		double counter = 0;
		double avg = 0;
		for (int i = 0 ; i<books.size();i++){
			if (!books.get(i).n_catName.contentEquals(category)) continue;
			avg += books.get(i).descr.length();
			counter++;
		}
		if (counter>0)
			return avg/counter;
		else return -1;
	}
	
	public  double avgMeaninfulWordsDES(String category) {
		double counter = 0;
		double avg = 0;
		for (int i = 0 ; i<books.size();i++){
			if (!books.get(i).n_catName.contentEquals(category)) continue;
			avg += books.get(i).descr_amount_meaninful_words;
			counter++;
		}
		if (counter>0)
			return avg/counter;
		else return -1;
	}

	public  double avgMeaninfulCharactersDES(String category) {
		double counter = 0;
		double avg = 0;
		for (int i = 0 ; i<books.size();i++){
			if (!books.get(i).n_catName.contentEquals(category)) continue;
			avg += books.get(i).descr_meaninfull.length();
			counter++;
		}
		if (counter>0)
			return avg/counter;
		else return -1;
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
