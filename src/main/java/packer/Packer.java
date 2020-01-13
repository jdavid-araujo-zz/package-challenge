package packer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

import exception.APIException;
import model.Item;
import model.Package;
import util.RegexUtil;

public class Packer {

  private Packer() {
  }

  public static String pack(String filePath) throws APIException {
    return null;
  }
  
  Package getItem(String line) {
		Matcher m = RegexUtil.getNumberByRegex(line);
		
		Package pck = new Package();
		
		pck.setWeight(Double.parseDouble(m.group(0)));
		
		for (int i = 1; i < m.groupCount(); i+=3) {
			 pck.getItems().add(new Item(Integer.parseInt(m.group(i)),
							Double.parseDouble(m.group(i+1)),
							Double.parseDouble(m.group(i+2))));
		}
		
		return pck; 
  }
  
  public void readFile(String filePath) {
	  try {
		File file = new File(filePath);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
			stringBuffer.append("\n");
			Package pck = this.getItem(line);
		}
		fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
  }
}