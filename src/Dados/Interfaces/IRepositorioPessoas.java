package Dados.Interfaces;

import Negocio.ClassesBasicas.PessoaAbstrata;

import java.util.ArrayList;

public interface IRepositorioPessoas {
    boolean adicionar(PessoaAbstrata pessoa);
    boolean remover(PessoaAbstrata pessoa);
    boolean atualizar(String cpf, String nome);
    boolean buscar(String cpf);
    PessoaAbstrata retornaPessoa(String cpf);
    ArrayList<PessoaAbstrata> ler();
}
