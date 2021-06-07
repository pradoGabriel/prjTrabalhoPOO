package DAO.Interface;

import java.sql.Connection;
import java.sql.SQLException;

public interface IBaseDao {
    public Connection getConnection() throws ClassNotFoundException, SQLException;
}
