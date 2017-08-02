package ac.du.iit.detector.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import detector.model.ClonePairId;
import detector.model.ClonePairIdCode;
import detector.model.FunctionIdPathStarEnd;

public class FileUtil {

	public static List<String> getTokenListFromTokenFile(String tokenFilePath) {
		List<String> tokenList = new ArrayList<String>();
		File file = new File(tokenFilePath);
		if (file.exists()) {
			String line = "";
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(tokenFilePath));
				while ((line = reader.readLine()) != null && line.trim().length() > 0) {
					tokenList.add(line);
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
		return tokenList;

	}

	public static List<ClonePairId> getConePairsId(String filepath) {
		List<ClonePairId> clonePairs = new ArrayList<ClonePairId>();
		File file = new File(filepath);
		if (file.exists()) {
			String line = "";
			BufferedReader reader;
			List<String> everyline;

			try {
				reader = new BufferedReader(new FileReader(filepath));
				while ((line = reader.readLine()) != null) {
					everyline = (Arrays.asList(line.split(",")));
					clonePairs.add(new ClonePairId(everyline.get(0), everyline.get(1)));
				}

			} catch (Exception e) {
			}

		}
		return clonePairs;

	}

	public static List<FunctionIdPathStarEnd> getFunctionIdPathStartEnd(String filepath) {
		List<FunctionIdPathStarEnd> functions = new ArrayList<FunctionIdPathStarEnd>();
		File file = new File(filepath);
		if (file.exists()) {
			String line = "";
			BufferedReader reader;
			List<String> everyline;

			try {
				reader = new BufferedReader(new FileReader(filepath));
				while ((line = reader.readLine()) != null) {
					everyline = (Arrays.asList(line.split(",")));

					functions.add(new FunctionIdPathStarEnd(everyline.get(0), everyline.get(1), everyline.get(2),
							Integer.parseInt(everyline.get(3)), Integer.parseInt(everyline.get(4))));
				}

			} catch (Exception e) {
			}

		}
		return functions;

	}

	public static String getCodeByPathAndLineNumber(String filepath, int start, int end) throws IOException {

		File file = new File(filepath);
		StringBuilder code = new StringBuilder();
		if (file.exists()) {
			try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file))) {

				for (String line = null; (line = lineNumberReader.readLine()) != null;) {
					int currentLineNumber = lineNumberReader.getLineNumber();
					if (currentLineNumber >= start && currentLineNumber <= end) {
						code.append(line + '\n');
					}
				}
			} catch (Exception e) {
			}

		}
		return code.toString();

	}

	public static void writeClonesWithCode(List<ClonePairIdCode> cloneList, String outputResultPath)
			throws IOException {
		String result = "";
		Writer output = new BufferedWriter(new FileWriter(outputResultPath, true));

		for (ClonePairIdCode pair : cloneList) {
			result += pair.toString() + '\n';
		}
		output.append(result);
		output.close();
	}

	public static void writeClones(List<ClonePairId> cloneList, String outputResultPath) throws IOException {
		String result = "";
		Writer output = new BufferedWriter(new FileWriter(outputResultPath, true));

		for (ClonePairId pair : cloneList) {
			result += pair.toString() + '\n';
		}
		output.append(result);
		output.close();
	}
}
