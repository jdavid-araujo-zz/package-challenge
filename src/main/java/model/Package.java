package model;

import java.util.ArrayList;
import java.util.List;

public class Package {
	
	private Integer weight;
	
	private List<Item> items = new ArrayList<>();
	
	public Package() {}

	public Package(Integer weight, List<Item> items) {
		super();
		this.weight = weight;
		this.items = items;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
