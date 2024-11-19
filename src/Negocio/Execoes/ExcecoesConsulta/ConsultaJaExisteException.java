package Negocio.Execoes.ExcecoesConsulta;

public class ConsultaJaExisteException extends Exception{
    private String id;

    public ConsultaJaExisteException(String id){
        super("Não foi possível agendar a consulta!\nA consulta já existe no sistema!");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
