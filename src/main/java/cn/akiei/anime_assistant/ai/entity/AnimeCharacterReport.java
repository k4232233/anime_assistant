package cn.akiei.anime_assistant.ai.entity;

/**
 * @author haoran.xu
 * @date 2026/03/22 22:34
 **/
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 动漫人物特征报告（企业级标准实体）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimeCharacterReport {

    // 基本信息
    private String name;
    private String anime;
    private String gender;
    private String ageBirthday;
    private String identity;

    // 外貌特征
    private String hair;
    private String eyes;
    private String figure;
    private String costume;

    // 性格
    private String personality;
    private String behavior;
    private String mantra;

    // 能力
    private String abilityType;
    private String equipment;
    private String skills;
    private String combatLevel;

    // 背景
    private String camp;
    private String relationship;
    private String plotRole;

    // 总结
    private String summary;
}