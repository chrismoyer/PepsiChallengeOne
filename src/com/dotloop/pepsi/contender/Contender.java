package com.dotloop.pepsi.contender;

import java.util.Set;

import com.dotloop.pepsi.support.EpicFail;

public interface Contender {
	public Set<String> identifyDuplicates(String fileName) throws EpicFail;
}
