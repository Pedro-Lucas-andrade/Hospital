package Dados.Repositorios;

import Dados.Interfaces.IRepositorioPessoas;
import Negocio.ClassesBasicas.PessoaAbstrata;

import java.util.ArrayList;

public class RepositorioMedicos_Pacientes implements IRepositorioPessoas {
    private ArrayList<PessoaAbstrata> arrayPessoas;

    public RepositorioMedicos_Pacientes() {
        arrayPessoas = new ArrayList<>();
    }

    @Override
    public boolean buscar(String cpf) {
        for (PessoaAbstrata pessoa : arrayPessoas) {
            if (cpf.equals(pessoa.getCpf())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<PessoaAbstrata> ler() {
        return arrayPessoas;
    }

    @Override
    public boolean adicionar(PessoaAbstrata pessoa) {
        return arrayPessoas.add(pessoa);
    }

    @Override
    public boolean remover(PessoaAbstrata pessoa) {
        return arrayPessoas.remove(pessoa);
    }

    @Override
    public boolean atualizar(String cpf, String nome) {
        for (PessoaAbstrata pessoa : arrayPessoas) {
            if (cpf.equals(pessoa.getCpf())) {
                pessoa.setNome(nome);
                return true;
            }
        }
        return false;
    }

    @Override
    public PessoaAbstrata retornaPessoa(String cpf) {
        for (PessoaAbstrata pessoa : arrayPessoas) {
            if (cpf.equals(pessoa.getCpf())) {
                return pessoa;
            }
        }
        return null; // Pessoa não encontrada
    }
}
