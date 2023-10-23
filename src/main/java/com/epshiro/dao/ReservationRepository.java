package com.epshiro.dao;

import com.epshiro.entities.Event;
import com.epshiro.entities.Ticket;
import com.oracle.wls.shaded.org.apache.xpath.operations.Bool;
import jakarta.persistence.*;

import java.util.List;

public class ReservationRepository {
    private final EntityManagerFactory emf;

    public ReservationRepository() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public List<Ticket> getAllTicket(){
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
    }

    public boolean checkNbrTotalPlaceEvent(Long eventID){
        EntityManager em = emf.createEntityManager();


        String select = "SELECT CASE WHEN (e.max - SUM(t.number)) < 0 THEN true ELSE false END " +
                "FROM Ticket t " +
                "LEFT JOIN t.event e " +
                "WHERE e.id = :eventID";

        Query query = em.createQuery(select);
        query.setParameter("eventID", eventID);

        boolean result = (boolean) query.getSingleResult();
        em.close();

        return result;
    }

    public Ticket insert(Ticket ticket){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ticket);
        em.getTransaction().commit();
        em.close();
        return ticket;

    }



}
