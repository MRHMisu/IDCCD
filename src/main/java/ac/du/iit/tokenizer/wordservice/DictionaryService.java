package ac.du.iit.tokenizer.wordservice;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import util.PropertyFileLoader;

public class DictionaryService {

	public static Set<String> getSynomyns(String rootWord) {

		IDictionary dict = new Dictionary(
				new File(PropertyFileLoader.getRequiredPropertyPathByPropertyName("dictonaryPath")));
		Set<String> list = new HashSet<String>();
		try {
			dict.open();
			IIndexWord idxWordNoun = dict.getIndexWord(rootWord, POS.VERB);
			if (idxWordNoun != null) {
				List<IWordID> listWordNoun = idxWordNoun.getWordIDs();
				for (Iterator<IWordID> ite = listWordNoun.iterator(); ite.hasNext();) {
					IWordID wordID1 = ite.next();
					IWord word = dict.getWord(wordID1);
					ISynset synset = word.getSynset();
					for (IWord w : synset.getWords()) {
						list.add(w.getLemma());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
