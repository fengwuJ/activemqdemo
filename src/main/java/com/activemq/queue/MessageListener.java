package com.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 监听器消费消息
 * 异步方式
 */
public class MessageListener {
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue("hello1115");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new javax.jms.MessageListener() {
            //每次接收消息，自动调用onMessage()；
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //监听结束，程序就会结束
        while (true){
            //死循环，让程序一直处于运行状态
        }

    }
}
