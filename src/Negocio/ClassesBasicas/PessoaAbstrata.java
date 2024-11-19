package Negocio.ClassesBasicas;

import Negocio.Constantes.Sexo;

public abstract class PessoaAbstrata {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private Sexo sexo;

    public PessoaAbstrata(String nome, String cpf, String dataNascimento, Sexo sexo){
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
