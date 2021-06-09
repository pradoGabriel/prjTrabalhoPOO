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
    private static IBaseDao baseDao = new BaseDao();
    private int id;

    @Override
    public int insertPagamento(PagamentoModel pagamento){
        try {
            connection = baseDao.getConnection();
            String sql = "INSERT INTO Pagamento (NomeTitular,CpfTitular, NumCartao, Cvv, ValidadeCartao) VALUES (?, ?, ?, ?, ?) SELECT SCOPE_IDENTITY()";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pagamento.getNomeTitular());
            ps.setString(2, pagamento.getCpfTitular());
            ps.setString(3, pagamento.getNumCartao());
            ps.setString(4, pagamento.getCvv());
            ps.setString(5, pagamento.getValidadeCartao());

            id = 0;
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                id = result.getInt(1);
            }

            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return id;
    }
}
