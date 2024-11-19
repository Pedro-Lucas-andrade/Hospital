package Dados.Repositorios;

import Dados.Auxiliares.Ano;
import Dados.Auxiliares.ListadeConsultas;
import Dados.Auxiliares.Mes;
import Dados.Interfaces.IRepositorioConsultas;
import Negocio.ClassesBasicas.Consulta;
import Negocio.Execoes.ExcecoesConsulta.ConsultaJaExisteException;
import Negocio.Execoes.ExcecoesConsulta.ConsultaNaoExisteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioConsultas implements IRepositorioConsultas {
    public Ano ano;
    public Mes[] meses;

    public RepositorioConsultas(){
        this.ano = new Ano(2025);
        this.meses = ano.getArrayMeses();
    }


    @Override
    public Consulta buscar(String id, LocalDate data, LocalTime hora) throws ConsultaNaoExisteException {
        int mesDaConsulta = data.getMonthValue() - 1; // Ajuste para índice de array (0 a 11)
        int diaDaConsulta = data.getDayOfMonth() - 1; // Ajuste para índice (0 a 30/29)
        int horaDaConsulta = hora.getHour() - 8;

        Mes mes = meses[mesDaConsulta];

        for (Consulta c : mes.getDiaPorHora()[horaDaConsulta][diaDaConsulta].getConsultas()) {
            if (id.equals(c.getId()))
                return c;
        }

        throw new ConsultaNaoExisteException(id);
    }

    @Override
    public boolean agendar(Consulta consulta) throws ConsultaJaExisteException {
        int mesDaConsulta = consulta.getData().getMonthValue() - 1; // Ajuste para índice de array (0 a 11)
        int diaDaConsulta = consulta.getData().getDayOfMonth() - 1; // Ajuste para índice (0 a 30/29)
        int horaDaConsulta = consulta.getHora().getHour() - 8; // Ajuste para o indice (0 a 8)

        Mes mes = meses[mesDaConsulta];

        for(Consulta c: mes.getDiaPorHora()[horaDaConsulta][diaDaConsulta].getConsultas()){
            if(c.equals(consulta)){
                throw new ConsultaJaExisteException(consulta.getId());
            }
        }

        return mes.getDiaPorHora()[horaDaConsulta][diaDaConsulta].adicionarConsulta(consulta);
    }


   @Override
    public void cancelar(String id, LocalDate data, LocalTime hora) throws ConsultaNaoExisteException{
        Consulta consulta = buscar(id, data, hora);
        int mesDaConsulta = consulta.getData().getMonthValue() - 1;
        int diaDaConsulta = consulta.getData().getDayOfMonth() - 1;
        int horaDaConsulta = consulta.getHora().getHour() - 8;

        Mes mes = meses[mesDaConsulta];

        // Verificação de limites
       ListadeConsultas lista = mes.getDiaPorHora()[horaDaConsulta][diaDaConsulta];
       lista.getConsultas().remove(consulta); // Remover a consulta específica
    }

    public Consulta remarcar(String id, LocalDate data, LocalTime hora, LocalDate novaData, LocalTime novaHora) throws ConsultaNaoExisteException, IllegalStateException{
        Consulta consulta = buscar(id, data, hora);
        consulta.setData(novaData);
        consulta.setHora(novaHora);
        int mesDaConsulta = consulta.getData().getMonthValue() - 1; // Ajuste para índice de array (0 a 11)
        int diaDaConsulta = consulta.getData().getDayOfMonth() - 1; // Ajuste para índice (0 a 30/29)
        int horaDaConsulta = consulta.getHora().getHour() - 8; // Ajuste para o indice (0 a 8)

        Mes mes = meses[mesDaConsulta];

        for(Consulta c: mes.getDiaPorHora()[horaDaConsulta][diaDaConsulta].getConsultas()){
            if(c.getMedico() == consulta.getMedico())
                throw new IllegalStateException("O médico: "+ consulta.getMedico()+" não está disponível para consultas na data " + data + " e hora " + hora + ".");
        }

        return consulta;
    }


    public List<Consulta> buscarPorDataHora(LocalDate data, LocalTime hora) {
        int mesDaConsulta = data.getMonthValue() - 1; // Ajusta para índice de array (0 a 11)
        int diaDaConsulta = data.getDayOfMonth() - 1; // Ajuste para índice (0 a 30/29)
        int horaDaConsulta = hora.getHour() - 8;

        Mes mes = meses[mesDaConsulta];

        // Verificação de limites
        if (horaDaConsulta >= 8 && horaDaConsulta < 16 && diaDaConsulta >= 0 && diaDaConsulta < mes.getDias()) {
            ListadeConsultas lista = mes.getDiaPorHora()[horaDaConsulta][diaDaConsulta];
            return new ArrayList<>(lista.getConsultas());
        } else {
            System.out.println("Erro: Hora ou dia da consulta está fora dos limites.");
            return new ArrayList<>();
        }
    }


    public List<Consulta> buscarPorPaciente(String cpfPaciente) {
        List<Consulta> consultasPaciente = new ArrayList<>();
        for (Mes mes : meses) {
            for (int i = 0; i < mes.getHoras(); i++) {
                for (int j = 0; j < mes.getDias(); j++) {
                    for (Consulta consulta : mes.getDiaPorHora()[i][j].getConsultas()) {
                        if (consulta.getPaciente().getCpf().equals(cpfPaciente)) {
                            consultasPaciente.add(consulta);
                        }
                    }
                }
            }
        }
        return consultasPaciente;
    }

    public List<Consulta> buscarPorMedico(String crmMedico) {
        List<Consulta> consultasMedico = new ArrayList<>();
        for (Mes mes : meses) {
            for (int i = 0; i < mes.getHoras(); i++) {
                for (int j = 0; j < mes.getDias(); j++) {
                    for (Consulta consulta : mes.getDiaPorHora()[i][j].getConsultas()) {
                        if (consulta.getMedico().getCRM().equals(crmMedico)) {
                            consultasMedico.add(consulta);
                        }
                    }
                }
            }
        }
        return consultasMedico;
    }
}
