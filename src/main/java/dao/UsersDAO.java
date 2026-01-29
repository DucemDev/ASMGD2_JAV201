package dao;

import entity.Users;

import java.util.List;

public interface UsersDAO {
    Users findById(String id);
    List<Users> findAll();
    void create(Users user);
    void update(Users user);
    void delete(Users user);
}
