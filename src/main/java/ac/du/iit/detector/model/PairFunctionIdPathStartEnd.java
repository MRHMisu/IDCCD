package ac.du.iit.detector.model;

public class PairFunctionIdPathStartEnd {

	private FunctionIdPathStarEnd firstElement;
	private FunctionIdPathStarEnd secondElement;

	public PairFunctionIdPathStartEnd(FunctionIdPathStarEnd firstElement,
			FunctionIdPathStarEnd secondElement) {
		this.firstElement = firstElement;
		this.secondElement = secondElement;
	}

	public FunctionIdPathStarEnd getFirstElement() {
		return firstElement;
	}

	public FunctionIdPathStarEnd getSecondElement() {
		return secondElement;
	}

	public void setFirstElement(FunctionIdPathStarEnd firstElement) {
		this.firstElement = firstElement;
	}

	public void setSecondElement(FunctionIdPathStarEnd secondElement) {
		this.secondElement = secondElement;
	}

	@Override
	public int hashCode() {
		int hash = 47;
		hash = 47 * hash + this.firstElement.hashCode();
		hash = 47 * hash + this.secondElement.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		PairFunctionIdPathStartEnd test = (PairFunctionIdPathStartEnd) obj;

		if (this.firstElement.equals(test.firstElement)
				&& this.secondElement.equals(test.secondElement))
			return true;
		if (this.firstElement.equals(test.secondElement)
				&& this.secondElement.equals(test.firstElement))
			return true;

		return false;
	}

}