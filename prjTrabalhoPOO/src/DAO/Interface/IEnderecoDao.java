package DAO.Interface;


import Model.EnderecoModel;

import java.sql.SQLException;

public interface IEnderecoDao {
    public int insertEndereco(EnderecoModel endereco);
}
