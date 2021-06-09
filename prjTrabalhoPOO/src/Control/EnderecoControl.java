package Control;

import DAO.EnderecoDao;
import DAO.Interface.IEnderecoDao;
import Model.EnderecoModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EnderecoControl {

    private IEnderecoDao enderecoDao = new EnderecoDao();

    private static IntegerProperty id = new SimpleIntegerProperty(0);
    private static StringProperty lougradouro = new SimpleStringProperty("");
    private static IntegerProperty num = new SimpleIntegerProperty(0);

    private static StringProperty complemento = new SimpleStringProperty("");
    private static StringProperty cep = new SimpleStringProperty("");
    private static StringProperty uf = new SimpleStringProperty("");

    public void setEntity(EnderecoModel e) {
        if (e != null) {
            id.set(e.getId());
            lougradouro.set(e.getLogradouro());
            num.set(e.getNum());
            complemento.set(e.getComplemento());
            cep.set(e.getCep());
            uf.set(e.getUf());
        }
    }

    public EnderecoModel getEntity() {
        EnderecoModel e = new EnderecoModel();
        e.setId(id.get());
        e.setLogradouro(lougradouro.get());
        e.setNum(num.get());
        e.setComplemento(complemento.get());
        e.setCep(cep.get());
        e.setUf(uf.get());
        return e;
    }

    public EnderecoModel adicionar(){
        EnderecoModel e = getEntity();
        enderecoDao.insertEndereco(e);
        setEntity(e);
        return  e;
    }

    public int getId() { return id.get(); }

    public  IntegerProperty idProperty() {return id; }

    public String getLougradouro() {
        return lougradouro.get();
    }

    public StringProperty lougradouroProperty() {
        return lougradouro;
    }

    public int getNum() {
        return num.get();
    }

    public IntegerProperty numProperty() {
        return num;
    }

    public String getComplemento() {
        return complemento.get();
    }

    public StringProperty complementoProperty() {
        return complemento;
    }

    public String getCep() {
        return cep.get();
    }

    public StringProperty cepProperty() {
        return cep;
    }

    public String getUf() {
        return uf.get();
    }

    public StringProperty ufProperty() {
        return uf;
    }
}
