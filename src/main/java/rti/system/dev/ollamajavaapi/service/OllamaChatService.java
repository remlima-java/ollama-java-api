package rti.system.dev.ollamajavaapi.service;

import java.util.concurrent.CompletableFuture;

public interface OllamaChatService {

    CompletableFuture<String> getGenerateOllamaFormatted(String request);

    CompletableFuture<String> getChatOllamaFormatted(String request);

    String getEmbedOllamaFormatted(String request);

    String getTagsOllamaFormatted();

    String getPsOllamaFormatted();

    String getShowOllamaFormatted(String request);

    String getCreateResponseOllamaFormatted(String request);

    String getCopyResponseOllamaFormatted(String request);

    String getPullResponseOllamaFormatted(String request);

    String getPushResponseOllamaFormatted(String request);

    String deleteModels(String request);

    String getVersion();

}
