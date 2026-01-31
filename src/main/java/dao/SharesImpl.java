package dao;

import entity.Restaurant;
import entity.Shares;
import entity.Users;
import jakarta.persistence.EntityManager;
import util.XJPA;

import java.time.LocalDateTime;
import java.util.UUID;

public class SharesImpl implements SharesDAO {

    @Override
    public void share(String userId, String restaurantId, String email) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();

            Shares s = new Shares();
            s.setShareId(UUID.randomUUID().toString());
            s.setRecipientEmail(email);

            // ✅ ĐÚNG TÊN + ĐÚNG KIỂU
            s.setSharedAt(LocalDateTime.now());

            s.setUser(em.find(Users.class, userId));
            s.setRestaurant(em.find(Restaurant.class, restaurantId));

            em.persist(s);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
