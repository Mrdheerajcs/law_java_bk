package com.lawproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.w3c.dom.Text;

public class EventResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("eventType")
    private String eventType;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("status")
    private String status;

    public EventResponse(Long id, String eventType, String subject, String desc, String status) {
        this.id = id;
        this.eventType = eventType;
        this.subject = subject;
        this.desc = desc;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}