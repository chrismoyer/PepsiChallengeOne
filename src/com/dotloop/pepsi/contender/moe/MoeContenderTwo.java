package com.dotloop.pepsi.contender.moe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;


import com.dotloop.pepsi.contender.Contender;
import com.dotloop.pepsi.support.EpicFail;

public class MoeContenderTwo implements Contender {

	public Set<String> identifyDuplicates(String fileName) throws EpicFail{
		
		TreeSet<String> duplicates = new TreeSet<String>();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line = null;
			
			TreeSet<String> tree = new TreeSet<String>();

			while ((line = in.readLine()) != null) {
				if (!tree.add(line)) {
					duplicates.add(line);
				}
			}
			
			in.close();
		} catch (IOException e) {
			throw new EpicFail(e);
		}
		
		return duplicates;
	}

}
