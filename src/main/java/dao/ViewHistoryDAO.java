package dao;

import entity.ViewHistory;
import java.util.List;

public interface ViewHistoryDAO {
    void create(String userId, String restaurantId);
    List<ViewHistory> findByUser(String userId);
}
