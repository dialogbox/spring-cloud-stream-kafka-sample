package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@Slf4j
@SpringBootApplication
@EnableBinding({CustomSink.class, CouponUseSource.class})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	SomeService someService;

	@StreamListener(CustomSink.INPUT_COUPON_USE)
	public void processCouponUsed(String message) {
		log.info("Coupon has been used: " + message);
	}

	@StreamListener(CustomSink.INPUT_STOCK_USE)
	public void processStockUsed(String message) {
		log.info("Stock has been used: " + message);
	}
}

interface CouponUseSource {
	String OUTPUT_COUPON_USE = "couponUse";

	@Output(CouponUseSource.OUTPUT_COUPON_USE)
	MessageChannel output();
}

interface CustomSink {
	String INPUT_COUPON_USE = "couponUsed";
	String INPUT_STOCK_USE = "stockUsed";

	@Input(CustomSink.INPUT_COUPON_USE)
	SubscribableChannel conponUseInput();

	@Input(CustomSink.INPUT_STOCK_USE)
	SubscribableChannel stockUseInput();
}