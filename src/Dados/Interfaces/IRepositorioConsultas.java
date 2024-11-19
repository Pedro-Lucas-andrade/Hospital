package Dados.Interfaces;

import Negocio.ClassesBasicas.Consulta;
import Negocio.Execoes.ExcecoesConsulta.ConsultaJaExisteException;
import Negocio.Execoes.ExcecoesConsulta.ConsultaNaoExisteException;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract interface IRepositorioConsultas {
    boolean agendar(Consulta consulta) throws ConsultaJaExisteException;
    void cancelar(String id, LocalDate data, LocalTime hora) throws ConsultaNaoExisteException;
    Consulta buscar(String id, LocalDate data, LocalTime hora) throws ConsultaNaoExisteException;
    public Consulta remarcar(String id, LocalDate data, LocalTime hora, LocalDate novaData, LocalTime novaHora) throws ConsultaNaoExisteException, IllegalStateException;
}
