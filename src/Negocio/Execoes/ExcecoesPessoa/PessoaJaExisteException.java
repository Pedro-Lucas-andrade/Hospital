package Negocio.Execoes.ExcecoesPessoa;

public class PessoaJaExisteException extends Exception{
    private String cpf;

    public PessoaJaExisteException(String cpf){
        super("Não foi possível adicionar!\nEssa pessoa já existe no sistema!");
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
