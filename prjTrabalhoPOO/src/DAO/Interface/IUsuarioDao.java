package DAO.Interface;

import Model.PlanoModel;
import Model.UsuarioModel;

import java.sql.SQLException;

public interface IUsuarioDao {
    public void insertUsuario(UsuarioModel usuario) throws SQLException, ClassNotFoundException;
    public UsuarioModel login(String nome, String senha) throws SQLException;
}
