package com.wondertek.baiying.kafka;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * 获取kafka配置信息
 *
 */
public class CustomProducer {
	
	private static Log log = LogFactory.getLog(CustomProducer.class);
	private Producer<String, byte[]> producer;
	private static Properties properties = new Properties();
	private static CustomProducer k;
	
	public static void setPropertiesPath(String kafkaconfig) throws IOException {		
		properties.load(CustomProducer.class.getClassLoader().getResourceAsStream(kafkaconfig));
	}
	
	private void createProducer() {
		this.producer = new KafkaProducer(properties);
	}
	public static CustomProducer getInstance() {
		k = new CustomProducer();
		return k;
	}
	
	/**
	 * 发送对象信息
	 * @param msg
	 */
	public void sendMsg(String topic,byte[] msg,String unConvertMsg) {
		try {
			createProducer();
			log.info("Topic=" + topic + ",sendMsg message=" + unConvertMsg);
			ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>(topic,(byte[]) msg);
			Future<RecordMetadata> future = producer.send(record);
			log.info("Success Topic=" + future.get().topic() + " ,sendMsg message=" + unConvertMsg + " ,partition=" + future.get().partition());
		} catch (Exception e) {
			log.error("sendMsg Exception Topic=" + topic + ",sendMsgList message=" + unConvertMsg);
		} finally {
			producer.close();
		}
		
	}
}
