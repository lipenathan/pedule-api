package br.com.pedule.infra.api.handler

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.format.DateTimeFormatter

@Configuration
class JacksonConfiguration {
    @Bean
    fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer? {
        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->

            // formatter
            val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val timeFormatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("H:m:ss")

            // deserializers
            builder.deserializers(LocalDateTimeDeserializer(dateTimeFormatter))
            builder.deserializers(LocalTimeDeserializer(timeFormatter), LocalTimeDeserializer(timeFormatter2))

            // serializers
            builder.serializers(LocalDateTimeSerializer(dateTimeFormatter))
            builder.serializers(LocalDateTimeSerializer(timeFormatter))
        }
    }
}