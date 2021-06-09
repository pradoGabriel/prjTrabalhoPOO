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
    private static IBaseDao baseDao = new BaseDao();

    @Override
    public List<DependenciaModel> getDependenciasByPlano(int idPlano)  {
        List<DependenciaModel> listaDependencias = new ArrayList<DependenciaModel>();
        try {
            connection = baseDao.getConnection();

            String sql = "SELECT D.* FROM Dependencia D \n" +
                    "INNER JOIN PlanoDependencia PD ON PD.IdDependencia = D.Id\n" +
                    "Where PD.IdPlano = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idPlano);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                DependenciaModel dependencia = new DependenciaModel();
                dependencia.setId(result.getInt(("Id")));
                dependencia.setNome(result.getString("Nome"));
                dependencia.setDescricao(result.getString(("Descricao")));

                listaDependencias.add(dependencia);
            }
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return listaDependencias;
    }

    @Override
    public List<DependenciaModel> getAllDependencias() {
        List<DependenciaModel> listaDependencias = new ArrayList<DependenciaModel>();
        try {
            connection = baseDao.getConnection();

            String sql = "SELECT * FROM Dependencia ";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                DependenciaModel dependencia = new DependenciaModel();
                dependencia.setId(result.getInt(("Id")));
                dependencia.setNome(result.getString("Nome"));
                dependencia.setDescricao(result.getString(("Descricao")));

                listaDependencias.add(dependencia);
            }
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return listaDependencias;
    }

    @Override
    public DependenciaModel getDependenciaByName(String nomePlano)  {
        DependenciaModel dependencia = new DependenciaModel();
        try {
            connection = baseDao.getConnection();

            String sql = "SELECT * FROM Dependencia Where nome = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nomePlano);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                dependencia.setId(result.getInt(("Id")));
                dependencia.setNome(result.getString("Nome"));
                dependencia.setDescricao(result.getString(("Descricao")));
            }
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return dependencia;
    }
}
