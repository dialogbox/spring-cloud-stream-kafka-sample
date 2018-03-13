package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SomeService {
  @Autowired
  private CustomInOut source;

  public void sendEvent() {
    source.output().send(new GenericMessage<>(new SomeEvent("jason", 100)));
  }

}
