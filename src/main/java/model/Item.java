package model;

public class Item {

	private Integer index;
	
	private Double weight;
	
	private Double cost;

	public Item(Integer index, Double weight, Double cost) {
		super();
		this.index = index;
		this.weight = weight;
		this.cost = cost;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
}
