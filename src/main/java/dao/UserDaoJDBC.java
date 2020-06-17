package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SuppressWarnings("SqlIdentifier")
public class UserDaoJDBC implements UserDao {

    private Connection connection;

    public UserDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO user (login, name, password) VALUES (?, ?, ?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(User user) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE user SET name = ?, password = ? WHERE id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> optionalUser = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?")) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("name"),
                        resultSet.getString("password")
                );
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Optional<User> optionalUser = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE login = ?")) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("name"),
                        resultSet.getString("password")
                );
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("name"),
                        resultSet.getString("password")
                );
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean hasById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM user WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean hasByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id FROM user WHERE login = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE login = ?")) {
            statement.setString(1, login);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteAll() {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM user")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
