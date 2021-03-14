package com.demo.engine;

public class ChatMessage {

    private long time;
    private String contents;

    public ChatMessage(long time, String contents) {
        this.time = time;
        this.contents = contents;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
