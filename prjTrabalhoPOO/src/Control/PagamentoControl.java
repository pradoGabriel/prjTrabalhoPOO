package Control;

import DAO.Interface.IPagamentoDao;
import DAO.PagamentoDao;
import Model.PagamentoModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PagamentoControl {
    private ObservableList<PagamentoModel> pagamentos = FXCollections.observableArrayList();

    private IPagamentoDao pagamentoDao = new PagamentoDao();

    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty nomeTitular = new SimpleStringProperty("");
    private StringProperty cpfTitular = new SimpleStringProperty("");
    private StringProperty numCartao = new SimpleStringProperty("");
    private StringProperty cvv = new SimpleStringProperty("");
    private StringProperty validadeCartao = new SimpleStringProperty("");



    public void setEntity(PagamentoModel p) {
        if (p != null) {
            nomeTitular.set(p.getNomeTitular());
            id.set(p.getId());
            cpfTitular.set(p.getCpfTitular());
            numCartao.set(p.getNumCartao());
            cvv.set(p.getCvv());
            validadeCartao.set(p.getValidadeCartao());
        }
    }

    public PagamentoModel getEntity() {
        PagamentoModel p = new PagamentoModel();
        p.setNomeTitular(nomeTitular.get());
        p.setId(id.get());
        p.setCpfTitular(cpfTitular.get());
        p.setNumCartao(numCartao.get());
        p.setCvv(cvv.get());
        p.setValidadeCartao(validadeCartao.get());
        return p;
    }

    public void adicionar(){
        PagamentoModel p = getEntity();
        pagamentoDao.insertPagamento(p);
    }

    public String getNomeTitular() {
        return nomeTitular.get();
    }

    public StringProperty nomeTitularProperty() {
        return nomeTitular;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getCpfTitular() {
        return cpfTitular.get();
    }

    public StringProperty cpfTitularProperty() {
        return cpfTitular;
    }

    public String getNumCartao() {
        return numCartao.get();
    }

    public StringProperty numCartaoProperty() {
        return numCartao;
    }

    public String getCvv() {
        return cvv.get();
    }

    public StringProperty cvvProperty() {
        return cvv;
    }

    public String getValidadeCartao() {
        return validadeCartao.get();
    }

    public StringProperty validadeCartaoProperty() {
        return validadeCartao;
    }
}
