package ac.du.iit.tokenizer.wordservice;

import java.util.Set;

public class TestWS {

	public static void main(String[] args) {
		String methodName="binarySearch1";
		Set<String> rootWordsofMethodName = WordService.getRootWords(methodName);
		System.out.println(rootWordsofMethodName);
		Set<String> sysonyms = WordService.getSynonymWords(rootWordsofMethodName);
		System.out.println(sysonyms);
	}

}
