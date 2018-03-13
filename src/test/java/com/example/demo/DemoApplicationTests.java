package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private CustomInOut inout;

	@Autowired
	private MessageCollector messageCollector;

	@Autowired
	private SomeService someService;

	@Test
	@SuppressWarnings("unchecked")
	public void testWiring() {

		Message<String> message = new GenericMessage<>("hello");
		inout.conponUseInput().send(message);

		someService.sendEvent();
		Collection<Message<?>> messages = new ArrayList<>();
		messageCollector.forChannel(inout.output()).drainTo(messages);

		messages.stream().forEach(System.out::println);
		assertThat(messages.size(), equalTo(3));
	}
}
