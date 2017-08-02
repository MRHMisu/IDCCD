package ac.du.iit.searcher;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import support.IndexerAndSearcherConstant;

public class InterfaceSearcher {

	IndexReader reader;
	IndexSearcher indexSearcher;
	Analyzer analyzer;
	QueryParser queryParser;
	Query query;

	public InterfaceSearcher(String indexDirectoryPath) throws IOException {
		reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDirectoryPath)));
		indexSearcher = new IndexSearcher(reader);
		analyzer = new StandardAnalyzer();
		queryParser = new QueryParser(indexDirectoryPath, analyzer);
	}

	public TopDocs searchReturnType(String returnTypeQuery) throws IOException, ParseException {
		//return type query
		
		Term term = new Term(IndexerAndSearcherConstant.RETURN_TYPE, returnTypeQuery);
		Query query = new TermQuery(term);
		return indexSearcher.search(query, IndexerAndSearcherConstant.MAX_SEARCH);
	}

	public TopDocs searchParameter(List<String> parameters) throws IOException, ParseException {
		// parameters query
		BooleanQuery.Builder parameterQueryBuilder = new BooleanQuery.Builder();
		for (String p : parameters) {
			Query q = new TermQuery(new Term(IndexerAndSearcherConstant.PARAMETERS_TYPES, p));
			parameterQueryBuilder.add(q, BooleanClause.Occur.SHOULD);
		}
		BooleanQuery parameterBooleanQuery = parameterQueryBuilder.build();
		return indexSearcher.search(parameterBooleanQuery, IndexerAndSearcherConstant.MAX_SEARCH);
	}

	public TopDocs searchSynonyms(Set<String> synonyms) throws IOException, ParseException {
		// synonyms query
		BooleanQuery.Builder synonymsQueryBuilder = new BooleanQuery.Builder();
		for (String s : synonyms) {
			Query q = new TermQuery(new Term(IndexerAndSearcherConstant.SYNONYMS, s));
			synonymsQueryBuilder.add(q, BooleanClause.Occur.SHOULD);
		}
		BooleanQuery synonymsBooleanQuery = synonymsQueryBuilder.build();
		return indexSearcher.search(synonymsBooleanQuery, IndexerAndSearcherConstant.MAX_SEARCH);
	}

	public TopDocs searchRootWords(Set<String> rootWords) throws IOException, ParseException {
		// root word query
		BooleanQuery.Builder rootWordsQueryBuilder = new BooleanQuery.Builder();
		for (String s : rootWords) {
			Query q = new TermQuery(new Term(IndexerAndSearcherConstant.SYNONYMS, s));
			rootWordsQueryBuilder.add(q, BooleanClause.Occur.SHOULD);
		}
		BooleanQuery rootWordsBooleanQuery = rootWordsQueryBuilder.build();
		return indexSearcher.search(rootWordsBooleanQuery, IndexerAndSearcherConstant.MAX_SEARCH);
	}

	public Document getDocument(ScoreDoc scoreDoc) throws CorruptIndexException, IOException {
		return indexSearcher.doc(scoreDoc.doc);
	}

	public void close() throws IOException {
		reader.close();
	}

}
