package Control;

import DAO.DependenciaDao;
import DAO.Interface.IDependenciaDao;
import Model.DependenciaModel;
import Model.UsuarioModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DependenciaControl {


    private ObservableList<DependenciaModel> dependencias = FXCollections.observableArrayList();

    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty descricao = new SimpleStringProperty("");

    private IDependenciaDao dependenciaDao = new DependenciaDao();

    public void setEntity(DependenciaModel d) {
        if (d != null) {
            id.set(d.getId());
            nome.set(d.getNome());
            descricao.set(d.getDescricao());
        }
    }
    public DependenciaModel getEntity() {
        DependenciaModel d = new DependenciaModel();
        d.setId(id.get());
        d.setNome(nome.get());
        d.setDescricao(descricao.get());
        return d;
    }

    public void getDependenciaByPlano(int idPlano){
        List<DependenciaModel> lista = dependenciaDao.getDependenciasByPlano(idPlano);
        dependencias.clear();
        dependencias.addAll(lista);
    }

    public List<DependenciaModel> getAllDependencias(){
        return dependenciaDao.getAllDependencias();
    }

    public void setDependenciaByName(String nome){
        DependenciaModel d;
        d = dependenciaDao.getDependenciaByName(nome);
        setEntity(d);
    }

    public List retornaDependencia(UsuarioModel usuario){
        getDependenciaByPlano(usuario.getPlano().getId());
        return dependencias;
    }

    public String getNome() { return nome.get(); }

    public IntegerProperty idProperty() { return id; }

    public StringProperty nomeProperty() { return nome; }

    public String getDescricao() {return descricao.get(); }

    public StringProperty descricaoProperty() {return descricao; }

    public int getId() { return id.get(); }

    public ObservableList<DependenciaModel> getDependencias() {
        return dependencias;
    }
}
