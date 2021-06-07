package DAO.Interface;

import Model.DependenciaModel;

import java.sql.SQLException;
import java.util.List;

public interface IDependenciaDao {
    public List<DependenciaModel> getDependenciasByPlano(int idPlano) throws SQLException;
}
