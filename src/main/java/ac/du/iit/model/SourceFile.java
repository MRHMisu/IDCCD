package ac.du.iit.model;

public class SourceFile {

	private String sourceFileID;
	private String soureFilePath;

	public SourceFile(String sourceFileID, String soureFilePath) {
		this.sourceFileID = sourceFileID;
		this.soureFilePath = soureFilePath;
	}

	public String getSourceFileID() {
		return sourceFileID;
	}

	public void setSourceFileID(String sourceFileID) {
		this.sourceFileID = sourceFileID;
	}

	public String getSoureFilePath() {
		return soureFilePath;
	}

	public void setSoureFilePath(String soureFilePath) {
		this.soureFilePath = soureFilePath;
	}

}
