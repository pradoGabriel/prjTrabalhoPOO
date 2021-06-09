

import Control.PlanoControl;
import Control.UsuarioControl;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class TelaPrincipal extends Application implements ChamadorTelas {

    private TelaStrategy telaLogin = new TelaLogin(this);
    private TelaStrategy telaReserva = new TelaReserva();
    private TelaStrategy telaMeuPlano = new TelaMeuPlano();
    private TelaStrategy telaCadastro = new TelaCadastro(this);
    private TelaStrategy telaPagto = new TelaPagamento(this);
    private TelaStrategy telaNovoPlano = new TelaNovoPlano();

    Label lblTitulo = new Label("Login");
    Button btnLogin = new Button("Login");
    Button btnVerPlanos = new Button("Planos");
    Button btnSair = new Button("Sair");
    Button btnMeuPlano = new Button("Meu plano");
    Button btnReserva = new Button("Reservas");
    BorderPane bp = new BorderPane();

    private UsuarioControl usuarioControl = new UsuarioControl();

    @Override
    public void start(Stage stage) throws Exception {
        btnSair.setDisable(true);
        btnReserva.setDisable(true);
        btnVerPlanos.setDisable(true);
        btnMeuPlano.setDisable(true);
        FlowPane fp = new FlowPane();
        fp.setAlignment(Pos.CENTER);
        fp.getChildren().add(lblTitulo);
        lblTitulo.setAlignment(Pos.CENTER);
        TilePane tp = new TilePane();
        Scene scn = new Scene(bp, 800, 500);
        lblTitulo.setPrefSize(300, 50);
        lblTitulo.setFont(new Font(25.0));
        tp.setVgap(20);
        bp.setTop(fp);
        bp.setLeft(tp);
        tp.setPrefColumns(1);
        tp.getChildren().addAll(btnMeuPlano, btnReserva, btnLogin, btnSair, btnVerPlanos);
        bp.setCenter(telaLogin.gerarTela());

        btnReserva.setOnAction((e) -> {
            lblTitulo.setText("Reserva");
            bp.setCenter(telaReserva.gerarTela());
        });

        btnMeuPlano.setOnAction((e) -> {
            lblTitulo.setText("Meu Plano");
            bp.setCenter(telaMeuPlano.gerarTela());
        });

        btnSair.setOnAction((e) -> {
            lblTitulo.setText("Login");
            bp.setCenter(telaLogin.gerarTela());
            setBotoesDeslogado();
        });

        btnLogin.setOnAction((e) -> {
            lblTitulo.setText("Login");
            bp.setCenter(telaLogin.gerarTela());
        });

        btnVerPlanos.setOnAction((e) -> {
            lblTitulo.setText("Consulta de Planos");
            bp.setCenter(telaNovoPlano.gerarTela());
        });

        stage.setScene(scn);
        stage.setTitle("Sistema de Clube");
        stage.show();
    }

    public void chamarTelas(String acao) {
        if (acao.equals("Avançar")) {
            lblTitulo.setText("Cadastro");
            bp.setCenter(telaCadastro.gerarTela());
        } else if (acao.equals("Pagamento")) {
            lblTitulo.setText("Pagamento");
            bp.setCenter(telaPagto.gerarTela());
        } else if (acao.equals("Entrar")) {
            if(usuarioControl.getEntity().isAdmin()){
                setBotoesLogadoAdmin();
                lblTitulo.setText("Planos");
                bp.setCenter(telaNovoPlano.gerarTela());
            }
            else{
                lblTitulo.setText("Meu Plano");
                setBotoesLogado();
                bp.setCenter(telaMeuPlano.gerarTela());
            }

        } else if (acao.equals("Confirmar")) {
            lblTitulo.setText("Meu Plano");
            setBotoesLogado();
            bp.setCenter(telaMeuPlano.gerarTela());
        }
    }

    public void setBotoesLogadoAdmin() {
        btnSair.setDisable(false);
        btnVerPlanos.setDisable(false);
    }
    public void setBotoesLogado() {
        btnSair.setDisable(false);
        btnMeuPlano.setDisable(false);
        btnReserva.setDisable(false);
        btnLogin.setDisable(true);
    }

    public void setBotoesDeslogado(){
        btnMeuPlano.setDisable(true);
        btnReserva.setDisable(true);
        btnSair.setDisable(true);
        btnVerPlanos.setDisable(true);
        btnLogin.setDisable(false);
    }


    public static void main (String[] args){
        Application.launch(TelaPrincipal.class, args);
    }
}
