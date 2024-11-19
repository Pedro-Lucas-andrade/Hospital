package Negocio.Execoes.ExcecoesPessoa;

public class PessoaNaoExisteException extends Exception{
    private String cpf;

    public PessoaNaoExisteException(String cpf){
        super("A Pessoa com o CPF " + cpf + " não existe no sistema!");
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
