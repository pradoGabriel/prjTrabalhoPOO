package DAO.Interface;

import Model.ReservaModel;

import java.sql.SQLException;
import java.util.List;

public interface IReservaDao {
    public void insertReserva(ReservaModel reserva) throws SQLException;
    public void updateReserva(ReservaModel reserva) throws SQLException;
    public void deleteReserva(int idReserva) throws SQLException;
    public List<ReservaModel> getReservasByUsuario(int idUsuario) throws SQLException;
}
