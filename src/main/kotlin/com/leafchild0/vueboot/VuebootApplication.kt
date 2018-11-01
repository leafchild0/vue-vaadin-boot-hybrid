package com.leafchild0.vueboot

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
@EnableAutoConfiguration(exclude = [SecurityAutoConfiguration::class])
class VuebootApplication

fun main(args: Array<String>) {
	runApplication<VuebootApplication>(*args)
}
