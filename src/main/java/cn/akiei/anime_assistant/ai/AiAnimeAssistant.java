package cn.akiei.langchain4j_demo.ai;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author huiyi
 * @date 2026/03/09 23:34
 **/

@Service
@Slf4j
public class AiAnimeAssistant {

    //ChatModel 构造注入（生产）
    private final ChatLanguageModel qwenChatModel;

    public AiAnimeAssistant(ChatLanguageModel qwenChatModel) {
        this.qwenChatModel = qwenChatModel;
    }

    //系统提示词
    private static final String SYSTEM_MESSAGE = """
        ## 核心身份
        你是**无幻觉、高严谨、可查证**的专业动漫知识库智能体，名字叫萌娘助手，使命是输出100%匹配官方原作的信息，错误率无限趋近于0。
        阅片覆盖：日本TV动画/剧场版/OVA/OAD、国产动画、欧美经典动画、官方衍生作品。
        拒绝同人二设、拒绝脑补、拒绝张冠李戴。
        
        ## 支持输入类型
        - 人物全名/昵称/绰号/外号
        - 动漫全称/简称/别名/罗马音
        - 图片描述、截图画面、视频片段描述
        - 场景、台词、招式、名梗
        - 画风、服饰、发色、瞳色等视觉特征
        
        ## 铁律约束（必须严格执行）
        1. **权威优先**：所有信息必须来自官方设定集、正版放送、维基类权威百科，禁止编造。
        2. **拒绝猜测**：信息不足无法唯一匹配时，只输出「无法精准匹配，请补充更多特征」，绝不乱猜。
        3. **多候选机制**：同一特征对应多部作品时，**全部列出候选**，并标注区分点。
        4. **禁止扩写**：不添加原作不存在的剧情、人物关系、能力设定。
        5. **出处可追溯**：关键信息必须标注来源（集数、章节、剧场版、官方访谈等）。
        6. **禁敏感内容**：只输出合规正版作品，拒绝违规/违禁动漫相关内容。
        
        ## 固定输出结构（不可变更）
        【精准匹配结果】
        - 匹配状态：唯一匹配 / 多候选匹配 / 无法匹配
        - 官方名称：作品全名 / 角色全名
        - 别名/别称：（若无则写无）
        
        【权威基础信息】
        - 地区/年份：
        - 制作公司/动画制作：
        - 原作类型：原创 / 漫画改编 / 游戏改编 / 小说改编
        - 监督/系列构成：（关键职位，无则省略）
        
        【出处与定位】
        - 登场集数/章节/时间点：
        - 所属篇章/剧情阶段：
        - 场景/台词对应剧情：
        
        【防混淆说明】
        - 易混淆角色/作品：（与相似作品/人物的区分点）
        - 常见误区纠正：（网络流传错误信息辟谣）
        """;


    /**
     * 单模态文本对话
     *
     * @param message 用户文本消息（非空）
     * @return Optional<String>
     */
    public Optional<String> chatString(String message) {
        if (message.isEmpty()) {
            log.warn("用户输入为空");
            return Optional.empty();
        }
        try {
            var systemMessage = SystemMessage.from(SYSTEM_MESSAGE);
            var userMessage = UserMessage.from(message.trim());
            var response = qwenChatModel.generate(systemMessage, userMessage);
            var aiMessage = response.content();
            var aiReply = aiMessage.text();

            return Optional.ofNullable(aiReply);
        } catch (Exception e) {
            log.error("文本对话调用失败", e);
            return Optional.of("error");
        }
    }

    /**
     * 多模态对话
     *
     * @param userMessage 多模态用户消息
     * @return Optional<String>
     */
    public Optional<String> chatRequest(UserMessage userMessage) {
        if (userMessage == null || userMessage.contents() == null || userMessage.contents().isEmpty()) {
            log.warn("用户消息为空或无内容");
            return Optional.empty();
        }
        try {
            var response = qwenChatModel.generate(userMessage);
            var aiMessage = response.content();
            var aiReply = aiMessage == null ? null : aiMessage.text();

            return Optional.ofNullable(aiReply);
        } catch (Exception e) {
            log.error("文本对话调用失败", e);
            return Optional.of("error");
        }
    }
}
