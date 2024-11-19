package Negocio.ClassesBasicas;

import Negocio.Constantes.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Consulta {
    private Medico medico;
    private Paciente paciente;
    private LocalDate data;
    private LocalTime hora;
    private Status status;
    private String id;

    public Consulta(Medico medico, Paciente paciente, LocalDate data, LocalTime hora){
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
        this.hora = hora;
        this.status = Status.NAO_AGENDADA;
        this.id = medico.getCRM() + paciente.getCpf();
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Status getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString(){
        return "Consulta [Paciente: " + this.getPaciente() + "Medico: " + this.getMedico() + "Data: " + this.getData() + "Hora: " + this.getHora() + "Status: " + this.getStatus() + "]";
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Consulta){
            Consulta consulta2 = (Consulta) obj;
            if(this.id.equals(consulta2.getId()))
                return true;
        }
        return false;
    }
}
