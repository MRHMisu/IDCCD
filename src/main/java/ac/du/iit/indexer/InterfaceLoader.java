package ac.du.iit.indexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.Interface;

public class InterfaceLoader {

	public static List<Interface> loadInterfaceFromInterfaceFile(String interfaceFilePath) {
		List<Interface> interfaceList = new LinkedList<Interface>();
		File file = new File(interfaceFilePath);
		if (file.exists()) {
			String line = "";
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(interfaceFilePath));
				while ((line = reader.readLine()) != null && line.trim().length() > 0) {

					List<String> everyLine = Arrays.asList(line.split("##"));
					String[] firstInfo = everyLine.get(0).split(",");
					String sourceFileID = firstInfo[0];
					String methodID = firstInfo[1];
					String methodName = firstInfo[2];
					String returnType = firstInfo[3];

					// parameter
					String[] parameters = everyLine.get(1).replace("[", "").replace("]", "").split(",");
					List<String> parameterTypes = new ArrayList<String>();
					for (String p : parameters) {
						parameterTypes.add(p);
					}
					// root words

					String[] rootWords = everyLine.get(2).replace("[", "").replace("]", "").split(",");
					Set<String> rootWordsofMethodName = new TreeSet<String>();
					for (String w : rootWords) {
						rootWordsofMethodName.add(w);
					}

					// synonyms

					String[] sysnonyms = everyLine.get(3).replace("[", "").replace("]", "").split(",");
					Set<String> synonyms = new TreeSet<String>();
					for (String s : sysnonyms) {
						synonyms.add(s);
					}

					Interface in = new Interface(sourceFileID, methodID, methodName, returnType, parameterTypes,
							rootWordsofMethodName, synonyms);

					interfaceList.add(in);
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return interfaceList;

	}

	/*public static void main(String[] args) {

		loadInterfaceFromInterfaceFile(
				"D://SoftwareReEngineeringProject//CrossCloneProjects//Fpml//Java_interface.txt");

	}*/

}
