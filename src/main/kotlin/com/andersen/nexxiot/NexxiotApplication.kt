package com.andersen.nexxiot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
class NexxiotApplication

fun main(args: Array<String>) {
	runApplication<NexxiotApplication>(*args)
}
