package DAO;

import DAO.Interface.IBaseDao;
import DAO.Interface.IEnderecoDao;
import DAO.Interface.IPagamentoDao;
import DAO.Interface.IUsuarioDao;
import Model.EnderecoModel;
import Model.PagamentoModel;
import Model.PlanoModel;
import Model.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao implements IUsuarioDao {
    private Connection connection;
    private static IBaseDao baseDao = new BaseDao();
    private int id=1;
    @Override
    public void insertUsuario(UsuarioModel usuario) {
        try {
            connection = baseDao.getConnection();
            IEnderecoDao enderecoDao = new EnderecoDao();
            IPagamentoDao pagamentoDao = new PagamentoDao();

            usuario.getEndereco().setId(enderecoDao.insertEndereco(usuario.getEndereco()));
            usuario.getPagamento().setId(pagamentoDao.insertPagamento(usuario.getPagamento()));

            String sql = "INSERT INTO Usuario (Nome, Senha, Cpf, IsAdmin, IdPlano, IdEndereco, IdPagamento)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getCpf());
            ps.setBoolean(4, false);
            ps.setInt(5, usuario.getPlano().getId());
            ps.setInt(6, usuario.getEndereco().getId());
            ps.setInt(7, usuario.getPagamento().getId());

            ps.execute();
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public UsuarioModel login(String nome, String senha) {
        UsuarioModel usuario = new UsuarioModel();
        EnderecoModel endereco = new EnderecoModel();
        PagamentoModel pagamento = new PagamentoModel();
        PlanoModel plano = new PlanoModel();
        try {
            connection = baseDao.getConnection();
            String sql = "SELECT TOP 1 U.Id, U.Nome, U.Cpf, U.IsAdmin," +
                    "PL.Id AS IdPlano, PL.Descricao AS DescricaoPlano," +
                    "PL.Nome AS NomePlano, PL.Valor AS ValorPlano," +
                    "E.Id AS IdEndereco, E.Logradouro, E.Cep, E.Complemento," +
                    "E.Numero, E.Uf, PG.Id AS IdPagamento, PG.CpfTitular," +
                    "PG.NumCartao, PG.ValidadeCartao FROM Usuario U " +
                    "INNER JOIN Plano PL ON U.IdPlano = PL.Id " +
                    "INNER JOIN Endereco E ON U.IdEndereco = E.Id " +
                    "INNER JOIN Pagamento PG ON U.IdPagamento = PG.Id " +
                    "WHERE U.Nome = ? AND U.Senha = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, senha);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                usuario.setId(result.getInt("Id"));
                usuario.setAdmin(result.getBoolean("IsAdmin"));
                usuario.setNome(result.getString("Nome"));
                usuario.setCpf(result.getString("Cpf"));
                endereco.setCep(result.getString("Cep"));
                endereco.setComplemento(result.getString("Complemento"));
                endereco.setId((result.getInt("IdEndereco")));
                endereco.setLogradouro((result.getString("Logradouro")));
                endereco.setUf(result.getString("Uf"));
                endereco.setNum(result.getInt("Numero"));
                plano.setId(result.getInt("IdPlano"));
                plano.setDescricao(result.getString("DescricaoPlano"));
                plano.setValor(result.getString("ValorPlano"));
                plano.setNome(result.getString("NomePlano"));
                pagamento.setId(result.getInt("IdPagamento"));
               // pagamento.setNomeTitular(result.getString("nomeTitular"));
                pagamento.setCpfTitular(result.getString("CpfTitular"));
                pagamento.setNumCartao(result.getString("NumCartao"));
                pagamento.setValidadeCartao(result.getString("ValidadeCartao"));

                usuario.setEndereco(endereco);
                usuario.setPagamento(pagamento);
                usuario.setPlano(plano);
            }
            ps.close();
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return usuario;
    }
}
