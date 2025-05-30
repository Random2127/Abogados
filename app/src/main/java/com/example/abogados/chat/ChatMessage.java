package com.example.abogados.chat;

public class ChatMessage {
    private String sender;
    private String message;
    private long timeStamp;


    public ChatMessage() {}

    public ChatMessage(String sender, String message, long timeStamp) {
        this.sender = sender;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
