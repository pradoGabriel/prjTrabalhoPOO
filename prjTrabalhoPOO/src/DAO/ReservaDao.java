package DAO;

import DAO.Interface.IBaseDao;
import DAO.Interface.IReservaDao;
import Model.DependenciaModel;
import Model.ReservaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao implements IReservaDao {
    private Connection connection;
    private static IBaseDao baseDao = new BaseDao();


    @Override
    public void insertReserva(ReservaModel reserva) {
        try {
            connection = baseDao.getConnection();
            String sql = "INSERT INTO Reserva (IdDependencia, IdUsuario, DataReserva) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, reserva.getDependencia().getId());
            ps.setInt(2, reserva.getUsuario().getId());
            ps.setString(3, reserva.getDataReserva());

            ps.execute();
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateReserva(ReservaModel reserva) {
        try {
            connection = baseDao.getConnection();
            String sql = "UPDATE Reserva SET IdDependencia = ?, IdUsuario = ?, DataReserva = ? WHERE Id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 2);
            ps.setInt(2, reserva.getUsuario().getId());
            ps.setString(3, reserva.getDataReserva());
            ps.setInt(4, reserva.getId());

            ps.execute();
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReserva(int idReserva){
        try {
            connection = baseDao.getConnection();
            String sql = "DELETE Reserva WHERE Id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idReserva);
            ps.execute();
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ReservaModel> getReservasByUsuario(int idUsuario){
        List<ReservaModel> listaReservas = new ArrayList<ReservaModel>();

        try {
            connection = baseDao.getConnection();
            String sql = "SELECT R.Id, R.DataReserva, D.Nome AS NomeDependencia, D.Descricao FROM Reserva R" +
                    "                INNER JOIN Dependencia D ON R.IdDependencia = D.Id WHERE IdUsuario = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                DependenciaModel dependencia = new DependenciaModel();
                ReservaModel reserva = new ReservaModel();
                reserva.setId(result.getInt("Id"));
                reserva.setDataReserva(result.getString("DataReserva"));
                dependencia.setNome(result.getString("NomeDependencia"));
                dependencia.setDescricao(result.getString("Descricao"));
                reserva.setDependencia(dependencia);

                listaReservas.add(reserva);
            }
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return listaReservas;
    }
    @Override
    public List<ReservaModel> getReservasByNome(int idUsuario,String pesquisa){
        List<ReservaModel> listaReservas = new ArrayList<ReservaModel>();

        try {
            connection = baseDao.getConnection();
            String sql = "SELECT R.Id, R.DataReserva, D.Nome AS NomeDependencia, D.Descricao FROM Reserva R" +
                    "                INNER JOIN Dependencia D ON R.IdDependencia = D.Id WHERE IdUsuario = ? AND D.Nome LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setString(2, pesquisa);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                DependenciaModel dependencia = new DependenciaModel();
                ReservaModel reserva = new ReservaModel();
                reserva.setId(result.getInt("Id"));
                reserva.setDataReserva(result.getString("DataReserva"));
                dependencia.setNome(result.getString("NomeDependencia"));
                dependencia.setDescricao(result.getString("Descricao"));
                reserva.setDependencia(dependencia);

                listaReservas.add(reserva);
            }
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return listaReservas;
    }
}
