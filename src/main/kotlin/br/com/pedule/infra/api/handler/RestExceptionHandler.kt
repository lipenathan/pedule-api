package br.com.pedule.infra.api.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun exceptionHandler(exception: Exception): ResponseEntity<*> {
        return ResponseEntity(
            mapOf(
                Pair("error", exception.message),
                Pair("timestamp", LocalDateTime.now()),
                Pair("status", HttpStatus.BAD_REQUEST)
            ), HttpStatus.BAD_REQUEST
        )
    }
}