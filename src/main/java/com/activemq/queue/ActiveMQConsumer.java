package com.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者
 * 同步方式
 */
public class ActiveMQConsumer {
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建会话(是否通过commit提交，自动消费)
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue("hello1115");
        //创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        //循环接收消息
        while (true){
            //失效时间，10秒内没有消息，退出循环
            TextMessage message = (TextMessage) messageConsumer.receive(10000);
            if (message != null){
                System.out.println(message.getText());
            }else {
                break;
            }
        }

        //关闭连接
        session.commit();
        messageConsumer.close();
        session.close();
        connection.close();
        System.out.println("消费结束");
    }
}
