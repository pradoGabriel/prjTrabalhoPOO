package sample;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.time.LocalDate;

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


    @Override
    public Pane gerarTela() {
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
        telaReserva.add(lblNome,0,1);
        telaReserva.add(txtNome,1,1);
        telaReserva.add(lblData,2,1);
        telaReserva.add(txtData,3,1);
        telaReserva.add(txtDesc,0,2,4,1);
        fButton.getChildren().addAll(btnAdicionar,btnPesquisar,btnExcluir,btnAtualizar);
        telaReserva.add(fButton,0,3,4,1);

       // control.generatedTable();
        adicionarMargens(telaReserva,fButton);

        btnAdicionar.setOnAction((e)->{
            System.out.println("Botao adicionar reserva pressionado");
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
       /*
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtDesc.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtData.textProperty(),control.dataProperty(),localDateToStringConverter);
        */

        return telaReserva;
    }

    private void adicionarMargens(GridPane telaReserva,FlowPane fp){
        telaReserva.setMargin(lblNome, new Insets(0,0,0,100));
        telaReserva.setMargin(txtDesc, new Insets(0,0,0,100));
        telaReserva.setMargin(fp, new Insets(0,0,0,100));
    }
}
