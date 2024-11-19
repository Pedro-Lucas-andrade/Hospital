package Negocio.Execoes.ExcecoesConsulta;

public class ConsultaNaoExisteException extends Exception {
    private String id;

    public ConsultaNaoExisteException(String id) {
        super("Consulta inexistente!\nNão existe uma consulta agendada com o id:" + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
