package pl.sdacademy.javaktw7.chat.model;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String author;
    private String message;

    public ChatMessage(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

}
