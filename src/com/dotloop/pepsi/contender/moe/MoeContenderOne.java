package com.dotloop.pepsi.contender.moe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;


import com.dotloop.pepsi.contender.Contender;
import com.dotloop.pepsi.support.EpicFail;

public class MoeContenderOne implements Contender {

	public Set<String> identifyDuplicates(String fileName) throws EpicFail{
		
		ArrayList<String> lines = new ArrayList<String>();
		
		TreeSet<String> duplicates = new TreeSet<String>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line = null;

			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
			
			in.close();
			
			Collections.sort(lines);
			
			String lastLine = null;
			for (String thisLine: lines) {
				
				if (lastLine != null) {
					if (lastLine.equals(thisLine)) {
						duplicates.add(thisLine);
					}
				}				
				lastLine = thisLine;
			}
		} catch (IOException e) {
			throw new EpicFail(e);
		}
		
		return duplicates;
	}

}
