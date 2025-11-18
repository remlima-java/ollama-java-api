package rti.system.dev.ollamajavaapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AsyncConfig implements WebMvcConfigurer {

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // Define o tempo limite para requisições assíncronas em milissegundos (30 segundos)
        configurer.setDefaultTimeout(30000);
    }
}
