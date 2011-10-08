package com.dotloop.pepsi.support;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DataGenerator {
	private final double LINE_IS_DUPLICATE_PROBABILITY = 0.025;
	private final double LINE_IS_WORTHY_OF_DUPLICATION_PROBABILITY = 0.002;
	
	private final long MAXIMUM_FILE_SIZE = 1024 * 1024 * 1024;  // 1 GB
	
	private final int MAXIMUM_LINE_LENGTH = 120;
	private final int MINIMUM_LINE_LENGTH = 10;
	
	private final String FILE_NAME = "data_file.txt";
	
	private ArrayList<String> duplicatePool = new ArrayList<String>();	
	private static final String CHARACTERS = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ !@#$%^&*()_+=[]{};':,.<>/?";

	
	public void writeDataFile() throws IOException {		
		PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));
		
		long size = 0;
		
		while (size < MAXIMUM_FILE_SIZE) {
			String line = null;
			
			if (isLineDuplicate()) {
				line = getLineFromDuplicatePool();
			}
			
			if (line == null) {
				line = generateLine();
			}
			
			if (isLineWorthyOfDuplication()) {
				putLineInDuplicatePool(line);
			}
			
			size += line.length();
			out.println(line);
		}
		
		out.close();
		
	}
	
	private String generateLine() {
		StringBuilder sb = new StringBuilder();
		int length = randomIntBetween(MINIMUM_LINE_LENGTH, MAXIMUM_LINE_LENGTH);
		for (int i = 0; i < length; i++) {
			sb.append(generateCharacter());
		}
		return sb.toString();	
	}
	
	private boolean isLineDuplicate() {
		return Math.random() < LINE_IS_DUPLICATE_PROBABILITY;
	}
	
	private boolean isLineWorthyOfDuplication() {
		return Math.random() < LINE_IS_WORTHY_OF_DUPLICATION_PROBABILITY;
	}
	
	private void putLineInDuplicatePool(String line) {
		duplicatePool.add(line);
	}
	
	private String getLineFromDuplicatePool() {
		if (duplicatePool.size() > 0) {
			int index = randomIntBetween(0, duplicatePool.size() - 1);
			return duplicatePool.get(index);
		} else {
			return null;
		}
	}

	private char generateCharacter() {
		int index = randomIntBetween(0, CHARACTERS.length() - 1);
		return CHARACTERS.charAt(index);
	}
	
	private int randomIntBetween(int greaterThan, int lessThan) {
		return (int)Math.ceil((Math.random() * (lessThan - greaterThan)) + greaterThan);	
	}
	
	public String getfileName() {
		return FILE_NAME;
	}
	
	public static void main(String[] args) throws IOException {
		DataGenerator gen = new DataGenerator();
		gen.writeDataFile();
	}	
}
