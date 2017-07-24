package ac.du.iit.model;

import java.util.List;
import java.util.Set;

public class Interface {

	private String sourceFileID;
	private String methodID;
	private String methodName;
	private String returnType;
	private List<String> parameterTypes;
	private Set<String> rootWordsofMethodName;
	private Set<String> synonyms;

	public Interface(String sourceFileID,String methodID,String methodName, String returnType,
			List<String> parameterTypes, Set<String> rootWordsofMethodName, Set<String> synonyms) {

		this.methodID = methodID;
		this.sourceFileID = sourceFileID;
		this.methodName = methodName;
		this.returnType = returnType;
		this.parameterTypes = parameterTypes;
		this.rootWordsofMethodName = rootWordsofMethodName;
		this.synonyms = synonyms;
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

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<String> getParameterTypes() {
		String res=parameterTypes.toString();
		res=res.replace("[","").replace("]", "");
		return parameterTypes;
	}

	public void setParameterTypes(List<String> parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Set<String> getRootWordsofMethodName() {
		return rootWordsofMethodName;
	}

	public void setRootWordsofMethodName(Set<String> rootWordsofMethodName) {
		this.rootWordsofMethodName = rootWordsofMethodName;
	}

	public Set<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<String> synonyms) {
		this.synonyms = synonyms;
	}

}
