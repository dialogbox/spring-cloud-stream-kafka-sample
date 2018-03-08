package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class SomeService {
  @Autowired
  private Source source;

  public void sendEvent() {
    source.output().send(new GenericMessage<>("Hello World"));
    source.output().send(new GenericMessage<>("Hello World"));
    source.output().send(new GenericMessage<>("Hello World"));
  }

}
