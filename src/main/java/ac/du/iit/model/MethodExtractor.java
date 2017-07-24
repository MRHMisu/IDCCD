package ac.du.iit.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.JavaParser;
import com.github.javaparser.Range;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodExtractor {
	public static List<Method> getAllMethods(SourceFile sourceFile) {
		List<Method> methods = new ArrayList<Method>();
		File filePath = new File(sourceFile.getSoureFilePath());
		String sourceFileID = sourceFile.getSourceFileID();
		try {
			new VoidVisitorAdapter<Object>() {
				@Override
				public void visit(MethodDeclaration n, Object arg) {
					String fileName = filePath.getName();
					String path = filePath.getAbsolutePath();
					String methodName = n.getNameAsString();
					String signature = n.getDeclarationAsString().toString();
					String returnType = n.getType().toString();

					NodeList<Parameter> parametersList = n.getParameters();
					List<String> parameters = new ArrayList<String>();
					for (Parameter m : parametersList) {
						parameters.add(m.getType().toString());
					}
					Range r = n.getRange().get();
					int startLine = r.begin.line;
					int endLine = r.end.line;
					int length = endLine - startLine;
					String body = n.getBody().toString();
					String methodCode = signature + '\n' + body;
					methods.add(new Method("", sourceFileID, fileName, path, returnType, methodName, methodCode,
							startLine, endLine, length, parameters));
					super.visit(n, arg);
				}
			}.visit(JavaParser.parse(filePath), null);

		} catch (IOException e) {
			System.out.println("Parsing error occur-->>" + filePath);
			// new RuntimeException(e);
		}

		return methods;
	}

}
