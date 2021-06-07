package DAO;

import DAO.Interface.IBaseDao;
import DAO.Interface.IPlanoDao;
import Model.PlanoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDao implements IPlanoDao {
    private Connection connection;

    public PlanoDao() throws SQLException, ClassNotFoundException {
        IBaseDao baseDao = new BaseDao();
        connection = baseDao.getConnection();
    }

    @Override
    public void insertPlano(PlanoModel plano) throws SQLException {
        String sql = "INSERT INTO Plano (Nome, Valor, Descricao) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, plano.getNome());
        ps.setString(2, plano.getValor());
        ps.setString(3, plano.getDescricao());

        ps.execute();
        ps.close();
    }

    @Override
    public List<PlanoModel> getAllPlanos() throws SQLException {
        List<PlanoModel> listaPlanos = new ArrayList<PlanoModel>();

        String sql = "SELECT * FROM Plano";
        PreparedStatement ps = connection.prepareStatement(sql);
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

        return listaPlanos;
    }
}
