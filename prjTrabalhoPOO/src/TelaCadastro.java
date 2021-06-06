package sample;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class TelaCadastro implements TelaStrategy, ChamadorTelas {
    private ChamadorTelas chamador;
    private Label lblNome = new Label("Nome");
    private Label lblCpf = new Label("CPF ");
    private Label lblPlano = new Label("Plano");
    private Label lblLogradouro = new Label("Logradouro");
    private Label lblNumero = new Label("Número");
    private Label lblComplemento = new Label("Complemento");
    private Label lblCep = new Label("CEP");
    private Label lblUF = new Label("UF");

    private TextField txtNome = new TextField();
    private TextField txtCpf = new TextField();
    private TextField txtPlano = new TextField();
    private TextField txtLogradouro = new TextField();
    private TextField txtNumero = new TextField();
    private TextField txtComplemento = new TextField();
    private TextField txtCep = new TextField();
    private TextField txtUF = new TextField();

    private Button btnVoltar = new Button("Voltar");
    private Button btnPagamento = new Button("Pagamento");

    TelaCadastro(ChamadorTelas chamador) {
        this.chamador = chamador;
    }

    @Override
    public Pane gerarTela() {
        GridPane telaCadastro = new GridPane();
        telaCadastro.setHgap(5);
        telaCadastro.setVgap(20);
        telaCadastro.add(lblNome, 0, 0);
        telaCadastro.add(txtNome, 1, 0);
        telaCadastro.add(lblCpf, 0, 1);
        telaCadastro.add(txtCpf, 1, 1);
        telaCadastro.add(lblLogradouro, 0, 2);
        telaCadastro.add(txtLogradouro, 1, 2);
        telaCadastro.add(lblNumero, 0, 3);
        telaCadastro.add(txtNumero, 1, 3);
        telaCadastro.add(lblComplemento, 0, 4);
        telaCadastro.add(txtComplemento, 1, 4);
        telaCadastro.add(lblCep, 0, 5);
        telaCadastro.add(txtCep, 1, 5);
        telaCadastro.add(lblUF, 0, 6);
        telaCadastro.add(txtUF, 1, 6);
        telaCadastro.add(btnPagamento, 1, 7);
        adicionarMargens(telaCadastro);

        StringConverter intToStringConverter = new IntegerStringConverter();

        btnPagamento.setOnAction((e) -> {
            //lblTitulo.setText("Reserva");
            chamador.chamarTelas("Pagamento");
        });
        /*
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtLogradouro.textProperty(),control.dataProperty());
        Bindings.bindBidirectional(txtNumero.textProperty(),control.dataProperty(), intToStringConverter);
        Bindings.bindBidirectional(txtComplemento.textProperty(),control.dataProperty());
        Bindings.bindBidirectional(txtCep.textProperty(),control.dataProperty());
        Bindings.bindBidirectional(txtUF.textProperty(),control.dataProperty());
        */
        return telaCadastro;
    }

    private void adicionarMargens(GridPane telaCadastro) {
        telaCadastro.setMargin(lblNome, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblCpf, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblLogradouro, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblNumero, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblComplemento, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblCep, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblUF, new Insets(0, 0, 0, 100));
    }

    @Override
    public void chamarTelas(String acao) {

    }
}


