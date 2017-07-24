package ac.du.iit.tokenizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Interface;
import model.Method;
import model.MethodExtractor;
import model.SourceFile;
import tokenizer.Tokenizer;
import util.PropertyFileLoader;
import util.Util;
import wordservice.WordService;

public class IDCCDTokenizerMain {

	public static void main(String[] args) throws IOException {

		String projectDirectoryPath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("projectDirectoryPath");
		String projectName = PropertyFileLoader.getRequiredPropertyPathByPropertyName("projectName");
		String headerFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("headerFilePath");
		String tokenFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("tokenFilePath");
		String interfaceFilePath = PropertyFileLoader.getRequiredPropertyPathByPropertyName("interfaceFilePath");

		int maximumLine = Integer.parseInt(PropertyFileLoader.getRequiredPropertyPathByPropertyName("maximumLine"));
		int minimumLine = Integer.parseInt(PropertyFileLoader.getRequiredPropertyPathByPropertyName("minimumLine"));
		int maximumToken = Integer.parseInt(PropertyFileLoader.getRequiredPropertyPathByPropertyName("maximumToken"));
		int minimumToken = Integer.parseInt(PropertyFileLoader.getRequiredPropertyPathByPropertyName("minimumToken"));

		List<SourceFile> filePaths = Util.getAllFilesFromSourceDirectory(new File(projectDirectoryPath));
		for (int i = 0; i < filePaths.size(); i++) {
			filePaths.get(i).setSourceFileID(projectName + "_javaSourceFileID_" + i);
		}
		List<Method> globalMethods = new LinkedList<Method>();

		for (SourceFile sf : filePaths) {
			List<Method> _methods = MethodExtractor.getAllMethods(sf);
			globalMethods.addAll(_methods);
		}

		for (int i = 0; i < globalMethods.size(); i++) {
			Method m = globalMethods.get(i);
			Tokenizer tokenizer = new Tokenizer(m.getMethodCode());
			m.setTokenFrequency(tokenizer.getTokenFrequencyMap());
			m.setToken(tokenizer.getToken());
		}

		List<Method> updatedMethod = new ArrayList<Method>();
		for (int i = 0; i < globalMethods.size(); i++) {
			Method m = globalMethods.get(i);
			int length = m.getLength();
			long tokenSize = m.getTokenSize();
			if (length <= maximumLine && length >= minimumLine && tokenSize <= maximumToken
					&& tokenSize >= minimumToken) {
				updatedMethod.add(m);
			}

		}
		
		for(int i = 0; i < updatedMethod.size(); i++){
			Method m = globalMethods.get(i);
			m.setMethodID(projectName + "_javaMethodID_" + i);
		}

		List<Interface> globalInterface = new LinkedList<Interface>();
		for (int i = 0; i < updatedMethod.size(); i++) {
			Method m = updatedMethod.get(i);
			Set<String> rootWordsofMethodName = WordService.getRootWords(m.getMethodName());
			Set<String> sysonyms = WordService.getSynonymWords(rootWordsofMethodName);
			Interface inter = new Interface(m.getSourceFileID(), m.getMethodID(), m.getMethodName(), m.getReturnType(),
					m.getParameterTypes(), rootWordsofMethodName, sysonyms);
			globalInterface.add(inter);
		}

		Util.writeHeaderFile(projectName, updatedMethod, headerFilePath);
		Util.writeTokenFile(projectName, updatedMethod, tokenFilePath);
		Util.writeInterfaceFile(projectName, globalInterface, interfaceFilePath);

	}

}
