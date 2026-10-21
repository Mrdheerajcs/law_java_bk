package com.lawproject.dto;

import jakarta.validation.constraints.Size;

public class EventUpdateRequest {

    @Size(max = 15, message = "Event type must not exceed 15 characters")
    private String eventType;

    @Size(max = 255, message = "Subject must not exceed 255 characters")
    private String subject;

    @Size(min = 10, max = 50000, message = "Description must be between 10 and 50000 characters")
    private String desc;

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
}