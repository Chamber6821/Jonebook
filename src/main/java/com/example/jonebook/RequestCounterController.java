package com.example.jonebook;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RequestCounterController {
  private final RequestCounter counter;

  @GetMapping("/counter")
  public Object count() {
    return counter.getCount();
  }
}
