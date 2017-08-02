package ac.du.iit.detector.model;

public class ClonePairId {

	private String javaMethodID;
	private String cSharpMethodID;

	public ClonePairId(String javaMethodID, String cSharpMethodID) {

		this.javaMethodID = javaMethodID;
		this.cSharpMethodID = cSharpMethodID;
	}

	public String getJavaMethodID() {
		return javaMethodID;
	}

	public void setJavaMethodID(String javaMethodID) {
		this.javaMethodID = javaMethodID;
	}

	public String getcSharpMethodID() {
		return cSharpMethodID;
	}

	public void setcSharpMethodID(String cSharpMethodID) {
		this.cSharpMethodID = cSharpMethodID;
	}

	@Override
	public String toString() {
		return javaMethodID + "," + cSharpMethodID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (javaMethodID.length() ^ (cSharpMethodID.length() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClonePairId other = (ClonePairId) obj;
		if ((this.javaMethodID == other.cSharpMethodID) && (this.cSharpMethodID == other.javaMethodID))
			return true;

		if (this.javaMethodID != other.javaMethodID)
			return false;
		if (this.cSharpMethodID != other.cSharpMethodID)
			return false;

		return true;
	}

}
