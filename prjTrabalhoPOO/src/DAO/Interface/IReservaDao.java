package DAO.Interface;

import Model.ReservaModel;

import java.sql.SQLException;
import java.util.List;

public interface IReservaDao {
    public void insertReserva(ReservaModel reserva);
    public void updateReserva(ReservaModel reserva);
    public void deleteReserva(int idReserva);
    public List<ReservaModel> getReservasByUsuario(int idUsuario);
    public List<ReservaModel> getReservasByNome(int idUsuario,String pesquisa);
}
