package br.dev.rti.ai.local.controller;

import br.dev.rti.ai.local.service.interfaces.OllamaChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
@Slf4j
public class OllamaController {

    private final OllamaChatService ollamaChatService;

    @PostMapping(value = "/generate", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompletableFuture<String>> getGenerateOllamaFormatted(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.getGenerateOllamaFormatted(request));
    }

    @PostMapping(value = "/chat", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompletableFuture<String>> getChatOllamaFormatted(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.getChatOllamaFormatted(request));
    }

    @PostMapping(value = "/embed", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getEmbedOllamaFormatted(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.getEmbedOllamaFormatted(request));
    }

    @GetMapping(value = "/tags", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTagsOllamaFormatted() {
        String tagsOllamaFormatted = this.ollamaChatService.getTagsOllamaFormatted();
        log.info(tagsOllamaFormatted);
        return ResponseEntity.ok(tagsOllamaFormatted);
    }

    @GetMapping(value = "/ps", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPsOllamaFormatted() {
        String psOllamaFormatted = this.ollamaChatService.getPsOllamaFormatted();
        log.info(psOllamaFormatted);
        return ResponseEntity.ok(psOllamaFormatted);
    }

    @PostMapping(value = "/show", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getShowOllamaFormatted(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.getShowOllamaFormatted(request));
    }

    @PostMapping(value = "/create", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCreateResponseOllamaFormatted(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.getCreateResponseOllamaFormatted(request));
    }

    @PostMapping(value = "/copy", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCopyResponseOllamaFormatted(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.getCopyResponseOllamaFormatted(request));
    }

    @PostMapping(value = "/push", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPushResponseOllamaFormatted(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.getPushResponseOllamaFormatted(request));
    }

    @PostMapping(value = "/pull", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPullResponseOllamaFormatted(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.getPullResponseOllamaFormatted(request));
    }

    @GetMapping(value = "/version", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getVersion() {
        log.info(this.ollamaChatService.getVersion());
        return ResponseEntity.ok(this.ollamaChatService.getVersion());
    }

    @DeleteMapping(value = "/delete", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteModels(@RequestBody String request) {
        log.info(request);
        return ResponseEntity.ok(this.ollamaChatService.deleteModels(request));
    }
}
