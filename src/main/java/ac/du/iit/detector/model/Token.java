package ac.du.iit.detector.model;

public class Token {
	private String value; // this is the face value of a token, e.g. 'for'
	private int id; // unique id of a token

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Token(String value) {
		super();
		this.value = value;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Token)) {
			return false;
		}
		Token other = (Token) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
