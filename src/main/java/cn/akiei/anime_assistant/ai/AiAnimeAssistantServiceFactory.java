package cn.akiei.anime_assistant.ai;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haoran.xu
 * @date 2026/03/22 20:44
 **/

@Configuration
public class AiAnimeAssistantServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Bean
    public AiAnimeAssistantService aiAnimeAssistantService() {
        // 会话记忆
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        //return AiServices.create(AiAnimeAssistantService.class, qwenChatModel);

        // 构造 Ai Service
        return AiServices.builder(AiAnimeAssistantService.class)
            .chatModel(qwenChatModel)
            .chatMemory(chatMemory)
            .build();
    }
}