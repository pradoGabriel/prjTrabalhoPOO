

import Control.EnderecoControl;
import Control.PagamentoControl;
import Control.PlanoControl;
import Control.UsuarioControl;
import Model.UsuarioModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TelaPagamento implements TelaStrategy, ChamadorTelas {
    private ChamadorTelas chamador;
    private Label lblNome = new Label("Nome do titular");
    private Label lblCpf = new Label("CPF do titular");
    private Label lblCartao = new Label("Número do Cartão");
    private Label lblCvv= new Label("CVV");
    private Label lblValidade = new Label("Número");
    private Label lblValor = new Label("Valor");

    private TextField txtNome = new TextField();
    private TextField txtCpf = new TextField();
    private TextField txtCartao = new TextField();
    private TextField txtCvv = new TextField();
    private TextField txtValidade = new TextField();
    private TextField txtValor = new TextField();

    private Button btnConfirmar = new Button("Confirmar");

    private UsuarioControl usuarioControl = new UsuarioControl();
    private PagamentoControl pagamentoControl = new PagamentoControl();
    private EnderecoControl enderecoControl = new EnderecoControl();
    private PlanoControl planoControl = new PlanoControl();
    TelaPagamento(ChamadorTelas chamador){
        this.chamador = chamador;
    }
    @Override
    public Pane gerarTela() {
        GridPane telaPagamento = new GridPane();
        telaPagamento.setHgap(5);
        telaPagamento.setVgap(20);
        telaPagamento.add(lblNome,0,0);
        telaPagamento.add(txtNome,1,0);
        telaPagamento.add(lblCpf,0,1);
        telaPagamento.add(txtCpf,1,1);
        telaPagamento.add(lblCartao,0,2);
        telaPagamento.add(txtCartao,1,2);
        telaPagamento.add(lblCvv,0,3);
        telaPagamento.add(txtCvv,1,3);
        telaPagamento.add(lblValidade,0,4);
        telaPagamento.add(txtValidade,1,4);
        telaPagamento.add(lblValor,2,2);
        telaPagamento.add(txtValor,3,2);
        telaPagamento.add(btnConfirmar,0,5);
        adicionarMargens(telaPagamento);

        btnConfirmar.setOnAction((e) -> {
            usuarioControl.setPagamento(pagamentoControl.getEntity());
            usuarioControl.setEndereco(enderecoControl.getEntity());
            UsuarioModel u = usuarioControl.getEntity();
            System.out.println(u.toString());
            usuarioControl.adicionar();
            chamador.chamarTelas("Confirmar");
        });


        Bindings.bindBidirectional(txtNome.textProperty(),pagamentoControl.nomeTitularProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(),pagamentoControl.cpfTitularProperty());
        Bindings.bindBidirectional(txtCartao.textProperty(),pagamentoControl.numCartaoProperty());
        Bindings.bindBidirectional(txtCvv.textProperty(),pagamentoControl.cvvProperty());
        Bindings.bindBidirectional(txtValidade.textProperty(),pagamentoControl.validadeCartaoProperty());
        Bindings.bindBidirectional(txtValor.textProperty(),planoControl.valorProperty());

        return telaPagamento;
    }

    public void adicionarMargens(GridPane telaPagamento){
        telaPagamento.setMargin(lblNome, new Insets(0, 0, 0, 100));
        telaPagamento.setMargin(lblCpf, new Insets(0, 0, 0, 100));
        telaPagamento.setMargin(lblCartao, new Insets(0, 0, 0, 100));
        telaPagamento.setMargin(lblCvv, new Insets(0, 0, 0, 100));
        telaPagamento.setMargin(lblValidade, new Insets(0, 0, 0, 100));
        telaPagamento.setMargin(lblValor, new Insets(0, 0, 0, 100));
        telaPagamento.setMargin(btnConfirmar, new Insets(0, 0, 0, 100));
    }

    @Override
    public void chamarTelas(String acao) {

    }
}
