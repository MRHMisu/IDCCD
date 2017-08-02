package ac.du.iit.detector.model;

public class ClonePairIdCode {
	private String javaMethodID;
	private String javaMethodCode;
	private String cSharpMethodID;
	private String cSharpMethodCode;

	public ClonePairIdCode(String javaMethodID, String javaMethodCode, String cSharpMethodID, String cSharpMethodCode) {
		this.javaMethodID = javaMethodID;
		this.javaMethodCode = javaMethodCode;
		this.cSharpMethodID = cSharpMethodID;
		this.cSharpMethodCode = cSharpMethodCode;
	}

	public String getJavaMethodID() {
		return javaMethodID;
	}

	public String getJavaMethodCode() {
		return javaMethodCode;
	}

	public String getCSharpMethodID() {
		return cSharpMethodID;
	}

	public String getCSharpMethodCode() {
		return cSharpMethodCode;
	}

	@Override
	public String toString() {

		String code = javaMethodID + '\n' + cSharpMethodID + '\n' + "-------------------------------------------" + '\n'
				+ javaMethodCode + '\n' + "----------------------------------------------" + '\n' + cSharpMethodCode
				+ '\n';
		return code;
	}

}