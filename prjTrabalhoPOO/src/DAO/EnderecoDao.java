package DAO;

import DAO.Interface.IBaseDao;
import DAO.Interface.IEnderecoDao;
import Model.EnderecoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDao implements IEnderecoDao {
    private Connection connection;
    private static IBaseDao baseDao = new BaseDao();
    private int id;

    @Override
    public int insertEndereco(EnderecoModel endereco){
        try {
            connection = baseDao.getConnection();
            String sql = "INSERT INTO Endereco (Logradouro, Numero, Complemento, Cep, Uf) VALUES (?, ?, ?, ?, ?) SELECT SCOPE_IDENTITY()";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, endereco.getLogradouro());
            ps.setInt(2, endereco.getNum());
            ps.setString(3, endereco.getComplemento());
            ps.setString(4, endereco.getCep());
            ps.setString(5, endereco.getUf());

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                id = result.getInt(1);
            }

            ps.close();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return id;
    }
}
