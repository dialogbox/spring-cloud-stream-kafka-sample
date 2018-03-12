package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private CustomSink sink;

	@Autowired
	private CouponUseSource source;

	@Autowired
	private MessageCollector messageCollector;

	@Autowired
	private SomeService someService;

	@Test
	@SuppressWarnings("unchecked")
	public void testWiring() {

		Message<String> message = new GenericMessage<>("hello");
		sink.stockUseInput().send(message);

		someService.sendEvent();
		Collection<Message<?>> messages = new ArrayList<>();
		messageCollector.forChannel(source.output()).drainTo(messages);

		messages.stream().forEach(System.out::println);
		assertThat(messages.size(), equalTo(3));
	}
}
