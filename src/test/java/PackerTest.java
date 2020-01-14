import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import packer.Packer;

public class PackerTest {

	private String path = "input-sample.txt";
	
	@BeforeAll
	public setup() {
		
	}
	
	@Test
	public void packTestResult() {
		Packer.pack(path);
	}
}
