package dao;

import entity.Shares;

public interface SharesDAO {
//    void create(Shares share);
    void share(String userId, String restaurantId, String email);
}
