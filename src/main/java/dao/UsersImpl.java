package dao;

import entity.Users;
import jakarta.persistence.EntityManager;
import util.XJPA;

import java.util.List;

public class UsersImpl implements UsersDAO {
    public Users findById(String id) {
        return null;
    }

    public List<Users> findAll() {
        EntityManager em = XJPA.getEntityManager();
        return em.createQuery("SELECT u FROM Users u").getResultList();
    }

    public void create(Users user) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(Users user) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(Users user) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
