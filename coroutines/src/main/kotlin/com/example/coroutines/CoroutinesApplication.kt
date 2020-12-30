package com.example.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@SpringBootApplication
class CoroutinesApplication {

  @Bean
  fun webClient(): WebClient = WebClient.create()
}

@RestController
class UserController(private val webClient: WebClient) {

  @GetMapping("/user/1")
  suspend fun getUser1(): ResponseEntity<String> {
    return WebClient.create().get().uri("http://192.168.1.60:8500/user/1")
        .retrieve()
        .awaitBody<String>()
        .let { ResponseEntity.ok(it) }
  }

  @GetMapping("/user/2")
  suspend fun getUser2(): ResponseEntity<String> {
    delay(200);
    return ResponseEntity.ok("{}")
  }

}

fun main(args: Array<String>) {
  runApplication<CoroutinesApplication>(*args)
}
