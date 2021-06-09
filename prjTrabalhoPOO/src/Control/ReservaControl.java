package Control;

import DAO.Interface.IReservaDao;
import DAO.ReservaDao;
import Model.DependenciaModel;
import Model.ReservaModel;
import Model.UsuarioModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ReservaControl {
    private ObservableList<ReservaModel> reservas = FXCollections.observableArrayList();
    private ObservableList<DependenciaModel> dependencias = FXCollections.observableArrayList();

    private TableView<ReservaModel> table = new TableView<>();

    private IntegerProperty id = new SimpleIntegerProperty(0);

    private ObjectProperty dependencia = new SimpleObjectProperty();
    private static ObjectProperty usuario = new SimpleObjectProperty();
    private SimpleStringProperty nomeDependencia = new SimpleStringProperty("");
    private SimpleStringProperty descDependencia = new SimpleStringProperty("");
    private StringProperty dataReserva = new SimpleStringProperty("");


    private IReservaDao reservaDao = new ReservaDao();

    public void setEntity(ReservaModel r) {
        if (r != null) {
            id.set(r.getId());
            dependencia.set(r.getDependencia());
            descDependencia.set(r.getDependencia().getDescricao());
            nomeDependencia.set(r.getDependencia().getNome());
            usuario.set(r.getUsuario());
            dataReserva.set(r.getDataReserva());
        }
    }

    public ReservaModel getEntity() {
        ReservaModel r = new ReservaModel();
        r.setId(getId());
        r.setDependencia((DependenciaModel) dependencia.get());
        r.setUsuario((UsuarioModel) usuario.get());
        r.setDataReserva(dataReserva.get());
        return r;
    }

    public void adicionar(UsuarioModel u, DependenciaModel d){
        usuario.set(u);
        dependencia.set(d);
        ReservaModel r = getEntity();
        reservaDao.insertReserva(r);
        getReservasByUsuario(u.getId());
    }

    public void updateReserva(int idUsuario) {
        reservaDao.updateReserva(getEntity());
        getReservasByUsuario(idUsuario);
    }

    public void deleteReserva(int idUsuario) {
        reservaDao.deleteReserva(getEntity().getId());
        getReservasByUsuario(idUsuario);
    }

    public void getReservasByUsuario(int idUsuario){
        List<ReservaModel> lista = reservaDao.getReservasByUsuario(idUsuario);
        reservas.clear();
        reservas.addAll(lista);
    }

    public void getReservasByNome(int idUsuario, String pesquisa){
        List<ReservaModel> lista = reservaDao.getReservasByNome(idUsuario, pesquisa);
        reservas.clear();
        reservas.addAll(lista);
    }


    public void generatedTable(UsuarioModel u){
        getReservasByUsuario(u.getId());
        TableColumn<ReservaModel, Integer> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(85);

        TableColumn<ReservaModel, ReservaModel> colDependencia = new TableColumn<>("Dependencia");
        colDependencia.setCellValueFactory(new PropertyValueFactory("dependencia"));
        colDependencia.setPrefWidth(300);

        TableColumn<ReservaModel, String> colReserva = new TableColumn<>("Data de Reserva");
        colReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));
        colReserva.setPrefWidth(120);


        table.getColumns().addAll(colId, colDependencia, colReserva);
        table.setMaxWidth(500);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {

                    setEntity(novo);
                    System.out.println(dependencia);
                }
        );

        table.setItems(reservas);
    }

    public void setUsuario(Object usuario) { this.usuario.set(usuario); }

    public TableView<ReservaModel> getTable() {return table; }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public Object getDependencia() {
        return dependencia.get();
    }

    public ObjectProperty dependenciaProperty() {
        return dependencia;
    }

    public Object getUsuario() {
        return usuario.get();
    }

    public ObjectProperty usuarioProperty() {
        return usuario;
    }

    public String getDataReserva() {
        return dataReserva.get();
    }

    public StringProperty dataReservaProperty() {
        return dataReserva;
    }

    public String getDescDependencia() {
        return descDependencia.get();
    }

    public SimpleStringProperty descDependenciaProperty() {
        return descDependencia;
    }

    public String getNomeDependencia() {
        return nomeDependencia.get();
    }

    public SimpleStringProperty nomeDependenciaProperty() {
        return nomeDependencia;
    }
}
