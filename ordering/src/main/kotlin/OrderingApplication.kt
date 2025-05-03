package ordering

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["ordering", "items"])
class OrderingApplication

fun main(args: Array<String>) {
    runApplication<OrderingApplication>(*args)
}