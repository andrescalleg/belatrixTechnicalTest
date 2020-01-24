package com.belatrix.technicaltest.model;

public enum MessageType {
	MESSAGE("1"),
	ERROR("2"),
	WARNING("3");

	private String value;

	MessageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
