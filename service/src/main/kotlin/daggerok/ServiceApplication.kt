package daggerok

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@EnableDiscoveryClient
@SpringBootApplication
class ServiceApplication

fun main(args: Array<String>) {
  SpringApplication.run(ServiceApplication::class.java, *args)
}

@RestController class SericeRestResource {
  @GetMapping fun root() = mapOf<String, String>("result" to "service response")
}
