package Model;

public class ReservaModel {
    private int id;
    private DependenciaModel dependencia;
    private UsuarioModel usuario;
    private String dataReserva;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DependenciaModel getDependencia() {
        return dependencia;
    }

    public void setDependencia(DependenciaModel dependencia) {
        this.dependencia = dependencia;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }
}
