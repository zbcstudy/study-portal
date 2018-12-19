package com.wondertek.baiying.kafka;

public enum Topic {

	BIGDATATOPIC("bigdatatopic")
	;
	String value;
	Topic(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
}
