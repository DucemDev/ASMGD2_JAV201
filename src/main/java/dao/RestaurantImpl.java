package dao;

import entity.Restaurant;
import jakarta.persistence.EntityManager;
import util.XJPA;

import java.util.List;

public class RestaurantImpl implements RestaurantDAO {
    public Restaurant findbyid(String id) {
        EntityManager em = XJPA.getEntityManager();
        return null;
    }

    public List<Restaurant> findall() {
        EntityManager em = XJPA.getEntityManager();
        return em.createQuery("SELECT r FROM Restaurant r").getResultList();
    }

    public void create(Restaurant restaurant) {
    }

    public void update(Restaurant restaurant) {
    }

    public void delete(String id) {
    }
}
