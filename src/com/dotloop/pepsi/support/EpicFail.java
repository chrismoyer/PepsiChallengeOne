package com.dotloop.pepsi.support;

@SuppressWarnings("serial")
public class EpicFail extends Exception {

	public EpicFail() {
		super();
	}

	public EpicFail(String message, Throwable ex) {
		super(message, ex);
	}

	public EpicFail(String message) {
		super(message);
	}

	public EpicFail(Throwable ex) {
		super(ex);
	}

}
