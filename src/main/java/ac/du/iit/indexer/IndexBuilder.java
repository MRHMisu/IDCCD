package ac.du.iit.indexer;

import java.io.IOException;
import java.util.List;

import model.Interface;

public class IndexBuilder {

	public static void buildIndex(String indexDirectory, List<Interface> methodInterfaces) throws IOException {
		InterfaceIndexer indexer = new InterfaceIndexer(indexDirectory);
		int numberOfIndex;
		long startTime = System.currentTimeMillis();
		numberOfIndex = indexer.createIndex(methodInterfaces);
		long endTime = System.currentTimeMillis();
		indexer.close();
		System.out.println(numberOfIndex + " interfaces are indexed within in " + (endTime - startTime) + " ms");
	}

}
