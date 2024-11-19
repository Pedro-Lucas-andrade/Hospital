package Negocio.Constantes;

public enum Status {
    AGENDADA("Agendada"),
    NAO_AGENDADA("Não Agendada"),
    REALIZADA("Realizada");

    private final String nome;
    Status(String nome){
        this.nome = nome;
    }
}
