package sample;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TelaLogin implements TelaStrategy {
    private ChamadorTelas chamador;
    TextField txtUsuario = new TextField();
    TextField txtSenha = new TextField();
    Button btnLogin = new Button("Entrar");
    Button btnCadastro = new Button("Cadastrar-se");
    Label lblUsuario = new Label("Usuário");
    Label lblSenha = new Label("Senha");
    BorderPane bp = new BorderPane();


    TelaLogin(ChamadorTelas chamador){
        this.chamador = chamador;
    }

    @Override
    public Pane gerarTela() {
        GridPane telaLogin = new GridPane();
        telaLogin.setVgap(10);
        telaLogin.setHgap(5);
        txtUsuario.setPromptText("Digite seu usuário");
        txtSenha.setPromptText("Digite sua senha");

        telaLogin.add(lblUsuario,0,0);
        telaLogin.add(txtUsuario, 0, 1,2,1);
        telaLogin.add(lblSenha,0,2);
        telaLogin.add(txtSenha, 0, 3,2,1);
        telaLogin.add(btnLogin,0,4);
        telaLogin.add(btnCadastro,1,4);
        txtUsuario.setPromptText("Digite seu nome de usuário");
        txtSenha.setPromptText("Digite sua senha");
        adicionarMargens(telaLogin);
        bp.setCenter(telaLogin);

        btnCadastro.setOnAction((e)->{
            chamador.chamarTelas("Avançar");
        });
        btnLogin.setOnAction((e)->{
            chamador.chamarTelas("Entrar");
            //Código de autenticação de login
        });
/*
        Bindings.bindBidirectional(txtUsuario.textProperty(), control.LoginProperty());
        Bindings.bindBidirectional(txtSenha.textProperty(), control.senhaProperty());
 */
        return telaLogin;
    }

    private void adicionarMargens(GridPane telaLogin){
        telaLogin.setMargin(lblUsuario, new Insets(100,100,0,200));
        telaLogin.setMargin(txtUsuario, new Insets(0,100,0,200));
        telaLogin.setMargin(lblSenha, new Insets(0,100,0,200));
        telaLogin.setMargin(txtSenha, new Insets(0,100,0,200));
        telaLogin.setMargin(btnLogin, new Insets(0,100,0,200));
        telaLogin.setMargin(btnCadastro, new Insets(0,100,0,60));

    }
}

