package ac.du.iit.searcher;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import support.IndexerAndSearcherConstant;

public class Searcher {

	public static Set<String> searchInterface(String indexDirectory, String returnType, Set<String> rootWords,
			Set<String> synonyms, List<String> parameters) throws IOException, ParseException {

		InterfaceSearcher searcher = new InterfaceSearcher(indexDirectory);
		long startTime = System.currentTimeMillis();
		TopDocs hitsReturnType = searcher.searchReturnType(returnType);
		TopDocs hitsRootWords = searcher.searchRootWords(rootWords);
		TopDocs hitsSynonyms = searcher.searchSynonyms(synonyms);
		TopDocs hitsParameters = searcher.searchParameter(parameters);

		Set<String> globalMethodIds = new TreeSet<String>();
		getMethodIDs(searcher, hitsReturnType, globalMethodIds);
		getMethodIDs(searcher, hitsRootWords, globalMethodIds);
		getMethodIDs(searcher, hitsSynonyms, globalMethodIds);
		getMethodIDs(searcher, hitsParameters, globalMethodIds);
		long endTime = System.currentTimeMillis();
		System.out.println(globalMethodIds.size() + " documents found. Time :" + (endTime - startTime));
		searcher.close();

		return globalMethodIds;
	}

	private static void getMethodIDs(InterfaceSearcher searcher, TopDocs hits, Set<String> set) {
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc;
			try {
				doc = searcher.getDocument(scoreDoc);
				set.add(doc.get(IndexerAndSearcherConstant.METHOD_ID));
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
