package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void save(User user);

    void update(User user);

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);

    List<User> findAll();

    boolean hasById(Long id);

    boolean hasByLogin(String login);

    boolean deleteById(Long id);

    boolean deleteByLogin(String login);

    void deleteAll();
}
