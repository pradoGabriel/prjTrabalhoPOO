

import Control.EnderecoControl;
import Control.PlanoControl;
import Control.UsuarioControl;
import Model.PlanoModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.List;

public class TelaCadastro implements TelaStrategy, ChamadorTelas {
    private ChamadorTelas chamador;
    private Label lblNome = new Label("Nome");
    private Label lblSenha = new Label("Senha");
    private Label lblCpf = new Label("CPF ");
    private Label lblPlano = new Label("Plano");
    private Label lblLogradouro = new Label("Logradouro");
    private Label lblNumero = new Label("Número");
    private Label lblComplemento = new Label("Complemento");
    private Label lblCep = new Label("CEP");
    private Label lblUF = new Label("UF");

    private TextField txtNome = new TextField();
    private TextField txtSenha = new TextField();
    private TextField txtCpf = new TextField();
    private TextField txtLogradouro = new TextField();
    private TextField txtNumero = new TextField();
    private TextField txtComplemento = new TextField();
    private TextField txtCep = new TextField();
    private TextField txtUF = new TextField();
    private ComboBox planoBox = new ComboBox();

    private Button btnVoltar = new Button("Voltar");
    private Button btnPagamento = new Button("Avançar");
    private UsuarioControl usuarioControl = new UsuarioControl();
    private EnderecoControl enderecoControl = new EnderecoControl();
    private PlanoControl planoControl = new PlanoControl();

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
        telaCadastro.add(lblSenha, 0, 1);
        telaCadastro.add(txtSenha, 1, 1);
        telaCadastro.add(lblCpf, 0, 2);
        telaCadastro.add(txtCpf, 1, 2);
        telaCadastro.add(lblLogradouro, 0, 3);
        telaCadastro.add(txtLogradouro, 1, 3);
        telaCadastro.add(lblNumero, 0, 4);
        telaCadastro.add(txtNumero, 1, 4);
        telaCadastro.add(lblComplemento, 0, 5);
        telaCadastro.add(txtComplemento, 1, 5);
        telaCadastro.add(lblCep, 0, 6);
        telaCadastro.add(txtCep, 1, 6);
        telaCadastro.add(lblUF, 0, 7);
        telaCadastro.add(txtUF, 1, 7);
        telaCadastro.add(lblPlano, 0, 8);
        telaCadastro.add(planoBox, 1, 8);
        telaCadastro.add(btnPagamento, 1, 9);
        adicionarComboBox();
        adicionarMargens(telaCadastro);

        StringConverter intToStringConverter = new IntegerStringConverter();

        btnPagamento.setOnAction((e) -> {
            planoControl.planoByName(planoBox.getSelectionModel().getSelectedItem()+"");
            usuarioControl.setPlano(planoControl.getEntity());
            chamador.chamarTelas("Pagamento");
        });

        Bindings.bindBidirectional(txtNome.textProperty(), usuarioControl.nomeProperty());
        Bindings.bindBidirectional(txtSenha.textProperty(), usuarioControl.senhaProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(), usuarioControl.cpfProperty());
        Bindings.bindBidirectional(txtLogradouro.textProperty(),enderecoControl.lougradouroProperty());
        Bindings.bindBidirectional(txtNumero.textProperty(),enderecoControl.numProperty(), intToStringConverter);
        Bindings.bindBidirectional(txtComplemento.textProperty(),enderecoControl.complementoProperty());
        Bindings.bindBidirectional(txtCep.textProperty(),enderecoControl.cepProperty());
        Bindings.bindBidirectional(txtUF.textProperty(),enderecoControl.ufProperty());
        Bindings.bindBidirectional(planoBox.promptTextProperty(),planoControl.nomeProperty());
        return telaCadastro;
    }

    private void adicionarMargens(GridPane telaCadastro) {
        telaCadastro.setMargin(lblNome, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblSenha, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblCpf, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblLogradouro, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblNumero, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblComplemento, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblCep, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblUF, new Insets(0, 0, 0, 100));
        telaCadastro.setMargin(lblPlano, new Insets(0, 0, 0, 100));
    }

    private void adicionarComboBox( ){
        List<PlanoModel> list = new ArrayList<>();
        list = planoControl.retornaPlano();
        for(PlanoModel p : list)
        planoBox.getItems().add((p.getNome()));
        planoBox.getSelectionModel().selectFirst();
    }

    @Override
    public void chamarTelas(String acao) {

    }
}


