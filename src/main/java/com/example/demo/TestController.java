package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Autowired
  SomeService someService;

  @GetMapping("/sendevent")
  public String sendEvent() {
    someService.sendEvent();
    return "ok";
  }

}
