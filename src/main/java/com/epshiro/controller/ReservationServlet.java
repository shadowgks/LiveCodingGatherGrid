package com.epshiro.controller;

import com.epshiro.dao.ReservationRepository;
import com.epshiro.entities.Event;
import com.epshiro.entities.Ticket;
import com.epshiro.entities.User;
import com.epshiro.service.EventService;
import com.epshiro.service.ReservationService;
import com.epshiro.service.dto.ResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;

@WebServlet(name = "reservation", value = {"/reservation"})
public class ReservationServlet extends HomeServlet{
    EventService es = new EventService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id_event = Long.valueOf(req.getParameter("id"));
        Event event = es.findEvent(id_event);
        req.setAttribute("event",event);
        User user = (User) req.getSession().getAttribute("user");
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setUser(user);
        ReservationService reservationService = new ReservationService();
        ResponseDTO response = reservationService.insertTicket(ticket);
    }

    protected void reservation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer quantity = Integer.valueOf(req.getParameter("number-ticket"));
        User user = (User) req.getSession().getAttribute("user");
        Long id_event = Long.valueOf(req.getParameter("id"));
        Event event = es.findEvent(id_event);

        //Ticket
        Ticket ticket = new Ticket(quantity, event, user);
        ReservationService rs = new ReservationService();
        rs.insertTicket(ticket);
        resp.sendRedirect("events");
    }

}
