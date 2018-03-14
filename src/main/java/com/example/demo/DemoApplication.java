package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@Slf4j
@SpringBootApplication
@EnableBinding({CustomInOut.class})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Autowired
	SomeService someService;

	@StreamListener(CustomInOut.INPUT_COUPON_USE)
	public void processCouponUsed(Message<SomeEvent> message) {
		Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
		if (acknowledgment != null) {
			SomeEvent someEvent = message.getPayload();
			log.info("Coupon has been used with ack: {}", message.getPayload());
			acknowledgment.acknowledge();
		} else {
			log.info("Coupon has been used with noack: {}", message.getPayload());
		}
	}
}

interface CustomInOut {
	String INPUT_COUPON_USE = "couponUsed";
	String OUTPUT_COUPON_USE = "couponUsing";

	@Input(CustomInOut.INPUT_COUPON_USE)
	SubscribableChannel conponUseInput();

	@Output(CustomInOut.OUTPUT_COUPON_USE)
	MessageChannel output();
}