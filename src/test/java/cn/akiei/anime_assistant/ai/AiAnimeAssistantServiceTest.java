package cn.akiei.anime_assistant.ai;

import cn.akiei.anime_assistant.ai.entity.AnimeCharacterReport;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import jakarta.annotation.Resource;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiAnimeAssistantServiceTest {

    @Resource
    AiAnimeAssistantService aiAnimeAssistantService;

    @Test
    void chat() {
        System.out.println("---------------- 首次会话 ---------------");
        String result = aiAnimeAssistantService.chat("亚瑟王Saber");
        System.out.println(result);
        System.out.println("-------------- 第二次会话 -----------------");
        String result2 = aiAnimeAssistantService.chat("刚刚我问了啥？");
        System.out.println(result2);
        System.out.println("---------------- 第三次会话 -----------------");
        System.out.println(aiAnimeAssistantService.chat("描述一下这个角色的具体特征"));
    }

    @Test
    void chatReport() {
        ResponseFormat responseFormat = ResponseFormat.builder()
            .type(ResponseFormatType.JSON)
            .jsonSchema(JsonSchema.builder()
                .name("Anime")
                .rootElement(JsonObjectSchema.builder()
                    .addStringProperty("人物姓名")
                    .addStringProperty("出自作品")
                    .addStringProperty("性别")
                    .addStringProperty("年龄生日")
                    .addStringProperty("身份职业")
                    .addStringProperty("发型发色")
                    .addStringProperty("瞳色眼部特征")
                    .addStringProperty("身高体型")
                    .addStringProperty("标志性服饰装饰")
                    .addStringProperty("核心性格")
                    .addStringProperty("行为特点")
                    .addStringProperty("口头禅标志性动作")
                    .addStringProperty("能力类型")
                    .addStringProperty("武器装备")
                    .addStringProperty("特殊技能")
                    .addStringProperty("战力定位")
                    .addStringProperty("所属阵营")
                    .addStringProperty("重要关系")
                    .addStringProperty("核心剧情定位")
                    .addStringProperty("总结")
                    .build())
                .build())
            .build();
        UserMessage userMessage = UserMessage.from("给我Saber的人物特征报告");
        ChatRequest chatRequest = ChatRequest.builder()
            .responseFormat(responseFormat)
            .messages(List.of(userMessage))
            .build();

        AnimeCharacterReport result = aiAnimeAssistantService.chatReport(chatRequest);
        System.out.println(result);
    }
}