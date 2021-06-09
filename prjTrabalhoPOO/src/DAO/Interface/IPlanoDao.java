package DAO.Interface;

import Model.DependenciaModel;
import Model.PlanoModel;

import java.sql.SQLException;
import java.util.List;

public interface IPlanoDao {
    public void insertPlano(PlanoModel plano, List<DependenciaModel> lista);
    public List<PlanoModel> getAllPlanos(String nome);
    public PlanoModel planoByUsuario(int idUsuario);
    public PlanoModel planoByName(String nomePlano);
}
