package com.example.demo;

import java.time.Instant;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SomeService {
  @Autowired
  private CustomInOut source;

  public void sendEvent() {
    source.output().send(
        new GenericMessage<>(
            new SomeEvent(
                RandomUtils.nextInt(1, 1000000),
                RandomStringUtils.random(10, true, false),
                RandomUtils.nextInt(1, 100000),
                Instant.now())
        ));
  }

}
