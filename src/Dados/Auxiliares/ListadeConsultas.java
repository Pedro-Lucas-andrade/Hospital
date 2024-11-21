package Dados.Auxiliares;

import Negocio.ClassesBasicas.Consulta;
import Negocio.Constantes.Status;

import java.util.ArrayList;

public class ListadeConsultas {
    ArrayList<Consulta> consultas = new ArrayList<>();


    public ListadeConsultas() {
    }

    public ListadeConsultas(Consulta consulta) {
        this.consultas.add(consulta);
    }

    public ArrayList<Consulta> getConsultas() {
        return this.consultas;
    }

    public boolean adicionarConsulta(Consulta consulta) {
        consulta.setStatus(Status.AGENDADA);
        return this.consultas.add(consulta);
    }
}
