package dao;

import entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import util.XJPA;
import java.util.List;

public class UsersImpl implements UsersDAO {

    @Override
    public Users findById(String id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Users> findAll() {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void create(Users user) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Users user) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(String id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            Users user = em.find(Users.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }



    @Override
    public Users findByEmail(String email) {
        EntityManager em = XJPA.getEntityManager();
        try {
            String jpql = "SELECT u FROM Users u WHERE u.email = :email";
            return em.createQuery(jpql, Users.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}