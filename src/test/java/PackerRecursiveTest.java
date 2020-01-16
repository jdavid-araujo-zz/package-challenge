import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Item;
import model.Package;

import packer.PackerRecursive;

public class PackerRecursiveTest {

	private static  PackerRecursive packer;
	
	private Package mockInputValues() {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(1, 53.38, 10));
		itemList.add(new Item(2, 88.62, 40));
		itemList.add(new Item(3, 78.48, 30));
		itemList.add(new Item(4, 72.30, 20));
		itemList.add(new Item(5, 30.18, 50));

		Package pck = new Package(40, itemList);
		
		return pck;
	}
	
	
	private Package mockInputSameCostValues() {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item(1, 53.38, 50));
		itemList.add(new Item(2, 88.62, 40));
		itemList.add(new Item(3, 78.48, 30));
		itemList.add(new Item(4, 72.30, 20));
		itemList.add(new Item(5, 30.18, 50));

		Package pck = new Package(60, itemList);
		
		return pck;
	}
		
	
	private List<String> mockIndexResult() {
		return  Arrays.asList("5");
	}
	
	@BeforeAll
	static void setUp() {
		packer = new PackerRecursive();
	}
	
	@Test
	public void getIndexSucess() {
		List<String> result = this.packer.getIndex(this.mockInputValues());
				
		assertLinesMatch(mockIndexResult(), result);
	}
	
	@Test
	public void returnIndexWithDifferentsWeightWithTheSameCostSucess() {
		List<String> result = this.packer.getIndex(this.mockInputSameCostValues());
				
		assertLinesMatch(mockIndexResult(), result);
	}
}
