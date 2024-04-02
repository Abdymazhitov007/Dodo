package kg.demo.dodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients("kg.demo.dodo.microservices")
@EnableScheduling
public class DodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DodoApplication.class, args);
    }

}
