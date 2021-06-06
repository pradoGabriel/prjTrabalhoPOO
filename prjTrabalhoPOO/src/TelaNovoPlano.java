package sample;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;

public class TelaNovoPlano implements TelaStrategy {

    private Label lblNome = new Label("Nome");
    private Label lblValor = new Label("CPF");
    private Label lblPlano = new Label ("Plano");
    private Label lblDesc = new Label ("Detalhes do Plano");

    private TextField txtNome = new TextField();
    private TextField txtValor = new TextField();
    private TextField txtPlano = new TextField();
    private TextArea txtDesc = new TextArea();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnExcluir = new Button("Excluir");
    private Button btnAtualizar = new Button("Atualizar");

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
        fpButton.getChildren().addAll(btnAdicionar,btnPesquisar,btnExcluir,btnAtualizar);
        telaNovoPlano.add(fpButton,1,3,5,1);
        panePrincipal.setCenter(telaNovoPlano);
        adicionarMargens(telaNovoPlano,fpButton);

        btnAdicionar.setOnAction((e)->{
            System.out.println("Botao adicionar plano pressionado");
        });

        btnPesquisar.setOnAction((e)->{
            System.out.println("Botao pesquisar reserva pressionado");
        });

        btnExcluir.setOnAction((e)->{
            System.out.println("Botao excluir reserva pressionado");
        });

        btnAtualizar.setOnAction((e)->{
            System.out.println("Botao atualizar reserva pressionado");
        });

        StringConverter floatToStringConverter = new FloatStringConverter();
 /*
        Bindings.bindBidirectional(txtNome.textProperty(),control.dataProperty());
        Bindings.bindBidirectional(txtValor.textProperty(),control.dataProperty());
        Bindings.bindBidirectional(txtPlano.textProperty(),control.dataProperty());
        Bindings.bindBidirectional(txtDesc.textProperty(),control.dataProperty(),floatToStringConverter);
      */

        return panePrincipal;
    }

    public void adicionarMargens(GridPane telaNovoPlano, FlowPane fpButton){
        telaNovoPlano.setMargin(lblNome, new Insets(0,0,0,100));
        telaNovoPlano.setMargin(lblValor, new Insets(0,0,0,100));
        telaNovoPlano.setMargin(lblPlano, new Insets(0,0,0,100));
        telaNovoPlano.setMargin(btnAdicionar, new Insets(0,0,0,100));
    }
}
