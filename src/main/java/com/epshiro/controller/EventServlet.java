package com.epshiro.controller;


import com.epshiro.entities.Event;
import com.epshiro.entities.User;
import com.epshiro.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "events", value = {"/events", "/add-event", "/edit-event", "/update-event", "/delete-event"})

public class EventServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action){
            case "/add-event":
                addEvent(req, resp);
                break;
            case "/delete-event":
                doDelete(req, resp);
                break;
            case "/edit-event":
                long id = Long.parseLong(req.getParameter("id"));
                Event event = new EventService().findEvent(id);
                req.setAttribute("event", event);

                req.getRequestDispatcher("/WEB-INF/views/edit-event.jsp").forward(req, resp);
                break;
            case "/update-event":
                updateEvent(req, resp);
                break;
            default:
                List<Event> events = new EventService().getAllEvents();
                req.setAttribute("events", events);

                req.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(req, resp);
                break;
        }
    }

    protected void addEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get data
        String name = req.getParameter("name");
        Integer max = Integer.valueOf(req.getParameter("max"));
        //date
        String dateString = req.getParameter("date");
        LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
        Double price = Double.valueOf(req.getParameter("price"));
        User user = (User) req.getSession().getAttribute("user");
        Event event = new Event(name, max, date, price, user);

        EventService eventService = new EventService();
        eventService.createEvent(event);

        //Get all events
        List<Event> events = new EventService().getAllEvents();
        req.setAttribute("events", events);

        //forward page events.jsp
//        req.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(req, resp);
        resp.sendRedirect("events");
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long eventID = Long.valueOf(req.getParameter("id"));

        EventService eventService = new EventService();
        eventService.findEvent(eventID);
        Event event_deleted = eventService.deleteEvent(eventID);
        String msg_error;
        if (event_deleted != null){
            msg_error = "Deleted Success";
        }else {
            msg_error = "Ops! Something is wrong";
        }
        req.setAttribute("msg", msg_error);

        List<Event> events = new EventService().getAllEvents();
        req.setAttribute("events", events);
        req.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(req, resp);
//        resp.sendRedirect("events");
    }

    protected void updateEvent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get data
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        Integer max = Integer.valueOf(req.getParameter("max"));
        //date
        String dateString = req.getParameter("date");
        LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
        Double price = Double.valueOf(req.getParameter("price"));
        User user = (User) req.getSession().getAttribute("user");

        //Validator data
        EventService eventService = new EventService();
        Event event = eventService.findEvent(id);
        event.setDate(date);
        event.setMax(max);
        event.setName(name);
        event.setPrice(price);
        event.setUser(user);
        Event event_update = eventService.updateEvent(event);

        //Get all events
        List<Event> events = new EventService().getAllEvents();
        req.setAttribute("events", events);


//        req.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(req, resp);
        resp.sendRedirect("events");
    }
}
