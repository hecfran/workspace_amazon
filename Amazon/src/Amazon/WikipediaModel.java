package Amazon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class WikipediaModel {
	private static void fold(){
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
	
	private static void addFile(HDicionario hd, String file) throws IOException{
		String line;
	
		try (
		InputStream fis = new FileInputStream(file);
	    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	    BufferedReader br = new BufferedReader(isr);
		){
		    while ((line = br.readLine()) != null) {
		    	line = TextAna.clean(line);
				String[]  ws = line.split(" ");
				for (String w : ws) 
					if (w.length()>0)
						hd.addWord(w);		    	
		    }			
		}		
	}
	public static void buildModel() throws FileNotFoundException, IOException{
		
		HDicionario hd = new HDicionario();		
		File folder = new File("..\\raw.en");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) 
		    	  if (listOfFiles[i].getName().charAt(0)!='.')
		    	  {
				        System.out.println("File " + listOfFiles[i].getName()+ " file "+ (i+1) + " of " +listOfFiles.length );
				        System.out.println("hd counter " + hd.counter);
				        System.out.flush();
				        addFile(hd,"..\\raw.en\\"+listOfFiles[i].getName());
		    	  }
		      
		    }
			hd.saveFile("..\\wikipedia_model.txt");
	}
}
