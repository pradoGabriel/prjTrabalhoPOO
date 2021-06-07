package DAO;

import DAO.Interface.IBaseDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao implements IBaseDao {
    private Connection connection;

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:jdts:sqlserver://127.0.0.1:1433;DatabaseName:Clube;namedPipes=true", "sa", "replytest");
        return connection;
    }
}