package app.example.videoplayer_quan.utils;

/**
 * Eventbus 实体类
 */

public class Eventbusmessage {
    private String position;
    private String text;

    public Eventbusmessage(String position, String text) {
        this.position = position;
        this.text = text;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
