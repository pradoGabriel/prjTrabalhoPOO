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

    public ReservaDao() throws SQLException, ClassNotFoundException {
        IBaseDao baseDao = new BaseDao();
        connection = baseDao.getConnection();
    }

    @Override
    public void insertReserva(ReservaModel reserva) throws SQLException {
        String sql = "INSERT INTO Reserva (IdDependencia, IdUsuario, DataReserva) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, reserva.getDependencia().getId());
        ps.setInt(2, reserva.getUsuario().getId());
        ps.setString(3, reserva.getDataReserva());

        ps.execute();
        ps.close();
    }

    @Override
    public void updateReserva(ReservaModel reserva) throws SQLException {
        String sql = "UPDATE Reserva SET IdDependencia = ?, IdUsuario = ?, DataReserva = ? WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, reserva.getDependencia().getId());
        ps.setInt(2, reserva.getUsuario().getId());
        ps.setString(3, reserva.getDataReserva());
        ps.setInt(4, reserva.getId());

        ps.execute();
        ps.close();
    }

    @Override
    public void deleteReserva(int idReserva) throws SQLException {
        String sql = "DELETE Reserva WHERE Id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idReserva);
        ps.execute();
        ps.close();
    }

    @Override
    public List<ReservaModel> getReservasByUsuario(int idUsuario) throws SQLException {
        List<ReservaModel> listaReservas = new ArrayList<ReservaModel>();

        String sql = "SELECT R.Id, R.DataReserva, D.Nome AS NomeDependencia, D.Descricao FROM Reserva R" +
                "                INNER JOIN Dependencia D ON R.IdDependencia = D.Id WHERE IdUsuario = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idUsuario);

        ResultSet result = ps.executeQuery();
        while(result.next()){
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

        return listaReservas;
    }
}
