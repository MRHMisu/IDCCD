package ac.du.iit.tokenizer.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import model.Interface;
import model.Method;
import model.SourceFile;

public class Util {
	public static List<SourceFile> getAllFilesFromSourceDirectory(File directoryPath) {
		List<SourceFile> sourceFiles = new ArrayList<SourceFile>();
		new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			//String modifiedPath = path.replace("\",("/"+""));
			String absolutePath = directoryPath+ path;
			sourceFiles.add(new SourceFile("", absolutePath));

		}).explore(directoryPath);
		return sourceFiles;
	}

	public static void writeHeaderFile(String projectName, List<Method> methods, String outputPath) throws IOException {
		String result = "";
		Writer output = new BufferedWriter(new FileWriter(outputPath, true));
		for (Method m : methods) {
			result += m.getMethodID() + "," + m.getMethodName() + "," + m.getFilePath() + "," + m.getStartLine() + ","
					+ m.getEndLine() + '\n';
		}
		output.append(result);
		output.close();

	}

	public static void writeTokenFile(String projectName, List<Method> methods, String outputPath) throws IOException {
		String result = "";
		Writer output = new BufferedWriter(new FileWriter(outputPath, true));
		for (Method m : methods) {
			result += m.getSourceFileID() + "," + m.getMethodID() + "##" + m.getToken() + '\n';
		}
		output.append(result);
		output.close();
	}

	public static void writeInterfaceFile(String projectName, List<Interface> interfaes, String outputPath)
			throws IOException {
		String result = "";
		Writer output = new BufferedWriter(new FileWriter(outputPath, true));
		for (Interface in : interfaes) {
			result += in.getSourceFileID() + "," + in.getMethodID() + "," + in.getMethodName() + ","
					+ in.getReturnType() + "##" + in.getParameterTypes() + "##" + in.getRootWordsofMethodName() + "##"
					+ in.getSynonyms() + '\n';
		}
		output.append(result);
		output.close();
	}

}
