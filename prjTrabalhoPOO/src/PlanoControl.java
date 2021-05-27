import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class PlanoControl {
    private ObservableList<Plano> planos = FXCollections.observableArrayList();
    private TableView<Plano> table = new TableView<>();

    public FloatProperty valor = new SimpleFloatProperty(0.0f);
    public StringProperty nomePlano = new SimpleStringProperty("");
    public StringProperty descricaoPlano = new SimpleStringProperty("Descrição");


    public Plano getEntity() {
        Plano p = new Plano();
        p.setNomePlano(nomePlano.get());
        p.setValorPlano(valor.get());
        p.setDescricaoPlano(descricaoPlano.get());
        return p;
    }

    public void setEntity(Plano p) {
        if (p != null) {
            valor.set(p.getValorPlano());
            nomePlano.set(p.getNomePlano());
            descricaoPlano.set(p.getDescricaoPlano());
        }
    }

    public void adicionar() {
        planos.add(getEntity());
        this.setEntity(new Plano());
    }

    public void pesquisarPorNome(String nome) {
        for (Plano p : planos) {
            if (p.getNomePlano().contains(nome)) {
                this.setEntity(p);
            }
        }
    }

    public void generatedTable(){
        TableColumn<Plano, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<Plano, String>("nomePlano"));
        colNome.setPrefWidth(300);

        TableColumn<Plano, Float> colValor = new TableColumn<>("Valor");
        colValor.setPrefWidth(200);
        colValor.setCellValueFactory(new PropertyValueFactory<Plano, Float>("valorPlano"));

        table.getColumns().addAll(colNome,colValor);

        table.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) ->  setEntity(novo));
        table.setItems(planos);
    }


    public TableView<Plano> getTable() {
        return table;
    }
}
