package org.example.kitsurecs.services;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.time.Duration;
import java.util.ArrayList;


@ApplicationScoped
public class OpenAIService {
    //logger for this class
    private static final Logger logger = LoggerFactory.getLogger(OpenAIService.class);

    //The OPENAI service client from the imported library
    private final OpenAiService service;

    @Inject
    public OpenAIService(){
        String apiKey = System.getenv("OPENAI_API_KEY");
        this.service  = new OpenAiService(apiKey, Duration.ofSeconds(30));
    }

    public String generateCompletion(String prompt){
        List<ChatMessages> messages = new ArrayList<>();
        message.add(new ChatMessage("user", prompt));

        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(messages)
                .model("gpt-4")
                .maxTokens(1000)
                .build();

        ChatCompletionResult result = service.createChatCompletion(completionRequest);

        return result.getChoices().get(0).getMessage().getContent();
    }
}
