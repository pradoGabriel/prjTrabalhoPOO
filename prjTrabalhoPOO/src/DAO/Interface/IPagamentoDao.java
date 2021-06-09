package DAO.Interface;

import Model.PagamentoModel;

import java.sql.SQLException;

public interface IPagamentoDao {
    public int insertPagamento(PagamentoModel pagamento);
}
