package ac.du.iit.tokenizer.wordservice.wordprocess;

import java.util.HashSet;
import java.util.Set;

public class PascleNameExtractor {
	public static Set<String> getWorldListFromPascleCase(String methodName) {
		Set<String> wordList = new HashSet<String>();
		for (String word : methodName.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
			wordList.add(word.toLowerCase());
		}
		return wordList;
	}
}
