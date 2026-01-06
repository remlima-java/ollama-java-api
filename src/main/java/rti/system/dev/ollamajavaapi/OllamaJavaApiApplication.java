package rti.system.dev.ollamajavaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OllamaJavaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OllamaJavaApiApplication.class, args);
    }

}
