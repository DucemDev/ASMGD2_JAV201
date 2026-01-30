package dao;

import entity.Restaurant;

import java.util.List;

public interface RestaurantDAO {
    Restaurant findbyid(String id);
    List<Restaurant> findall();
    void create(Restaurant restaurant);
    void update(Restaurant restaurant);
    void delete(String id);
}
