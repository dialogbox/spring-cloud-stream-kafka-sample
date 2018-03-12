package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SomeService {
  @Autowired
  private CouponUseSource source;

  public void sendEvent() {
    source.output().send(new GenericMessage<>(new SomeEvent("jason", 100)));
    source.output().send(new GenericMessage<>(new SomeEvent("kim", 150)));
    source.output().send(new GenericMessage<>(new SomeEvent("lee", 2000)));
  }

}
