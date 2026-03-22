package cn.akiei.langchain4j_demo.ai;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiAnimeAssistantTest {

    @Autowired
    AiAnimeAssistant aiAnimeAssistant;

    @Test
    void chatString() {
        var aiReply = aiAnimeAssistant.chatString("你好, Saber出自哪部动漫");
        aiReply.ifPresent(System.out::println);
    }

    @Test
    void chatRequest() {
        var userMessage = UserMessage.from(TextContent.from("这张图中动漫人物的出处"),
            ImageContent.from("https://imgs.aixifan.com/newUpload/2580739_5eab1ba414774a9e834566811af0c458.jpeg"));
        var aiReply = aiAnimeAssistant.chatRequest(userMessage);
        aiReply.ifPresent(System.out::println);
    }
}
