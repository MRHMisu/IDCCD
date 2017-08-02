package ac.du.iit.detector.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Bag { // TODO: why is this
					// not a
					// linkedhashmap?

	private String methodID;
	private String sourceFileID;
	private int size;
	private int comparisions;
	private LinkedHashSet<TokenFrequency> tokenFrequencySet;
	private String tokenString;

	public int getComparisions() {
		return comparisions;
	}

	public void setComparisions(int comparisions) {
		this.comparisions = comparisions;
	}

	public Bag(String methodID) {
		super();
		this.methodID = methodID;
		this.size = 0;
		this.comparisions = 0;
		this.sourceFileID = "";
		this.tokenFrequencySet = new LinkedHashSet<TokenFrequency>();
		this.tokenString = null;
	}

	public Bag() {
		super();
	}

	public String getParentId() {
		return sourceFileID;
	}

	public void setParentId(String parentId) {
		this.sourceFileID = parentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) ((methodID.length() >>> 32));
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
		Bag other = (Bag) obj;
		if (methodID != other.methodID)
			return false;
		return true;
	}

	public String getMethodID() {
		return this.methodID;
	}

	public void setMethodID(String methodID) {
		this.methodID = methodID;
	}

	@Override
	public String toString() {
		String returnString = "";
		for (TokenFrequency tokenFrequency : tokenFrequencySet) {
			returnString += tokenFrequency.getToken().toString() + "@@::@@" + tokenFrequency.getFrequency() + ",";
		}
		return this.methodID + "@#@" + returnString.substring(0, returnString.length() - 1)
				+ System.getProperty("line.separator");
	}

	public TokenFrequency get(TokenFrequency tokenFrequency) {
		this.comparisions = 0;
		for (TokenFrequency tf : tokenFrequencySet) {
			this.comparisions += 1;
			if (tf.equals(tokenFrequency)) {
				return tf;
			}
		}
		return null;
	}

	public int getSize() {
		if (this.size == 0) {
			for (TokenFrequency tf : tokenFrequencySet) {
				this.size += tf.getFrequency();
			}
		}
		return this.size;
	}

	public void addTokenFrequency(TokenFrequency tokenFrequency) {
		tokenFrequencySet.add(tokenFrequency);
	}

	public LinkedHashSet<TokenFrequency> getTokenFrequency() {
		return tokenFrequencySet;
	}

	public Set<String> getUniqueTokenSet() {
		Set<String> tokenSet = new TreeSet<String>();
		for (TokenFrequency tf : tokenFrequencySet) {
			tokenSet.add(tf.getToken().getValue());

		}
		return tokenSet;
	}

	public void setToken(String tokenString) {
		this.tokenString = tokenString;
	}

	public String getToken() {
		return this.tokenString;
	}
}
