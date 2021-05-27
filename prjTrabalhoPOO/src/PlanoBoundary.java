import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;


public class PlanoBoundary  extends Application {

    private Label lblTitulo = new Label("Cadastro de Plano");
    private Label lblNome = new Label("Nome do Plano: ");
    private Label lblValor = new Label("Valor do Plano: ");

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");

    private PlanoControl control = new PlanoControl();

    private  TextField txtNome = new TextField("Digite o nome");
    private TextField txtValor = new TextField("0,0");
    private TextArea txtDesc = new TextArea("Descriçao");


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane panePrincipal = new BorderPane();
        GridPane gp = new GridPane();

        Scene scn = new Scene(panePrincipal,500,400);
        gp.setHgap(5);
        gp.setVgap(20);

        txtDesc.setMinSize(200,100);


        gp.add(lblNome,0,1);
        gp.add(txtNome,1,1);
        gp.add(lblValor,2,1);
        gp.add(txtValor,3,1);
        gp.add(txtDesc,0,2,4,1);
        gp.add(btnAdicionar,0,3);
        gp.add(btnPesquisar,1,3);

        control.generatedTable();
        panePrincipal.setTop(lblTitulo);
        BorderPane.setAlignment(lblTitulo,Pos.CENTER);
        panePrincipal.setCenter(gp);
        panePrincipal.setBottom(control.getTable());

        btnAdicionar.setOnAction((e)-> control.adicionar());
        btnPesquisar.setOnAction((e)-> control.pesquisarPorNome(txtNome.getText()));

        StringConverter floatStringConverter = new FloatStringConverter();

        Bindings.bindBidirectional(txtValor.textProperty(), control.valor, floatStringConverter);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomePlano);
        Bindings.bindBidirectional(txtDesc.textProperty(), control.descricaoPlano);

        stage.setScene(scn);
        stage.setTitle("Cadastro de Planos");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch();
    }
}
