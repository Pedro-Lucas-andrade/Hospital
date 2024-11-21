package Dados.Auxiliares;

import Negocio.ClassesBasicas.Consulta;

import java.util.ArrayList;

public class Mes {
    private int dias;
    private int horas;
    private ListadeConsultas[][] diaPorHora;

    // Construtor que inicializa dias e horas e prepara cada posição com uma lista de consultas
    public Mes(int dias) {
        this.dias = dias;
        this.horas = 8;
        this.diaPorHora = new ListadeConsultas[horas][dias];

        // Inicializa cada posição da matriz com uma nova instância de ListadeConsultas
        for (int i = 0; i < horas; i++) {
            for (int j = 0; j < dias; j++) {
                diaPorHora[i][j] = new ListadeConsultas();
            }
        }
    }

    public boolean isIndiceValido(int hora, int dia) {
        return hora >= 0 && hora < horas && dia >= 0 && dia < dias;
    }

    public ListadeConsultas[][] getDiaPorHora() {
        return diaPorHora;
    }

    public int getDias() {
        return dias;
    }

    public int getHoras() {
        return horas;
    }
}
