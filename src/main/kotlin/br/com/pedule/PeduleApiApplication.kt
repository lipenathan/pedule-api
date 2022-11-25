package br.com.pedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@SpringBootApplication
class PeduleApiApplication

fun main(args: Array<String>) {
    runApplication<PeduleApiApplication>(*args)
}