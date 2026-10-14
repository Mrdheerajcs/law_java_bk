package com.lawproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import org.w3c.dom.Text;

public class EventReq {

    @Size(max = 15)
    @JsonProperty("eventType")
    private String eventType;

    @Size(max = 100)
    @JsonProperty("subject")
    private String subject;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("desc")
    private String desc;

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }


    @Override
    public String toString() {
        return "EventReq{eventType='" + eventType + "', subject='" + subject + "', desc='" + desc + "'}";
    }
}