package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

@Slf4j
@SpringBootApplication
@EnableBinding({CustomSink.class, Sink.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@StreamListener(CustomSink.INPUT)
	public void processCustomSink(String message) {
		log.info("Custom: " + message);
	}

	@StreamListener(Sink.INPUT)
	public void processSink(String message) {
		log.info("Normal: " + message);
	}
}

interface CustomSink {
	String INPUT = "myInputTopic";

	@Input(CustomSink.INPUT)
	SubscribableChannel input();
}