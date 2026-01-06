package rti.system.dev.ollamajavaapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rti.system.dev.ollamajavaapi.domain.entity.ChatLogs;
import rti.system.dev.ollamajavaapi.repository.OllamaJavaRepository;
import rti.system.dev.ollamajavaapi.service.OllamaChatService;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class OllamaChatServiceImpl implements OllamaChatService {

    @Value("${ai.base.ollama.url}")
    public  String baseUrl;

    private final RestTemplate restTemplate;
    private final OllamaJavaRepository ollamaJavaRepository;

    private final HttpHeaders headers;

    @Cacheable(value = "ollamaGenerate", key = "#root.methodName + T(java.util.Arrays).hashCode(#request.getBytes())")
    @Async
    public CompletableFuture<String> getGenerateOllamaFormatted(String request) {
        this.saveLogs(request);
        return CompletableFuture.completedFuture(
                restTemplate.exchange(baseUrl + "/generate", HttpMethod.POST,
                        new HttpEntity<>(request, headers), String.class).getBody()
        );
    }

    @Cacheable(value = "ollamaChat", key = "#root.methodName + T(java.util.Arrays).hashCode(#request.getBytes())")
    @Async
    public CompletableFuture<String> getChatOllamaFormatted(String request) {
        this.saveLogs(request);
        return CompletableFuture.completedFuture(
                restTemplate.exchange(baseUrl + "/chat", HttpMethod.POST,
                        new HttpEntity<>(request, headers), String.class).getBody()
        );
    }

    @Cacheable(value = "ollamaEmbed", key = "#root.methodName + T(java.util.Arrays).hashCode(#request.getBytes())")
    public String getEmbedOllamaFormatted(String request) {
        this.saveLogs(request);
        return restTemplate.exchange(baseUrl + "/embed", HttpMethod.POST,
                new HttpEntity<>(request, headers), String.class).getBody();
    }

    @Cacheable(value = "ollamaTags", key = "#root.methodName")
    public String getTagsOllamaFormatted() {
        this.saveLogs("OllamaTags");
        return restTemplate.exchange(baseUrl + "/tags", HttpMethod.GET,
                new HttpEntity<>(headers), String.class).getBody();
    }

    public String getPsOllamaFormatted() {
        this.saveLogs("PsOllama");
        return restTemplate.exchange(baseUrl + "/ps", HttpMethod.GET,
                new HttpEntity<>(headers), String.class).getBody();
    }

    @Cacheable(value = "ollamaShow", key = "#root.methodName + #request")
    public String getShowOllamaFormatted(String request) {
        this.saveLogs(request);
        return restTemplate.exchange(baseUrl + "/show", HttpMethod.POST,
                new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String getCreateResponseOllamaFormatted(String request) {
        this.saveLogs(request);
        return restTemplate.exchange(baseUrl + "/create", HttpMethod.POST, new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String getCopyResponseOllamaFormatted(String request) {
        this.saveLogs(request);
        return restTemplate.exchange(baseUrl + "/copy", HttpMethod.POST, new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String getPullResponseOllamaFormatted(String request) {
        this.saveLogs(request);
        return restTemplate.exchange(baseUrl + "/pull", HttpMethod.POST, new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String getPushResponseOllamaFormatted(String request) {
        this.saveLogs(request);
        return restTemplate.exchange(baseUrl + "/push", HttpMethod.POST, new HttpEntity<>(request, headers), String.class).getBody();
    }

    public String deleteModels(String request) {
        this.saveLogs(request);
        return restTemplate.exchange(baseUrl + "/delete", HttpMethod.DELETE, new HttpEntity<>(request, headers), String.class).getBody();

    }

    public String getVersion() {
        this.saveLogs("Version");
        return restTemplate.exchange(baseUrl + "/version", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();

    }

    private void saveLogs(String request) {
        log.info("Salvando logs de {}", request);
        this.ollamaJavaRepository.save(ChatLogs.builder()
                .command(request)
                .build());
    }
}
