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
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433/Clube; namedPipes=true", "sa", "1234");
            return connection;
    }
}