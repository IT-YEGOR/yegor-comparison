package com.example.tomcat;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private final RestTemplate restTemplate;

  @GetMapping("/user/1")
  public ResponseEntity<?> getUser1() {
    return Optional
        .ofNullable(restTemplate.getForObject("http://192.168.1.60:8500/user/1", String.class))
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/user/2")
  public ResponseEntity<String> getUser2() throws InterruptedException {
    Thread.sleep(200);
    return ResponseEntity.ok("{}");
  }
}
