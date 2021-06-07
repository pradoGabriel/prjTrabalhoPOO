package DAO;

import DAO.Interface.IBaseDao;
import DAO.Interface.IDependenciaDao;
import Model.DependenciaModel;
import Model.PlanoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DependenciaDao implements IDependenciaDao {
    private Connection connection;

    public DependenciaDao() throws SQLException, ClassNotFoundException {
        IBaseDao baseDao = new BaseDao();
        connection = baseDao.getConnection();
    }

    @Override
    public List<DependenciaModel> getDependenciasByPlano(int idPlano) throws SQLException {
        List<DependenciaModel> listaDependencias = new ArrayList<DependenciaModel>();

        String sql = "SELECT D.* FROM Dependencia D " +
                "INNER JOIN PlanoDependencia PD ON PD.IdDependencia = D.Id" +
                "Where PD.IdPlano = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idPlano);

        ResultSet result = ps.executeQuery();
        while(result.next()){
           DependenciaModel dependencia = new DependenciaModel();
            dependencia.setId(result.getInt(("Id")));
            dependencia.setNome(result.getString("Nome"));
            dependencia.setDescricao(result.getString(("Descricao")));

            listaDependencias.add(dependencia);
        }
        ps.close();

        return listaDependencias;
    }
}
