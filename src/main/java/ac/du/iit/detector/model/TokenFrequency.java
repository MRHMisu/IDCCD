package ac.du.iit.detector.model;

public class TokenFrequency {
	private int frequency;
	private Token token;

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return token.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TokenFrequency)) {
			return false;
		}
		TokenFrequency other = (TokenFrequency) obj;
		if (token == null) {
			if (other.token != null) {
				return false;
			}
		} else if (!token.getValue().equals(other.token.getValue())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TokenFrequency [frequency=" + frequency + ", token=" + token
				+ "]";
	}

}
