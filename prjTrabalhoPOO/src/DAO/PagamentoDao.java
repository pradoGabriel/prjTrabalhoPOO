package DAO;

import DAO.Interface.IBaseDao;
import DAO.Interface.IPagamentoDao;
import Model.PagamentoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagamentoDao implements IPagamentoDao {
    private Connection connection;

    public PagamentoDao() throws SQLException, ClassNotFoundException {
        IBaseDao baseDao = new BaseDao();
        connection = baseDao.getConnection();
    }

    @Override
    public int insertPagamento(PagamentoModel pagamento) throws SQLException {
        String sql = "INSERT INTO Pagamento (CpfTitular, NumCartao, Cvv, ValidadeCartao) VALUES (?, ?, ?, ?) SELECT SCOPE_IDENTITY()";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, pagamento.getCpfTitular());
        ps.setString(2, pagamento.getNumCartao());
        ps.setString(3, pagamento.getCvv());
        ps.setString(4, pagamento.getValidadeCartao());

        int id = 0;
        ResultSet result = ps.executeQuery();

        if (result.next()){
            id = result.getInt(1);
        }

        ps.close();
        return id;
    }
}
