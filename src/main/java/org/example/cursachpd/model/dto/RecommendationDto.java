package org.example.cursachpd.model.dto;

/**
 * DTO-объект рекомендации.
 * Представляет отдельную рекомендацию с заголовком, описанием, категорией и источником.
 */
public class RecommendationDto {

    private String title;
    private String description;
    private String category;
    private String source;

    /**
     * Конструктор по умолчанию для десериализации.
     */
    public RecommendationDto() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param title заголовок рекомендации
     * @param description описание рекомендации
     * @param category категория рекомендации
     * @param source источник (REST_API или TELEGRAM_BOT)
     */
    public RecommendationDto(String title, String description, String category, String source) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.source = source;
    }

    /**
     * Возвращает заголовок рекомендации.
     *
     * @return заголовок
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок рекомендации.
     *
     * @param title заголовок
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает описание рекомендации.
     *
     * @return описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание рекомендации.
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает категорию рекомендации.
     *
     * @return категория
     */
    public String getCategory() {
        return category;
    }

    /**
     * Устанавливает категорию рекомендации.
     *
     * @param category категория
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Возвращает источник рекомендации.
     *
     * @return источник
     */
    public String getSource() {
        return source;
    }

    /**
     * Устанавливает источник рекомендации.
     *
     * @param source источник
     */
    public void setSource(String source) {
        this.source = source;
    }
}
