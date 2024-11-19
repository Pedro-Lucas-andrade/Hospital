package Dados.Auxiliares;

import Negocio.ClassesBasicas.Consulta;
import Negocio.Constantes.Status;

import java.util.ArrayList;

public class ListadeConsultas {
    ArrayList<Consulta> consultas = new ArrayList<>();

    // Construtor sem argumentos
    public ListadeConsultas() {
        // inicializa a lista vazia
    }

    // Construtor com argumento (opcional, caso você precise adicionar uma consulta imediatamente)
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
