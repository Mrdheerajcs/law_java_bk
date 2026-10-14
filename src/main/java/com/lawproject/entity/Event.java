package com.lawproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_tbl")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    @JsonProperty("id")
    private Long eventId;

    @Column(name = "event_type", nullable = false, length = 15)
    @JsonProperty("eventType")
    private String eventType;

    @Column(name = "event_subject", nullable = false, length = 100)
    @JsonProperty("subject")
    private String eventSubject;


    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    @Column(name = "event_desc", columnDefinition = "TEXT", nullable = false)
    private String eventDesc;

    @Column(name = "status", nullable = false, length = 1)
    @JsonProperty("status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = "n";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Event() {}

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getEventSubject() { return eventSubject; }
    public void setEventSubject(String eventSubject) { this.eventSubject = eventSubject; }


    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}