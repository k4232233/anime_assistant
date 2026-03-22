package cn.akiei.anime_assistant.ai;

import cn.akiei.anime_assistant.ai.entity.AnimeCharacterReport;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.service.SystemMessage;
import java.util.List;

public interface AiAnimeAssistantService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String userMessage);

    @SystemMessage(fromResource = "system-prompt2.txt")
    AnimeCharacterReport chatReport(ChatRequest chatRequest);

    // 人物特征报告
    record Report(String name, List<String> suggestionList){}
}