package LanguageTools;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class WikipediaLanguageModelTest {


	@Test
	public void testWikipediaLanguageModel() throws FileNotFoundException, IOException {
		WikipediaLanguageModel wlm = new WikipediaLanguageModel();
		String[] knownWords = {"sun","planet", "language","stone","can","please","run"};
		for(String w : knownWords){
			if(!wlm.exist(w))
				fail(w+" does not exist");
		}
		//fail("Not yet implemented");
	}

}
