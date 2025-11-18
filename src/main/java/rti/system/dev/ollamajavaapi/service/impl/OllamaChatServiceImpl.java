package rti.system.dev.ollamajavaapi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class OllamaChatService {
    private final RestTemplate restTemplate;

    private final HttpHeaders headers;

    @Cacheable(value = "ollamaGenerate", key = "#root.methodName + T(java.util.Arrays).hashCode(#request.getBytes())")
    @Async
    public CompletableFuture<String> getGenerateOllamaFormatted(String request) {
        return CompletableFuture.completedFuture(
                restTemplate.exchange("http://localhost:11434/api/generate", HttpMethod.POST,
                        new HttpEntity<>(request, headers), String.class).getBody()
        );
    }

    @Cacheable(value = "ollamaChat", key = "#root.methodName + T(java.util.Arrays).hashCode(#request.getBytes())")
    @Async
    public CompletableFuture<String> getChatOllamaFormatted(String request) {
        return CompletableFuture.completedFuture(
                restTemplate.exchange("http://localhost:11434/api/chat", HttpMethod.POST,
                        new HttpEntity<>(request, headers), String.class).getBody()
        );
    }

    @Cacheable(value = "ollamaEmbed", key = "#root.methodName + T(java.util.Arrays).hashCode(#request.getBytes())")
    public String getEmbedOllamaFormatted(String request) {
        return restTemplate.exchange("http://localhost:11434/api/embed", HttpMethod.POST,
                new HttpEntity<>(request, headers), String.class).getBody();
    }

    @Cacheable(value = "ollamaTags", key = "#root.methodName")
    public String getTagsOllamaFormatted() {
        return restTemplate.exchange("http://localhost:11434/api/tags", HttpMethod.GET,
                new HttpEntity<>(headers), String.class).getBody();
    }

    public String getPsOllamaFormatted() {
        return restTemplate.exchange("http://localhost:11434/api/ps", HttpMethod.GET,
                new HttpEntity<>(headers), String.class).getBody();
    }

    @Cacheable(value = "ollamaShow", key = "#root.methodName + #request")
    public String getShowOllamaFormatted(String request) {
        return restTemplate.exchange("http://localhost:11434/api/show", HttpMethod.POST,
                new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String getCreateResponseOllamaFormatted(String request) {
        return restTemplate.exchange("http://localhost:11434/api/create", HttpMethod.POST, new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String getCopyResponseOllamaFormatted(String request) {
        return restTemplate.exchange("http://localhost:11434/api/copy", HttpMethod.POST, new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String getPullResponseOllamaFormatted(String request) {
        return restTemplate.exchange("http://localhost:11434/api/pull", HttpMethod.POST, new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String getPushResponseOllamaFormatted(String request) {
        return restTemplate.exchange("http://localhost:11434/api/push", HttpMethod.POST, new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String deleteModels(String request) {
        return restTemplate.exchange("http://localhost:11434/api/delete", HttpMethod.DELETE, new HttpEntity<>(request, headers), String.class).getBody();

    }

    public String getVersion() {
        return restTemplate.exchange("http://localhost:11434/api/version", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();

    }
}
