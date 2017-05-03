package daggerok

import com.netflix.hystrix.HystrixObservable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
class ClientApplication

fun main(args: Array<String>) {
  SpringApplication.run(ClientApplication::class.java, *args)
}

@FeignClient("http://service")
interface ServiceClient {
  @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET)) fun root(): Map<String, String>
}

@RestController class UI @Autowired constructor(val client: ServiceClient) {
  @GetMapping fun index() = mapOf("result" to "service says - ${client.root().getOrDefault("result", "<empty>")}")
}
