package Control;

import DAO.Interface.IPlanoDao;
import DAO.PlanoDao;
import Model.DependenciaModel;
import Model.PlanoModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;

public class PlanoControl {
    private ObservableList<PlanoModel> planos = FXCollections.observableArrayList();

    private TableView<PlanoModel> table = new TableView<>();

    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private static StringProperty valor = new SimpleStringProperty("");
    private StringProperty descricao = new SimpleStringProperty("");

    private IPlanoDao planoDAO = new PlanoDao();
    public void setEntity(PlanoModel p) {
        if (p != null) {
            id.set((p.getId()));
            nome.set(p.getNome());
            valor.set(p.getValor());
            descricao.set(p.getDescricao());
        }
    }

    public PlanoModel getEntity() {
        PlanoModel p = new PlanoModel();
        p.setId(id.get());
        p.setNome(nome.get());
        p.setValor(valor.get());
        p.setDescricao(descricao.get());
        return p;
    }

    public void adicionar(List<DependenciaModel> dependencias) {
        PlanoModel p = getEntity();
        planoDAO.insertPlano(p,dependencias);
        pesquisarPorNome("");
    }

    public void pesquisarPorNome(String nome){
        List<PlanoModel> lista = planoDAO.getAllPlanos(nome);
        planos.clear();
        planos.addAll(lista);
    }

    public List retornaPlano(){
        pesquisarPorNome("");
        return planos;
    }
    public void planoByName(String nomePlano){
        setEntity(planoDAO.planoByName(nomePlano));
    }

    public void planoByUsuario(int idUsuario){
        setEntity(planoDAO.planoByUsuario(idUsuario));
    }

    public void generatedTable(){
        pesquisarPorNome("");

        TableColumn<PlanoModel, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(70);

        TableColumn<PlanoModel, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<PlanoModel, String>("nome"));
        colNome.setPrefWidth(300);

        TableColumn<PlanoModel, String> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(new PropertyValueFactory<PlanoModel, String>("valor"));
        colValor.setPrefWidth(70);


        table.getColumns().addAll(colId, colNome, colValor);
        table.setMaxWidth(500);
        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    setEntity(novo);
                }
        );

        table.setItems(planos);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getValor() {
        return valor.get();
    }

    public StringProperty valorProperty() {
        return valor;
    }

    public String getDescricao() {
        return descricao.get();
    }

    public StringProperty descricaoProperty() {
        return descricao;
    }

    public TableView<PlanoModel> getTable() {
        return table;
    }

}
