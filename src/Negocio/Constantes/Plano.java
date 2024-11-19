package Negocio.Constantes;

public enum Plano {
    SUS("SUS"),
    PARTICULAR("Particualar"),
    PRATA("Prata"),
    GOLD("Gold"),
    PLATINUM("Platinum");

    private final String nome;

    Plano(String nome) {
        this.nome = nome;
    }
}
