
public class Edge<T> {
	private T value;
	private double weight;
	
	public Edge(T value, double weight) {
		this.value = value;
		this.weight = weight;
	}
	
	public Edge(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setValue(T newValue) {
		this.value = newValue;
	}
	
	public void setWeight(double newWeight) {
		this.weight = newWeight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	public String toString() {
		return this.value.toString() + "(" + this.weight + ")";
	}
}
