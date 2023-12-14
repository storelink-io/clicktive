package com.clicktive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CtvApplication

fun main(args: Array<String>) {
    runApplication<CtvApplication>(*args)
}