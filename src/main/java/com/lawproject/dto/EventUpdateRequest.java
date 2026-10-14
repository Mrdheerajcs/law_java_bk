package com.lawproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.w3c.dom.Text;

@Data
public class EventUpdateRequest {

    @Size(max = 15)
    @JsonProperty("eventType")
    private String eventType;

    @Size(max = 100)
    @JsonProperty("subject")
    private String subject;

    @JsonProperty("desc")
    private String desc;



}