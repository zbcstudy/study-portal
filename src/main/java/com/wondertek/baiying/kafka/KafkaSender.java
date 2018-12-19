package com.wondertek.baiying.kafka;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender implements Sender {
	
	private Logger logger = LoggerFactory.getLogger(KafkaSender.class);
	
	private CustomProducer producer = null;
	
	@Override
	public void sendMessage(String topic,byte[] data,String unConvertMsg) {
		logger.info("topic :" + topic + ", data : " + data);
		producer.sendMsg(topic,data,unConvertMsg);
	}
	
	@PostConstruct
	private void init() throws Exception{
		CustomProducer.setPropertiesPath("kafka/producer/producer-config.properties");
		producer = CustomProducer.getInstance();
	}
}
