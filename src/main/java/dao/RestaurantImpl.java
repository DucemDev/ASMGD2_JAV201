package dao;

import entity.Restaurant;
import jakarta.persistence.EntityManager;
import util.XJPA;

import java.util.List;

public class RestaurantImpl implements RestaurantDAO {

    @Override
    public Restaurant findbyid(String id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.find(Restaurant.class, id);
        } finally {
            em.close();
        }
    }
    @Override
    public List<Restaurant> findTop6ByView() {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT r FROM Restaurant r ORDER BY r.ViewCount DESC",
                    Restaurant.class
            ).setMaxResults(6).getResultList();
        } finally {
            em.close();
        }
    }
    @Override
    public void increaseView(String id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            Restaurant r = em.find(Restaurant.class, id);
            if (r != null) {
                r.setViewCount(r.getViewCount() + 1);
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
    public List<Restaurant> findPage(int page, int size) {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT r FROM Restaurant r ORDER BY r.ViewCount DESC",
                            Restaurant.class
                    )
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Restaurant> findall() {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT r FROM Restaurant r",
                    Restaurant.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void create(Restaurant restaurant) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(restaurant);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    @Override
    public List<Restaurant> searchByName(String keyword) {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE :kw",
                            Restaurant.class
                    )
                    .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Restaurant restaurant) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(restaurant);
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
            Restaurant r = em.find(Restaurant.class, id);
            if (r != null) {
                em.remove(r);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
