import Control.ReservaControl;
import Control.DependenciaControl;
import Control.UsuarioControl;
import Model.DependenciaModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TelaReserva implements TelaStrategy {
    private Label lblNome = new Label("Dependência: ");
    private Label lblData = new Label("Data da reserva: ");

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnExcluir = new Button("Excluir");
    private Button btnAtualizar = new Button("Atualizar");

    private TextField txtNome = new TextField();
    private TextField txtData = new TextField();
    private TextArea txtDesc = new TextArea();
    private ComboBox depenBox;

    private UsuarioControl usuarioControl = new UsuarioControl();
    private ReservaControl reservaControl  = new ReservaControl();
    private DependenciaControl dependenciaControl  = new DependenciaControl();
    private List<DependenciaModel> listDependencia = new ArrayList<>();
    @Override
    public Pane gerarTela() {
        depenBox = new ComboBox();
        FlowPane fButton = new FlowPane();
        fButton.setAlignment(Pos.CENTER);
        fButton.setHgap(30);
        GridPane telaReserva = new GridPane();
        telaReserva.setHgap(5);
        telaReserva.setVgap(20);
        txtNome.setPromptText("Nome do Local");
        txtData.setPromptText(String.valueOf(LocalDate.now()));
        txtDesc.setPromptText("Descricao do local");
        txtDesc.setMinSize(150,100);
        depenBox.getItems();
        telaReserva.add(lblNome,0,1);
        telaReserva.add(depenBox,1,1);
        telaReserva.add(lblData,2,1);
        telaReserva.add(txtData,3,1);
        telaReserva.add(txtDesc,0,2,4,1);
        fButton.getChildren().addAll(btnAdicionar,btnPesquisar,btnExcluir,btnAtualizar);
        telaReserva.add(fButton,0,3,4,1);

        reservaControl.generatedTable(usuarioControl.getEntity());
        telaReserva.add(reservaControl.getTable(),0,4,4,1);
        adicionarComboBox();
        adicionarMargens(telaReserva,fButton);

        depenBox.setOnAction((e)->{
            dependenciaControl.setDependenciaByName(depenBox.getSelectionModel().getSelectedItem()+"");
        });

        btnAdicionar.setOnAction((e)->{
            reservaControl.adicionar(usuarioControl.getEntity(),dependenciaControl.getEntity());
        });

        btnPesquisar.setOnAction((e)->{
            reservaControl.getReservasByNome(usuarioControl.getId(), depenBox.getPromptText());
        });

        btnExcluir.setOnAction((e)->{
            reservaControl.setUsuario(usuarioControl.getEntity());
            reservaControl.deleteReserva(usuarioControl.getId());
        });

        btnAtualizar.setOnAction((e)->{
            reservaControl.setUsuario(usuarioControl.getEntity());
            reservaControl.updateReserva(usuarioControl.getId());
        });


        Bindings.bindBidirectional(depenBox.promptTextProperty(), dependenciaControl.nomeProperty());

        Bindings.bindBidirectional(txtDesc.textProperty(), reservaControl.descDependenciaProperty());
        Bindings.bindBidirectional(txtDesc.textProperty(), dependenciaControl.descricaoProperty());
        Bindings.bindBidirectional(txtData.textProperty(),reservaControl.dataReservaProperty());
        Bindings.bindBidirectional(depenBox.promptTextProperty(), reservaControl.nomeDependenciaProperty());
        return telaReserva;
    }

    private void adicionarMargens(GridPane telaReserva,FlowPane fp){
        telaReserva.setMargin(lblNome, new Insets(0,0,0,100));
        telaReserva.setMargin(txtDesc, new Insets(0,0,0,100));
        telaReserva.setMargin(fp, new Insets(0,0,0,100));
        telaReserva.setMargin(reservaControl.getTable(), new Insets(0,0,0,100));
    }
    private void adicionarComboBox( ){
        listDependencia = dependenciaControl.retornaDependencia(usuarioControl.getEntity());
        for(DependenciaModel p : listDependencia)
            depenBox.getItems().add((p.getNome()));
        depenBox.getSelectionModel().selectFirst();
        dependenciaControl.setDependenciaByName(depenBox.getSelectionModel().getSelectedItem()+"");

    }

}
