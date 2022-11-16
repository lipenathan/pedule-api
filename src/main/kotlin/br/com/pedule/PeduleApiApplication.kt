package br.com.pedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class PeduleApiApplication

fun main(args: Array<String>) {
    runApplication<PeduleApiApplication>(*args)
}