package br.com.pedule.infra

import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoderProvider {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        val encoder = BCryptPasswordEncoder()
        return encoder
    }
}