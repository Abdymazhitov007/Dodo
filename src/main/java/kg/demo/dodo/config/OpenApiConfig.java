package kg.demo.dodo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Dodo Pizza API",
                description = "API for managing Dodo Pizza operations and services", version = "1.0.0",
                contact = @Contact(
                        name = "Dodo Pizza",
                        email = "abdykadyr000000@gmail.com",
                        url = "https://dodopizza.kg/bishkek"
                )
        )
)
public class OpenApiConfig {

}
