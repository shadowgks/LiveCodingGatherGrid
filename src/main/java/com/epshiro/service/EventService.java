package com.epshiro.service;

import com.epshiro.dao.EventRepository;
import com.epshiro.entities.Event;

import java.util.List;

public class EventService {

    private final EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new EventRepository();
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    public Event findEvent(Long eventID){
        return eventRepository.findEvent(eventID);
    }

    public Event createEvent(Event event){
        if(!isValid()){
            return null;
        }
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event){
        if(!isValid()){
            return null;
        }
        return eventRepository.update(event);
    }

    public Event deleteEvent(Long eventID) {
        return eventRepository.deleteEvent(eventID);
    }


    public boolean isValid(){
//        TODO : Validation here
        return true;
    }
}
