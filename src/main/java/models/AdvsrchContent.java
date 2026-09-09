package models;

import java.util.List;

public class AdvsrchContent {
    private List<AdvsrchGroupDto> content;

    // Пустой конструктор для Jackson
    public void ContentResponseDto() {
    }

    public List<AdvsrchGroupDto> getContent() { return content; }
    public void setContent(List<AdvsrchGroupDto> content) { this.content = content; }
}

