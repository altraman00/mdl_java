package com.mdl.activemq.services.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mdl.activemq.services.MQProducer;

//@Repository
@Service
public class MQProducerImpl implements MQProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Override
	public void sendDataToQueue(String queueKey, Object object) {
		try {
			System.out.println("=========发送消息开始=============消息：" + object.toString());
			amqpTemplate.convertAndSend(queueKey, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
