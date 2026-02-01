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
            // Phải parseInt vì Database dùng INT IDENTITY, còn tham số truyền vào là String
            return em.find(Restaurant.class, Integer.parseInt(id));
        } catch (Exception e) {
            System.out.println("LỖI findbyid: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Restaurant> findTop6ByView() {
        EntityManager em = XJPA.getEntityManager();
        try {
            // Sửa: r.viewCount thay vì r.ViewCount
            return em.createQuery("SELECT r FROM Restaurant r ORDER BY r.viewCount DESC", Restaurant.class)
                    .setMaxResults(6).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void increaseView(String id) {
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            Restaurant r = em.find(Restaurant.class, Integer.parseInt(id));
            if (r != null) {
                // Sửa: Dùng viewCount (viết thường chữ v)
                r.setViewCount(r.getViewCount() + 1);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Restaurant> findall() {
        EntityManager em = XJPA.getEntityManager();
        try {
            // Giúp Sidebar luôn hiển thị danh sách
            return em.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
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

    @Override
    public List<Restaurant> findPage(int page, int size) {
        EntityManager em = XJPA.getEntityManager();
        try {
            // Đảm bảo dùng viewCount viết thường
            return em.createQuery("SELECT r FROM Restaurant r ORDER BY r.viewCount DESC", Restaurant.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Restaurant> searchByName(String keyword) {
        EntityManager em = XJPA.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Restaurant r WHERE r.name LIKE :kw", Restaurant.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}