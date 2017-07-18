package ac.du.iit.tokenizer.wordservice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wordservice.wordprocess.PascleNameExtractor;
import wordservice.wordprocess.StopwordRemover;
import wordservice.wordprocess.WordStemmer;

public class WordService {

	public static Set<String> getSynonymWords(Set<String> rootWords) {

		Set<String> allRootWordSynonyms = new HashSet<String>();

		for (String word : rootWords) {
			allRootWordSynonyms.addAll(DictionaryService.getSynomyns(word));
		}
		return allRootWordSynonyms;
	}

	public static Set<String> getRootWords(String methodName) {
		Set<String> rootWords = new HashSet<String>();
		methodName = methodName.replaceAll("\\d", "");
		String[] methodNames = methodName.split("_");

		for (String method : methodNames) {
			Set<String> wordsAfterParsingFromPascleCase = PascleNameExtractor
					.getWorldListFromPascleCase(method);
			Set<String> wordsAfterRemovingStopWords = StopwordRemover
					.removeStopWord(wordsAfterParsingFromPascleCase);
			Set<String> wordsAfterStemming = WordStemmer
					.getStemmedWord(wordsAfterRemovingStopWords);
			rootWords.addAll(wordsAfterStemming);
		}

		return rootWords;
	}

}
