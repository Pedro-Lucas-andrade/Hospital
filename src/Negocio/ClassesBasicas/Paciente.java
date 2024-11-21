package Negocio.ClassesBasicas;

import Negocio.Constantes.Plano;
import Negocio.Constantes.Sexo;

public class Paciente extends PessoaAbstrata {
    private Plano plano;

    public Paciente(String nome, String cpf, String dataDeNascimento, Sexo sexo, Plano plano) {
        super(nome, cpf, dataDeNascimento, sexo);
        this.plano = plano;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    @Override
    public String toString() {
        return "\nPaciente{" +
                "nome='" + this.getNome() + '\'' +
                ", cpf='" + this.getCpf() + '\'' +
                ", dataDeNascimento='" + this.getDataNascimento() + '\'' +
                ", sexo=" + this.getSexo() +
                ", plano=" + this.plano + "}\n";
    }
}
