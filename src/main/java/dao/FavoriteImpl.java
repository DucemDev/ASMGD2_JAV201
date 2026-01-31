package dao;

import entity.Favorite;
import entity.Restaurant;
import entity.Users;
import jakarta.persistence.EntityManager;
import util.XJPA;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class FavoriteImpl implements FavoriteDAO {

    @Override
    public boolean isLiked(String userId, String restaurantId) {
        EntityManager em = XJPA.getEntityManager();
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(f) FROM Favorite f " +
                                    "WHERE f.user.UserId = :uid " +          // 👈 VIẾT HOA
                                    "AND f.restaurant.RestaurantId = :rid", // 👈 VIẾT HOA
                            Long.class
                    )
                    .setParameter("uid", userId)
                    .setParameter("rid", restaurantId)
                    .getSingleResult();

            return count > 0;
        } finally {
            em.close();
        }
    }
    @Override
    public void unlike(String userId, String restaurantId) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();

            em.createQuery(
                            "DELETE FROM Favorite f " +
                                    "WHERE f.user.UserId = :uid " +
                                    "AND f.restaurant.RestaurantId = :rid"
                    )
                    .setParameter("uid", userId)
                    .setParameter("rid", restaurantId)
                    .executeUpdate();

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void like(String userId, String restaurantId) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();

            Users user = em.find(Users.class, userId);
            Restaurant restaurant = em.find(Restaurant.class, restaurantId);

            Favorite f = new Favorite();
            f.setFavoriteId(UUID.randomUUID().toString());
            f.setUser(user);
            f.setRestaurant(restaurant);
            f.setLikedAt(LocalDateTime.now());

            em.persist(f);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Restaurant> findLikedByUser(String userId) {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT f.restaurant FROM Favorite f " +
                                    "WHERE f.user.UserId = :uid", // 👈 VIẾT HOA
                            Restaurant.class
                    )
                    .setParameter("uid", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
