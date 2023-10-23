package com.epshiro.dao;

import com.epshiro.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class UserRepository {
    EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("my-persistence-unit");
    public User save(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
        return user;
    }

    public Optional<User> findUserByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Optional<User> user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findAny();
        entityManager.close();
        return user;
    }
}
