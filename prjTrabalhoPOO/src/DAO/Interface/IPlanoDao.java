package DAO.Interface;

import Model.PlanoModel;

import java.sql.SQLException;
import java.util.List;

public interface IPlanoDao {
    public void insertPlano(PlanoModel plano ) throws SQLException;
    public List<PlanoModel> getAllPlanos() throws SQLException;
}
