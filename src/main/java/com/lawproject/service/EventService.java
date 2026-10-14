package com.lawproject.service;

import com.lawproject.dto.EventReq;
import com.lawproject.dto.EventUpdateRequest;
import com.lawproject.entity.Event;
import com.lawproject.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventReq eventReq) {
        log.info("Creating event with eventType: {}, subject: {}, desc: {}",
                eventReq.getEventType(), eventReq.getSubject(), eventReq.getDesc());

        try {
            Event event = new Event();
            event.setEventType(eventReq.getEventType());
            event.setEventSubject(eventReq.getSubject());
            event.setEventDesc(eventReq.getDesc());
            event.setStatus("y");

            Event savedEvent = eventRepository.save(event);
            log.info("Event created successfully with ID: {}", savedEvent.getEventId());
            return savedEvent;
        } catch (Exception e) {
            log.error("Error creating event: ", e);
            throw new RuntimeException("Failed to create event: " + e.getMessage());
        }
    }

    public Event updateEvent(Long id, EventUpdateRequest request) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (request.getEventType() != null)
            event.setEventType(request.getEventType());

        if (request.getSubject() != null)
            event.setEventSubject(request.getSubject());

        if (request.getDesc() != null)
            event.setEventDesc(request.getDesc());

        return eventRepository.save(event);
    }
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents(Integer flag) {
        if (flag != null && flag == 1) {
            return eventRepository.findByStatus("y");
        }
        return eventRepository.findAll();
    }

    public List<Event> filterEvents(String eventType, String subject) {

        List<Event> events = eventRepository.findByStatus("y");

        if (eventType != null &&
                !eventType.isEmpty() &&
                !"all".equalsIgnoreCase(eventType)) {

            events = events.stream()
                    .filter(e -> e.getEventType() != null &&
                            e.getEventType().equalsIgnoreCase(eventType))
                    .collect(Collectors.toList());
        }

        if (subject != null && !subject.isEmpty()) {
            events = events.stream()
                    .filter(e -> e.getEventSubject() != null &&
                            e.getEventSubject().toLowerCase()
                                    .contains(subject.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return events;
    }
    public boolean changeStatus(Long id, String status) {
        try {
            int updated = eventRepository.updateStatus(id, status);
            return updated > 0;
        } catch (Exception e) {
            log.error("Error changing status for event ID: {}", id, e);
            return false;
        }
    }

    public boolean bulkUpdate(List<Long> ids, String status) {
        try {
            int updated = eventRepository.bulkUpdateStatus(ids, status);
            return updated > 0;
        } catch (Exception e) {
            log.error("Error in bulk update for IDs: {}", ids, e);
            return false;
        }
    }
}