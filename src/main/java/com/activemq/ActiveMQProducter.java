package com.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * 消息生产者
 *
 */
public class ActiveMQProducter
{
    public static void main( String[] args ) throws JMSException {
        // 连接工厂
        // 使用默认用户名、密码、路径
        // 因为：底层实现：final String defaultURL = "tcp://" + DEFAULT_BROKER_HOST + ":" + DEFAULT_BROKER_PORT;
        // 所以：路径 tcp://host:61616
        //1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2、创建连接
        Connection connection = connectionFactory.createConnection();
        //3、打开连接
        connection.start();
        //4、创建会话(是否开启事务，消息是否自动确认)
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        //5、创建队列(队列名称)
        Queue queue = session.createQueue("hello1115");
        //6、创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        //7、创建消息
        Message message = session.createTextMessage("收到消息了嘛？");
        //8、发送消息
        producer.send(message);
        //关闭消息等
        session.commit();
        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送成功");
    }
}
