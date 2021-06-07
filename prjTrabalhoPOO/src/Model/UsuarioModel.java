package Model;

public class UsuarioModel {
    private int id;
    private String nome;
    private String senha;
    private String cpf;
    private boolean isAdmin;
    private PlanoModel plano;
    private EnderecoModel endereco;
    private PagamentoModel pagamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public PlanoModel getPlano() {
        return plano;
    }

    public void setPlano(PlanoModel plano) {
        this.plano = plano;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public PagamentoModel getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoModel pagamento) {
        this.pagamento = pagamento;
    }
}
