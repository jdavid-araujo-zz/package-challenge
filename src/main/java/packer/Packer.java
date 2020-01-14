package packer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import exception.APIException;
import model.Item;
import model.Package;
import util.RegexUtil;

public class Packer {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.toString();
	}

	private static String getPackage(String line) {
		Matcher m = RegexUtil.getNumberByRegex(line);
		StringBuffer result = new StringBuffer();

		Package pck = new Package();

		pck.setWeight(Integer.parseInt((m.group(0))));

		for (int i = 1; i < m.groupCount(); i += 3) {
			pck.getItems().add(new Item(Integer.parseInt(m.group(i)), Double.parseDouble(m.group(i + 1)),
					Double.parseDouble(m.group(i + 2))));
		}

		List<String> indexList = packerRecursive.getIndex(pck);

		if (!indexList.isEmpty()) {
			result.append(indexList.stream().map(i -> i.toString()).collect(Collectors.joining(",")));
		} else {
			result.append("-");
		}
		
		return result.toString();
	}

}