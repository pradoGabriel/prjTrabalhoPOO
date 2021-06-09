package Control;

import DAO.Interface.IUsuarioDao;
import DAO.UsuarioDao;
import Model.EnderecoModel;
import Model.PagamentoModel;
import Model.PlanoModel;
import Model.UsuarioModel;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioControl {

    private ObservableList<UsuarioModel> usuarios = FXCollections.observableArrayList();

    private IUsuarioDao usuarioDao = new UsuarioDao();

    private static IntegerProperty id = new SimpleIntegerProperty(0);
    private static BooleanProperty isAdmin = new SimpleBooleanProperty(false);
    private static StringProperty nome = new SimpleStringProperty("");
    private static StringProperty senha = new SimpleStringProperty("");
    private static StringProperty cpf = new SimpleStringProperty("");


    private static ObjectProperty plano = new SimpleObjectProperty(new PlanoModel());

    private static ObjectProperty endereco = new SimpleObjectProperty();
    private static ObjectProperty pagamento = new SimpleObjectProperty();

    public void setEntity(UsuarioModel u) {
        if (u != null) {
            id.set(u.getId());
            isAdmin.set(u.isAdmin());
            nome.set(u.getNome());
            senha.set(u.getSenha());
            cpf.set(u.getCpf());
            plano.set(u.getPlano());
            endereco.set(u.getEndereco());
            pagamento.set(u.getPagamento());
        }
    }

    public UsuarioModel getEntity() {
        UsuarioModel u = new UsuarioModel();
        u.setId(id.get());
        u.setAdmin(isAdmin.get());
        u.setNome(nome.get());
        u.setSenha(senha.get());
        u.setCpf(cpf.get());
        u.setEndereco((EnderecoModel) endereco.get());
        u.setPagamento((PagamentoModel) pagamento.get());
        u.setPlano((PlanoModel) plano.get());
        return u;
    }

    public void login(){
        UsuarioModel usuario = usuarioDao.login(getNome(),getSenha());
        setEntity(usuario);
    }
    public void adicionar(){
        UsuarioModel u =  getEntity();
        usuarioDao.insertUsuario(u);
        setEntity(u);
        login();
    }


    public void setPlano(Object plano) {
        UsuarioControl.plano.set(plano);
    }

    public void setEndereco(Object endereco) {
        UsuarioControl.endereco.set(endereco);
    }

    public  void setPagamento(Object pagamento) {
        UsuarioControl.pagamento.set(pagamento);
    }

    public Object getPlano() { return plano.get(); }

    public ObjectProperty planoProperty() { return plano; }

    public int getId() { return id.get(); }

    public IntegerProperty idProperty() { return id; }

    public Object getEndereco() { return endereco.get(); }

    public ObjectProperty enderecoProperty() { return endereco; }

    public Object getPagamento() { return pagamento.get(); }

    public ObjectProperty pagamentoProperty() { return pagamento; }

    public String getNome() { return nome.get(); }

    public StringProperty nomeProperty() {  return nome; }

    public String getSenha() { return senha.get(); }

    public StringProperty senhaProperty() { return senha; }

    public String getCpf() { return cpf.get(); }

    public StringProperty cpfProperty() { return cpf; }

    public static boolean isIsAdmin() { return isAdmin.get(); }

    public static BooleanProperty isAdminProperty() { return isAdmin; }

}
