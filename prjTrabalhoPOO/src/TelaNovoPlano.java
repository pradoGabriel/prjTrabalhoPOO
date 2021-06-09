
import Control.DependenciaControl;
import Control.PlanoControl;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class TelaNovoPlano implements TelaStrategy {

    private Label lblNome = new Label("Nome");
    private Label lblValor = new Label("Valor");
    private Label lblPlano = new Label ("Plano");

    private TextField txtNome = new TextField();
    private TextField txtValor = new TextField();
    private TextArea txtDesc = new TextArea();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private static int tabelacount = 0;
    private PlanoControl planoControl = new PlanoControl();
    private DependenciaControl dependenciaControl = new DependenciaControl();

    @Override
    public Pane gerarTela() {

        BorderPane panePrincipal = new BorderPane();
        GridPane telaNovoPlano = new GridPane();
        FlowPane fpButton = new FlowPane();
        fpButton.setAlignment(Pos.CENTER);
        fpButton.setHgap(30);
        telaNovoPlano.setHgap(5);
        telaNovoPlano.setVgap(20);
        txtDesc.setMinSize(150,100);
        telaNovoPlano.add(lblNome,0,1);
        telaNovoPlano.add(txtNome,1,1);
        telaNovoPlano.add(lblValor,2,1);
        telaNovoPlano.add(txtValor,3,1,2,1);
        telaNovoPlano.add(lblPlano,0,2);
        telaNovoPlano.add(txtDesc,1,2,4,1);
        fpButton.getChildren().addAll(btnAdicionar,btnPesquisar);
        telaNovoPlano.add(fpButton,1,3,5,1);
        telaNovoPlano.add(planoControl.getTable(),0,4,5,1);
        panePrincipal.setCenter(telaNovoPlano);

        if(tabelacount == 0) {
            planoControl.generatedTable();
                tabelacount++;
        }
        adicionarMargens(telaNovoPlano,fpButton);

        btnAdicionar.setOnAction((e)-> planoControl.adicionar(dependenciaControl.getAllDependencias()));
        btnPesquisar.setOnAction((e)-> planoControl.pesquisarPorNome(txtNome.getText()));

        Bindings.bindBidirectional(txtNome.textProperty(),planoControl.nomeProperty());
        Bindings.bindBidirectional(txtValor.textProperty(),planoControl.valorProperty());
        Bindings.bindBidirectional(txtDesc.textProperty(),planoControl.descricaoProperty());

        return panePrincipal;
    }

        public void adicionarMargens(GridPane telaNovoPlano, FlowPane fpButton){
            telaNovoPlano.setMargin(lblNome, new Insets(0,0,0,100));
            telaNovoPlano.setMargin(lblValor, new Insets(0,0,0,100));
            telaNovoPlano.setMargin(lblPlano, new Insets(0,0,0,100));
            telaNovoPlano.setMargin(btnAdicionar, new Insets(0,0,0,100));
            telaNovoPlano.setMargin(planoControl.getTable(), new Insets(0,0,0,100));
        }
}
