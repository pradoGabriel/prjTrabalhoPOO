

import Control.PlanoControl;
import Control.UsuarioControl;
import Model.UsuarioModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TelaMeuPlano implements TelaStrategy {
    private Label lblNome = new Label("Nome");
    private Label lblCpf = new Label("CPF");
    private Label lblPlano = new Label ("Plano");
    private Label lblDesc = new Label ("Detalhes do Plano");

    private TextField txtNome = new TextField();
    private TextField txtCpf = new TextField();
    private TextField txtPlano = new TextField();
    private TextArea txtDesc = new TextArea();

    private UsuarioControl usuarioControl = new UsuarioControl();
    private PlanoControl planoControl = new PlanoControl();

    @Override
    public Pane gerarTela() {
        GridPane telaMeuPlano = new GridPane();
        telaMeuPlano.setHgap(5);
        telaMeuPlano.setVgap(20);
        txtDesc.setMinSize(150,100);
        txtNome.setEditable(false);
        txtDesc.setEditable(false);
        txtCpf.setEditable(false);
        txtPlano.setEditable(false);
        telaMeuPlano.add(lblNome,0,1);
        telaMeuPlano.add(txtNome,1,1);
        telaMeuPlano.add(lblCpf,2,1);
        telaMeuPlano.add(txtCpf,3,1);
        telaMeuPlano.add(lblPlano,0,2);
        telaMeuPlano.add(txtDesc,1,2,3,1);
        adicionarMargens(telaMeuPlano);

        Bindings.bindBidirectional(txtNome.textProperty(), usuarioControl.nomeProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(), usuarioControl.cpfProperty());
        Bindings.bindBidirectional(txtPlano.textProperty(), planoControl.nomeProperty());
        Bindings.bindBidirectional(txtDesc.textProperty(), planoControl.descricaoProperty());

        System.out.println(usuarioControl.getId());
        UsuarioModel u = usuarioControl.getEntity();
        planoControl.planoByUsuario(u.getPlano().getId());
        return telaMeuPlano;
    }

    public void adicionarMargens(GridPane telaMeuPlano){
        telaMeuPlano.setMargin(lblNome, new Insets(0,0,0,100));
        telaMeuPlano.setMargin(lblPlano, new Insets(0,0,0,100));
        telaMeuPlano.setMargin(lblDesc, new Insets(0,0,0,100));
    }
}
