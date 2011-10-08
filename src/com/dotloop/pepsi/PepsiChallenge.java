package com.dotloop.pepsi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


import com.dotloop.pepsi.contender.Contender;
import com.dotloop.pepsi.contender.moe.MoeContenderOne;
import com.dotloop.pepsi.contender.moe.MoeContenderTwo;
import com.dotloop.pepsi.support.DataGenerator;
import com.dotloop.pepsi.support.EpicFail;

/*
 * WELCOME TO THE PEPSI CHALLENGE - VERSION ONE
 * 
 * See README.TXT for full rules and details. The short version is that you
 * write a class implementing com.dotloop.pepsi.Contender. Your class (and all 
 * of it's internal dependencies) should be in a subpackage of 
 * com.dotloop.pepsi.contenders. Then add your class to the list of contenders
 * in PepsiChallenge.loadContenders() and run the challenge.
 * 
 * Note that the data file will be created on the first run and if it's subsequently
 * deleted.
 * 
 */
public class PepsiChallenge {

	private static ArrayList<Contender> contenders = new ArrayList<Contender>();
	
	private static void loadContenders() {
		contenders.add(new MoeContenderTwo());
		contenders.add(new MoeContenderOne());
	}
	
	public static void main(String args[]) throws IOException {
		loadContenders();
		
		log("Pepsi Challenge One - 1.0");
		log("-------------------------");

		// Initalize the datafile if it doesn't exist
		String fileName = initializeDataFile();
		
		// Load the data file once so no one has disk cache advantage
		readFile(fileName);

		// Garbage collect here and after each contender for fairness
		System.gc();
		
		for (Contender contender : contenders) {
			takeTheChallenge(contender, fileName);

			
			System.gc();
		}	
		
	}
	
	private static void takeTheChallenge(Contender contender, String fileName) {
		Class<?> c = contender.getClass();
		
		log("--------------");
		log("Contender: " + c.getName());
		
		long start = System.currentTimeMillis();
		
		Set<String> duplicates;
		try {
			duplicates = contender.identifyDuplicates(fileName);
			long duration = System.currentTimeMillis() - start;
			log(c.getName() + " returned " + duplicates.size() + " duplicates in " + duration + " milliseconds");
		} catch (EpicFail e) {
			log("CONTENDER ENCOUNTERED EPIC FAIL: " + e.toString());
		}
	}

	private static void readFile(String fileName) throws IOException {
		log("Reading the data file to eliminate disk caching disadvantage for first contender...");
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = in.readLine()) != null) {
			// Do nothing... we're just reading the file for fun to seed the disk cache
			line.length();
		}	
		in.close();
		log("File read, should be an even playing field");
	}

	private static String initializeDataFile() throws IOException {
		DataGenerator dataGenerator = new DataGenerator();
		String fileName = dataGenerator.getfileName();
		
		File dataFile = new File(fileName);
		if (dataFile.exists()) {
			log("Data file: " + fileName + " already exists, skipping generation");
		} else {
			long start = System.currentTimeMillis();
			log("Generating data file: " + fileName + "...");
			dataGenerator.writeDataFile();
			long duration = System.currentTimeMillis() - start;
			log("Data file generated: " + fileName + " in " + duration + " milliseconds");			
		}
	
		return fileName;
	}

	private static void log(String s) {
		System.out.println(s);
	}
}
