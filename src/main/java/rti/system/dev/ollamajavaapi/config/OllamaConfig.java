package br.dev.rti.ai.local.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class OllamaConfig {

    @Bean
    public RestClient.Builder customRestClientBuilder() {
        log.info("Configurando RestClient.Builder custom para Ollama API");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(List.of(
                MediaType.APPLICATION_JSON,
                MediaType.TEXT_PLAIN,
                new MediaType("text", "plain", java.nio.charset.StandardCharsets.UTF_8)
        ));
        jsonConverter.setObjectMapper(objectMapper);

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(jsonConverter);

        return RestClient.builder()
                .requestFactory(new JdkClientHttpRequestFactory())
                .messageConverters(messageConverters -> {
                    messageConverters.clear();
                    messageConverters.addAll(converters);
                });
    }

    @Bean
    public OllamaApi ollamaApi(RestClient.Builder customRestClientBuilder) {
        String baseUrl = "http://localhost:11434";
        log.info("Inicializando OllamaApi com baseUrl: {}", baseUrl);

        return OllamaApi.builder()
                .baseUrl(baseUrl)
                .restClientBuilder(customRestClientBuilder)
                .build();
    }

    @Bean
    public OllamaChatModel ollamaChatModel(OllamaApi ollamaApi) {
        OllamaOptions defaultOptions = OllamaOptions.builder()
                .temperature(0.8)
                .topP(0.9)
                .build();

        log.info("Criando OllamaChatModel com modelo default: {}", defaultOptions.getModel());

        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(defaultOptions)
                .build();
    }
}
