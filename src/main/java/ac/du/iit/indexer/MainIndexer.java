package ac.du.iit.indexer;

import java.io.IOException;
import java.util.List;

import model.Interface;
import support.PropertyFileLoader;

public class MainIndexer {

	public static void main(String[] args) throws IOException {

		String indexDirectory = "D:\\Index";//PropertyFileLoader.getRequiredPropertyPathByPropertyName("indexDirectory");
		String interfaceFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("interfaceFilePath");
		List<Interface> interfaces = InterfaceLoader.loadInterfaceFromInterfaceFile(interfaceFilePath);
		IndexBuilder.buildIndex(indexDirectory, interfaces);

	}

}
