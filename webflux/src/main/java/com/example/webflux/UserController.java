package com.example.webflux;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private final WebClient webClient;

  @GetMapping("/user/1")
  public Mono<String> getUser1() {
    return webClient.get().uri("http://192.168.1.60:8500/user/1")
        .retrieve()
        .bodyToMono(String.class);
  }

  @GetMapping("/user/2")
  public Mono<ResponseEntity<String>> getUser2() {
    return Mono.delay(Duration.ofMillis(200))
        .map(i -> "{}")
        .map(ResponseEntity::ok);
  }
}
