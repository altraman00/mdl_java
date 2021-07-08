package com.mdl.activemq.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mdl.activemq.services.MQProducer;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-rabbitmq.xml" })
public class TestQueue {

	@Autowired
	private MQProducer mqProducer;

	private static final String QUEUE_KEY = "xk_test_queue";

	@Test
	public void send() {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("data", "hello,rabbmitmq!");
		mqProducer.sendDataToQueue(QUEUE_KEY, msg);
	}
}
