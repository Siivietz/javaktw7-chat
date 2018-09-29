package pl.sdacademy.javaktw7.chat.model;

import java.util.Date;

public class DatedChatMessage extends ChatMessage {
    private static final long serialVersionUID = 1L;

    private Date receiveDate;

    public DatedChatMessage(ChatMessage chatMessage) {
        super(chatMessage.getAuthor(), chatMessage.getMessage());
        receiveDate = new Date();
    }
    public Date getReceiveDate() {
        return receiveDate;
    }
}
