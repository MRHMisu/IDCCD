package ac.du.iit.tokenizer.wordservice.wordprocess;

import java.util.HashSet;
import java.util.Set;

public class WordStemmer {
	private static PorterStemmer stemmer = new PorterStemmer();

	public static String getStemmedWord(String word) {
		stemmer.reset();
		stemmer.stem(word);
		return stemmer.toString();
	}

	public static Set<String> getStemmedWord(Set<String> wordList) {
		Set<String> stemmedWord = new HashSet<String>();
		for (String word : wordList) {
			stemmedWord.add(getStemmedWord(word));
		}
		return stemmedWord;

	}
}
