package ru.test.task.carservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "CarService API",
                version = "1.0",
                description = "Service for managing cars and their parts",
                contact = @Contact(url = "https://github.com/DedPixta", name = "Maxim", email = "max.kossatyy@gmail.com")
        )
)
public class SwaggerConfig {
}
