package Negocio;

import Dados.Interfaces.IRepositorioPessoas;
import Negocio.ClassesBasicas.PessoaAbstrata;
import Negocio.Execoes.ExcecoesPessoa.PessoaJaExisteException;
import Negocio.Execoes.ExcecoesPessoa.PessoaNaoExisteException;

import java.util.ArrayList;

public class NegocioPaciente {
    private final IRepositorioPessoas repositorioPaciente;

    public NegocioPaciente(IRepositorioPessoas repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public boolean buscar(String cpf) throws IllegalArgumentException{
        if (!Utilitaria.validarCpf(cpf)) {
            throw new IllegalArgumentException("O CPF digitado é inválido!");
        }
        return repositorioPaciente.buscar(cpf);
    }

    public ArrayList<PessoaAbstrata> ler(){
            return repositorioPaciente.ler();
    }

    public boolean adicionar(PessoaAbstrata pessoa) throws PessoaJaExisteException, IllegalArgumentException {
        if (pessoa == null || !Utilitaria.validarCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("A pessoa é nula, porfavor cadastre uma pessoa válida"); // Pessoa inválida ou CPF incorreto
        }
        if (repositorioPaciente.buscar(pessoa.getCpf())) {
            throw new PessoaJaExisteException(pessoa.getCpf());
        }
        return repositorioPaciente.adicionar(pessoa);
    }

    public boolean atualizar(String cpf, String nome) throws IllegalArgumentException, PessoaNaoExisteException {
        if (!Utilitaria.validarCpf(cpf)) {
            throw new IllegalArgumentException("O CPF digitado é inválido!");
        }
        if (!repositorioPaciente.buscar(cpf)) {
            throw new PessoaNaoExisteException(cpf);
        }
        return repositorioPaciente.atualizar(cpf, nome);
    }

    public boolean remover(String cpf) throws IllegalArgumentException, PessoaNaoExisteException{
        if (!Utilitaria.validarCpf(cpf)) {
            throw new IllegalArgumentException("O CPF é inválido!");
        }
        PessoaAbstrata pessoa = retornaPessoa(cpf);
        if (pessoa == null) {
            throw new PessoaNaoExisteException(cpf);
        }
        return repositorioPaciente.remover(pessoa);
    }

    public PessoaAbstrata retornaPessoa(String cpf) throws IllegalArgumentException, PessoaNaoExisteException{
        if (!Utilitaria.validarCpf(cpf)) {
            throw new IllegalArgumentException("O CPF é inválido!");
        }
        PessoaAbstrata pessoa = repositorioPaciente.retornaPessoa(cpf);
        if (pessoa == null) {
            throw new PessoaNaoExisteException(cpf);
        }

        return pessoa;
    }
}
