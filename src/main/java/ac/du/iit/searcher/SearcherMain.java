package ac.du.iit.searcher;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.queryparser.classic.ParseException;

import detector.core.CloneDetector;
import detector.model.Bag;
import indexer.InterfaceLoader;
import model.Interface;
import support.PropertyFileLoader;

public class SearcherMain {

	public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
		String indexDirectory = "D:\\Index";// PropertyFileLoader.getRequiredPropertyPathByPropertyName("indexDirectory");
		String interfaceFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("interfaceFilePath");
		List<Interface> interfaces = InterfaceLoader.loadInterfaceFromInterfaceFile(interfaceFilePath);

		float threashold =Float.parseFloat(args[0]);
		String javaHeaderFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("javaHeaderFilePath");
		String javaTokenFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("javaTokenFilePath");
		String outputCloneIDs = PropertyFileLoader.getRequiredPropertyPathByPropertyName("outputCloneIDs");
		String outputCloneIDCodes = PropertyFileLoader.getRequiredPropertyPathByPropertyName("outputCloneIDCodes");

		CloneDetector myCloneDetector = new CloneDetector(threashold, javaHeaderFilePath, javaTokenFilePath,
				javaHeaderFilePath, javaTokenFilePath, outputCloneIDs, outputCloneIDCodes);

		Map<String, Bag> bagMap = myCloneDetector.getBagMap();
		int count = 0;
		for (Interface in : interfaces) {

			Set<String> idSet = Searcher.searchInterface(indexDirectory, in.getReturnType(),
					in.getRootWordsofMethodName(), in.getSynonyms(), in.getParameterTypes());

			Bag targetBag = bagMap.get(in.getMethodID());
			Set<Bag> bagSet = myCloneDetector.getProbableBagSet(idSet, bagMap);

			myCloneDetector.detectReducedClones(bagSet, targetBag, threashold);

		}
		myCloneDetector.outputClone();

		System.out.println(count);

	}

}
