package ac.du.iit.detector.core;

import java.io.IOException;
import java.text.ParseException;

import support.PropertyFileLoader;

public class Main {

	public static void main(String[] args) throws ParseException, IOException {
		float threashold = (float) 1;
		String javaHeaderFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("javaHeaderFilePath");
		String javaTokenFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("javaTokenFilePath");
		String outputCloneIDs = PropertyFileLoader.getRequiredPropertyPathByPropertyName("outputCloneIDs");
		String outputCloneIDCodes = PropertyFileLoader.getRequiredPropertyPathByPropertyName("outputCloneIDCodes");
		
		CloneDetector myCloneDetector = new CloneDetector(threashold, javaHeaderFilePath, javaTokenFilePath,
				javaHeaderFilePath, javaTokenFilePath, outputCloneIDs, outputCloneIDCodes);

		myCloneDetector.detectClone();

	}

}
