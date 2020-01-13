package model;

import java.util.List;

public class Package {
	
	private Double weight;
	
	private List<Item> items;
	
	public Package() {}

	public Package(Double weight, List<Item> items) {
		super();
		this.weight = weight;
		this.items = items;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
