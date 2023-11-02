package com.project.user.repository;

import com.project.user.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class HibernateUserRepository implements UserRepository{

    @Inject
    EntityManager em;

    public List<User> findAll() {
        return em.createQuery("SELECT u from User u", User.class).getResultList();
    }

    public Optional<User> findById(long id) {
        Query query = em.createQuery("SELECT u from User u where u.id=:id", User.class);
        query.setParameter("id", id);
        return query.getResultStream().findFirst();
    }

    public Optional<User> findByEmail(String email) {
        Query query = em.createQuery("SELECT u from User u where u.email=:email", User.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Transactional
    public void delete(User user) {
        em.remove(user);
    }
}
