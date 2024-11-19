package Negocio.ClassesBasicas;

import Negocio.Constantes.Especialidade;
import Negocio.Constantes.Sexo;

public class Medico extends PessoaAbstrata {
    private Especialidade especialidade;
    private final String CRM;

    public Medico(String nome, String cpf, String dataDeNascimento, Sexo sexo, String CRM, Especialidade especialidade) {
        super(nome, cpf, dataDeNascimento, sexo);
        this.CRM = CRM;
        this.especialidade = especialidade;
    }

    public String getCRM() {
        return CRM;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    @Override
    public String toString(){
        return "\nMédico {nome='" + this.getNome() + '\'' +
                ", cpf='" + this.getCpf() + '\'' +
                ", dataDeNascimento='" + this.getDataNascimento() + '\'' +
                ", sexo=" + this.getSexo() +
                ", especialidade=" + this.especialidade +
                ", crm=" + this.CRM + "}\n";
    }

}