package com.activemq.queuemq;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;

/**
 * 消息生产者
 */
@Component
@EnableScheduling   //定时任务注解
public class QueueProducer {
    /*
	 * @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
	 * private JmsMessagingTemplate jmsTemplate; //
	 * 发送消息，destination是发送到的队列，message是待发送的消息
	 *
	 * @Scheduled(fixedDelay=3000)//每3s执行1次
	   public void sendMessage(Destination destination, final String message){
	      jmsTemplate.convertAndSend(destination, message);
	   }
	 */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 3000)   //每三秒执行一次
    public void send(){
        try {
            MapMessage mapMessage = new ActiveMQMapMessage();
            mapMessage.setString("info","测试成功了？？");
            this.jmsMessagingTemplate.convertAndSend(this.queue,mapMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
