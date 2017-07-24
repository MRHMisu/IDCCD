package ac.du.iit.model;

import java.util.List;
import java.util.Map;

public class Method {

	private String methodID;
	private String sourceFileID;
	private String fileName;
	private String filePath;
	private String returnType;
	private String methodName;
	private List<String> parameterTypes;
	private String methodCode;
	private int startLine;
	private int endLine;
	private int length;
	private String token;
	private Map<String, Integer> tokenFrequency;

	public Method(String methodID, String sourceFileID, String fileName, String filePath, String signature,
			String methodName, String methodCode, int startLine, int endLine, int length, List<String> parameterTypes) {
		this.methodID = methodID;
		this.sourceFileID = sourceFileID;
		this.fileName = fileName;
		this.filePath = filePath;
		this.returnType = signature;
		this.methodName = methodName;
		this.methodCode = methodCode;
		this.startLine = startLine;
		this.endLine = endLine;
		this.length = length;
		this.parameterTypes = parameterTypes;
	}

	public String getMethodID() {
		return methodID;
	}

	public void setMethodID(String methodID) {
		this.methodID = methodID;
	}

	public String getSourceFileID() {
		return sourceFileID;
	}

	public void setSourceFileID(String sourceFileID) {
		this.sourceFileID = sourceFileID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String signature) {
		this.returnType = signature;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, Integer> getTokenFrequency() {
		return tokenFrequency;
	}

	public void setTokenFrequency(Map<String, Integer> tokenFrequency) {
		this.tokenFrequency = tokenFrequency;
	}

	public List<String> getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(List<String> parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public long getTokenSize() {

		long size = 0;
		for (Map.Entry<String, Integer> entry : this.tokenFrequency.entrySet()) {
			size += entry.getValue();
		}

		return size;
	}

}
