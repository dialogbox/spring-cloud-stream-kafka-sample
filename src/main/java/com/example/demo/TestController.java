package com.example.demo;

import static org.springframework.http.ResponseEntity.status;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Autowired
  SomeService someService;

  @GetMapping("/sendevent")
  public ResponseEntity<String> sendEvent() {
    if (someService.sendEvent())
      return ok().body("ok");
    else
      return status(HttpStatus.SERVICE_UNAVAILABLE).body("Can not send event");
  }

}
