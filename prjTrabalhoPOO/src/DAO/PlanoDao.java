package DAO;

import DAO.Interface.IBaseDao;
import DAO.Interface.IPlanoDao;
import Model.DependenciaModel;
import Model.PlanoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDao implements IPlanoDao {
    private Connection connection;
    private static IBaseDao baseDao = new BaseDao();

    @Override
    public void insertPlano(PlanoModel plano, List<DependenciaModel> dependencias)  {
        try {
            connection = baseDao.getConnection();
            String sql = "INSERT INTO Plano (Nome, Valor, Descricao) VALUES (?, ?, ?) SELECT SCOPE_IDENTITY()";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, plano.getNome());
            ps.setString(2, plano.getValor());
            ps.setString(3, plano.getDescricao());

            int id = 0;
            ResultSet result = ps.executeQuery();

            if (result.next()){
                id = result.getInt(1);
            }
            ps.close();

            for(DependenciaModel dependencia : dependencias){
                String sqlQuery = "INSERT INTO PlanoDependencia (TempoPermanencia, IdPlano, IdDependencia) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, "4 Horas");
                preparedStatement.setInt(2, id);
                preparedStatement.setInt(3, dependencia.getId());

                preparedStatement.execute();
                preparedStatement.close();
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<PlanoModel> getAllPlanos(String nome) {
        List<PlanoModel> listaPlanos = new ArrayList<>();
        try {
            connection = baseDao.getConnection();
            String sql = "select * from plano where nome like ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"%" + nome +  "%");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                PlanoModel plano = new PlanoModel();
                plano.setId(result.getInt("Id"));
                plano.setNome(result.getString("Nome"));
                plano.setDescricao(result.getString(("Descricao")));
                plano.setValor(result.getString(("Valor")));

                listaPlanos.add(plano);
            }
            ps.close();


        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return listaPlanos;
    }
    @Override
    public PlanoModel planoByUsuario(int idUsuario) {
        PlanoModel plano = new PlanoModel();
        try {
            connection = baseDao.getConnection();
            String sql = "select * from plano p inner join usuario u on p.Id  = u.IdPlano "+
                    "where u.idPlano = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,idUsuario+"");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                plano.setId(result.getInt("Id"));
                plano.setNome(result.getString("Nome"));
                plano.setDescricao(result.getString(("Descricao")));
                plano.setValor(result.getString(("Valor")));

            }
            ps.close();


        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return plano;
    }

    @Override
    public PlanoModel planoByName(String nomePlano) {
        PlanoModel plano = new PlanoModel();
        try {
            connection = baseDao.getConnection();
            String sql = "select * from plano where nome = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,nomePlano+"");
            ResultSet result = ps.executeQuery();
            while(result.next()){
                plano.setId(result.getInt("Id"));
                plano.setNome(result.getString("Nome"));
                plano.setDescricao(result.getString(("Descricao")));
                plano.setValor(result.getString(("Valor")));

            }
            ps.close();


        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return plano;
    }
}
