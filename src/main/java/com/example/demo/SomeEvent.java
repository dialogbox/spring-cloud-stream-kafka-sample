package com.example.demo;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SomeEvent {
  private Integer id;
  private String name;
  private Long amount;
  private Instant timestamp;
}
