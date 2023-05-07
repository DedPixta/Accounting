package ru.test.task.driverservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "DriverService API",
                version = "1.0",
                description = "Service for managing drivers and their accounts",
                contact = @Contact(url = "https://github.com/DedPixta", name = "Maxim", email = "max.kossatyy@gmail.com")
        )
)
public class SwaggerConfig {
}
