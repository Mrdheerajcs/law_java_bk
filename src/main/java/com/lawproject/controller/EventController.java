package com.lawproject.controller;

import com.lawproject.dto.*;
import com.lawproject.entity.Event;
import com.lawproject.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Event Controller", description = "Event Management APIs")
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @PostMapping("/create-event")
    @Operation(summary = "Create event", description = "Create a new event")
    public ResponseEntity<ApiResponse<EventResponse>> createEvent(@RequestBody EventReq eventReq) {
        try {
            log.info("Create event request received: {}", eventReq);
            Event event = eventService.createEvent(eventReq);
            EventResponse response = convertToResponse(event);
            return ResponseEntity.ok(new ApiResponse<>(response, 200, "success"));
        } catch (Exception e) {
            log.error("Error in create-event: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, 500, "Error: " + e.getMessage()));
        }
    }

    @GetMapping("/event/{id}")
    @Operation(summary = "Get event by ID", description = "Retrieve event details by ID")
    public ResponseEntity<ApiResponse<EventResponse>> getEventById(@PathVariable Long id) {
        var eventOpt = eventService.getEventById(id);
        if (eventOpt.isPresent()) {
            EventResponse response = convertToResponse(eventOpt.get());
            return ResponseEntity.ok(new ApiResponse<>(response, 200, "success"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null, 404, "Event not found"));
    }

    @GetMapping("/all-event")
    @Operation(summary = "Get all events", description = "Retrieve all events with optional flag filter")
    public ResponseEntity<ApiResponse<List<EventResponse>>> getAllEvents(@RequestParam(required = false) Integer flag) {
        List<Event> events = eventService.getAllEvents(flag);
        List<EventResponse> responses = events.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(responses, 200, "success"));
    }

    @GetMapping("/events/filter")
    @Operation(summary = "Filter events", description = "Filter events by type and subject")
    public ResponseEntity<ApiResponse<List<EventResponse>>> filterEvents(
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) String subject) {
        List<Event> events = eventService.filterEvents(eventType, subject);
        List<EventResponse> responses = events.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(responses, 200, "success"));
    }

    @PutMapping("/change-status/{id}")
    @Operation(summary = "Change event status", description = "Change status of an event")
    public ResponseEntity<ApiResponse<String>> changeStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        boolean updated = eventService.changeStatus(id, status);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse<>("Status updated", 200, "success"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null, 404, "Event not found"));
    }

    @PutMapping("/bulk-update")
    @Operation(summary = "Bulk update events", description = "Update status for multiple events")
    public ResponseEntity<ApiResponse<String>> bulkUpdate(
            @RequestParam List<Long> ids,
            @RequestParam String status) {
        boolean updated = eventService.bulkUpdate(ids, status);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse<>("Bulk update successful", 200, "success"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(null, 400, "Bulk update failed"));
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<ApiResponse<EventResponse>> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventUpdateRequest request
    ) {
        Event updatedEvent = eventService.updateEvent(id, request);
        return ResponseEntity.ok(
                new ApiResponse<>(convertToResponse(updatedEvent), 200, "Updated")
        );
    }

    private EventResponse convertToResponse(Event event) {
        return new EventResponse(
                event.getEventId(),
                event.getEventType(),
                event.getEventSubject(),
                event.getEventDesc(),
                event.getStatus()
        );
    }
}