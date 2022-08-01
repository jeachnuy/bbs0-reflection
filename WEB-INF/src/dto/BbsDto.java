package dto;

import java.sql.Timestamp;

/**
 * BbsDto
 */
public class BbsDto {
    private String name;
    private String text;
    
    public BbsDto() {}

    public BbsDto(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "BbsDto [name=" + name + ", text=" + text + ", time=" + getTime() + "]";
    }
}
