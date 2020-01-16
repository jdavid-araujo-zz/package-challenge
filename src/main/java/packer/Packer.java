package packer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import exception.APIException;
import model.Item;
import model.Package;
import util.RegexUtil;

public class Packer {
	
	private static final Integer MAX_WEIGHT_PACKAGE = 100;
	private static final Integer MAX_COST_ITEM = 100;
	private static final Double MAX_WEIGHT_ITEM = 100.00;
	private static final Integer MAX_ITEMS = 15;


	private static PackerRecursive packerRecursive = new PackerRecursive();

	public static String pack(String filePath) throws APIException {

		Path path = Paths.get(filePath);
		StringBuffer result = new StringBuffer();

		try {
			List<String> lines = Files.readAllLines(path);

			for (String line : lines) {
				result.append(getPackage(line)).append("\n");
			}

		} catch (IOException e) {
			throw new APIException("It is not possible process the operation");
		}

		return result.toString();
	}

	private static String getPackage(String line) throws APIException {
		Matcher m = RegexUtil.getNumberByRegex(line);
		StringBuffer result = new StringBuffer();
		int i = 1;
		Package pck = new Package();
		Item item = new Item();

		m.find();
		pck.setWeight(Integer.parseInt((m.group())));
				
		if(pck.getWeight() > MAX_WEIGHT_PACKAGE) {
			throw new APIException("Max weight that a package can take is ≤ 100");
		}

		while (m.find()) {
			if (i % 3 == 1) {				
				item.setIndex(Integer.parseInt(m.group()));
			}

			if (i % 3 == 2) {
				if(Double.parseDouble(m.group()) > MAX_WEIGHT_ITEM) {
					throw new APIException("Max weight of an item is ≤ 100");
				}
				
				item.setWeight(Double.parseDouble(m.group()));
			}

			if (i % 3 == 0) {
				if(Integer.parseInt(m.group()) > MAX_COST_ITEM) {
					throw new APIException("Max cost of an item is ≤ 100");
				}
				item.setCost(Integer.parseInt(m.group()));
				pck.getItems().add(item);
				
				if(pck.getItems().size() > MAX_ITEMS) {
					throw new APIException("There might be up to 15 items you need to choose from");					
				}
				
				item = new Item();
			}

			i++;
		}

		List<String> indexList = packerRecursive.getIndex(pck);

		if (!indexList.isEmpty()) {
			result.append(indexList.stream().map(index -> index.toString()).collect(Collectors.joining(",")));
		} else {
			result.append("-");
		}

		return result.toString();
	}

}