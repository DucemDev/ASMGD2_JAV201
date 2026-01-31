package dao;

import entity.Restaurant;

import java.util.List;

public interface RestaurantDAO {
    Restaurant findbyid(String id);
    List<Restaurant> findall();
    void create(Restaurant restaurant);
    void update(Restaurant restaurant);
    void delete(String id);
    List<Restaurant> findTop6ByView();
    void increaseView(String id);
    List<Restaurant> findPage(int page, int size);
    List<Restaurant> searchByName(String keyword);
}
