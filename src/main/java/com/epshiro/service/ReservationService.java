package com.epshiro.service;

import com.epshiro.dao.ReservationRepository;
import com.epshiro.entities.Event;
import com.epshiro.entities.Ticket;
import com.epshiro.service.dto.ResponseDTO;
import com.epshiro.service.dto.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService() {
        this.reservationRepository = new ReservationRepository();
    }

    public ResponseDTO insertTicket(Ticket ticket){
        if(LocalDateTime.now().isAfter(ticket.getEvent().getDate())){
            return new ResponseDTO(ResponseStatus.ERROR,"THIS_EVENT_IS_PAST_DUE_DATE");
        }
        if (!checkTicketAvailability(ticket.getEvent())) {
            return new ResponseDTO(ResponseStatus.ERROR,"NO_TICKET_AVAILABLE");
        }
        return new ResponseDTO(ResponseStatus.CREATED,"TICKER_CREATED_SUCCESSFULLY",reservationRepository.insert(ticket));
    }

    public boolean checkNbrTotalPlaceEvent(Long eventID){
        return reservationRepository.checkNbrTotalPlaceEvent(eventID);
    }

    public Boolean checkTicketAvailability(Event event){
        Integer max = event.getMax();
        List<Ticket> tickets = reservationRepository.getAllTicket();
        tickets = tickets.stream().filter(t -> t.getEvent().getId().equals(event.getId())).collect(Collectors.toList());
        return tickets.size() < max;

    }


}
