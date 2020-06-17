package dao;

public abstract class DaoFactory {
    public static DaoFactory getFactory(String factory) {
        switch (factory) {
            case "jdbc": {
                return new JdbcDaoFactory();
            }
            default: {
                throw new IllegalArgumentException("Factory " + factory + " not found.");
            }
        }
    }

    public abstract UserDao getUserDao();
}
