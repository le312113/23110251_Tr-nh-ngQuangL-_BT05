package com.example.BT12.Model;

public class ChatMessage {
    public enum MessageType { CHAT, JOIN, LEAVE }

    private MessageType type;
    private String room;       // "public" hoặc id phòng/phiên hỗ trợ
    private String sender;     // tên KH hoặc agent
    private String content;    // nội dung tin nhắn

    // getters/setters
    public MessageType getType() { return type; }
    public void setType(MessageType type) { this.type = type; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
