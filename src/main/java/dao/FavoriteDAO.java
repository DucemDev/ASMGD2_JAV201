package dao;

import entity.Restaurant;
import java.util.List;

public interface FavoriteDAO {
    boolean isLiked(String userId, String restaurantId);
    void like(String userId, String restaurantId);
    List<Restaurant> findLikedByUser(String userId);
    void unlike(String userId, String restaurantId);

}
