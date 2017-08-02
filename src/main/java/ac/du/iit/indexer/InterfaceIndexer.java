package ac.du.iit.indexer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import model.Interface;
import support.IndexerAndSearcherConstant;

public class InterfaceIndexer {

	private IndexWriter indexWriter;

	public InterfaceIndexer(String indexDirectoryPath) throws IOException {
		Directory directory = FSDirectory.open(Paths.get(indexDirectoryPath));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig indexWritterConfigure = new IndexWriterConfig(analyzer);
		indexWritterConfigure.setOpenMode(OpenMode.CREATE_OR_APPEND);
		indexWriter = new IndexWriter(directory, indexWritterConfigure);
	}

	public void close() throws CorruptIndexException, IOException {
		indexWriter.close();
	}

	private Document getDocument(Interface methodInterface) throws IOException {
		Document document = new Document();
		// index root words of method names

		document.add(new StringField(IndexerAndSearcherConstant.SOURCEFILE_ID, methodInterface.getSourceFileID(), Field.Store.YES));
		document.add(new StringField(IndexerAndSearcherConstant.METHOD_ID, methodInterface.getMethodID(), Field.Store.YES));

		for (String r : methodInterface.getRootWordsofMethodName()) {
			document.add(new StringField(IndexerAndSearcherConstant.ROOT_WORDS_OF_MethodName, r, Field.Store.YES));
		}

		for (String s : methodInterface.getSynonyms()) {
			document.add(new StringField(IndexerAndSearcherConstant.SYNONYMS, s, Field.Store.YES));
		}
		for (String p : methodInterface.getParameterTypes()) {
			document.add(new StringField(IndexerAndSearcherConstant.PARAMETERS_TYPES, p, Field.Store.YES));
		}
		document.add(new StringField(IndexerAndSearcherConstant.RETURN_TYPE, methodInterface.getReturnType(), Field.Store.YES));
		return document;
	}

	private void indexMethod(Interface methodInterface) throws IOException {
		System.out.println("Indexing " + methodInterface.toString());
		Document document = getDocument(methodInterface);
		indexWriter.addDocument(document);
	}

	public int createIndex(List<Interface> methodInterfaces) throws IOException {

		for (Interface _methodInterface : methodInterfaces) {
			indexMethod(_methodInterface);
		}

		return indexWriter.numDocs();
	}

}
