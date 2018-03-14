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

  public boolean sendEvent() {
    return source.output().send(new GenericMessage<>(
        new SomeEvent(
            RandomUtils.nextInt(0, 1000000),
            RandomStringUtils.randomAlphabetic(10),
            RandomUtils.nextLong(1, 20011)-10000,
            Instant.now())
    ));
  }

}
