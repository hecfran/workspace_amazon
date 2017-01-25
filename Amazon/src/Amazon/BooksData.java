package Amazon;

import java.util.LinkedList;
import java.util.List;

public class BooksData {
	public List<bookInfo> books = new  LinkedList<bookInfo>();
	
	public void add(bookInfo b){
		books.add(b);
	}
}
