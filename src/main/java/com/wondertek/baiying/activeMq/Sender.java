package com.wondertek.baiying.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by wd on 2018/9/14.
 */
public class Sender {

    public static void main(String[] args) {
//        String username = ActiveMQConnectionFactory.DEFAULT_USER;
//        String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;

        String username = "system";
        String password = "manager";

        //1.创建ConnectionFactory对象
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, "tcp://localhost:61616");

        Connection connection = null;
        try {
            //2.获取连接
            connection = connectionFactory.createConnection();
            //activeMQ的连接默认是关闭的，需要手动开启连接
            connection.start();

            //3.通过connection对象创建回话，参数1指是否开启事务，参数2为签收模式，一般默认为自动签收
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            //4.通过session对象创建Destination,指的是一个客户端用来指定生产消息目标和消费消息来源的对象，
            // 在PTP模式中destination被称作queue即队列
            Destination destination = session.createQueue("first");

            //5.通过session对象创建消息对象的生产者和消费者：MessageProducer MessageConsumer
            MessageProducer producer = session.createProducer(null);

            //6.通过MessageProducer的setDeliveryMode方法为其设置持久化性和非持久化性
//            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //7.使用JMS规范中的TextMessage形式创建数据（通过session对象）,并用messageProduce对象的send方法发送数据
            for (int i = 0; i < 100; i++) {
                TextMessage message = session.createTextMessage("我是消息内容" + i);
                //发送数据
                producer.send(destination,message);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
