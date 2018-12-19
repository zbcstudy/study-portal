package com.wondertek.baiying.kafka;

public interface Sender {

	public void sendMessage(String topic, byte[] data, String unConvertMsg);
}
