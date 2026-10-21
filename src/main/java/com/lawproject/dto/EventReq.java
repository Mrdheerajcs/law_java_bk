package com.lawproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EventReq {

    @NotBlank(message = "Event type is required")
    @Size(max = 15, message = "Event type must not exceed 15 characters")
    @JsonProperty("eventType")
    private String eventType;

    @NotBlank(message = "Subject is required")
    @Size(max = 255, message = "Subject must not exceed 255 characters")
    @JsonProperty("subject")
    private String subject;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 50000, message = "Description must be between 10 and 50000 characters")
    @JsonProperty("desc")
    private String desc;

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    @Override
    public String toString() {
        return "EventReq{eventType='" + eventType + "', subject='" + subject +
                "', desc='" + (desc == null ? null : desc.substring(0, Math.min(desc.length(), 30)) + "...") + "'}";
    }
}