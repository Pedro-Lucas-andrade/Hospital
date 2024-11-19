package Negocio;

import Dados.Interfaces.IRepositorioConsultas;
import Dados.Repositorios.RepositorioConsultas;
import Negocio.ClassesBasicas.Consulta;
import Negocio.Execoes.ExcecoesConsulta.ConsultaJaExisteException;
import Negocio.Execoes.ExcecoesConsulta.ConsultaNaoExisteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class NegocioConsultas {
    RepositorioConsultas repositorioConsultas;

    public NegocioConsultas(IRepositorioConsultas repositorioConsultas) {
        this.repositorioConsultas = new RepositorioConsultas();
    }

    public void agendarConsulta(Consulta consulta) throws IllegalArgumentException, ConsultaJaExisteException {
        if (consulta == null)
            throw new IllegalArgumentException("Consulta inválida! Certifique-se de preencher todos os dados corretamente.");
        if(consulta.getMedico() == null)
            throw new IllegalArgumentException("Não foi indicado nenhum médico para a consulta!");
        if(consulta.getPaciente() == null)
            throw new IllegalArgumentException("Não foi indicado nenhum paciente para a consulta!");

        LocalTime hora = consulta.getHora();

        // Verificar se a hora está dentro do intervalo permitido
        if (hora.getHour() < 8 || hora.getHour() >= 16) {
            throw new IllegalArgumentException("Horário inválido! Escolha um horário entre 8h e 15h.");
        }

        repositorioConsultas.agendar(consulta);
    }

    public Consulta buscarConsulta(String id, LocalDate data, LocalTime hora) throws ConsultaNaoExisteException {
        if(hora.getHour() < 8 || hora.getHour() >= 16)
            throw new IllegalArgumentException("Hora inválida! Não realizamos consultas as" + hora + "h");
        if(id.isEmpty())
            throw new IllegalArgumentException("Id inválido! É necessário informar um id");
        return repositorioConsultas.buscar(id, data, hora);
    }

    public void cancelarConsulta(String id, LocalDate data, LocalTime hora) throws IllegalArgumentException, ConsultaNaoExisteException{
        if (hora.getHour() < 8 || hora.getHour() >= 16)
            throw new IllegalArgumentException("Horário inválido! Escolha um horário entre 8h e 15h.");
        if(id.isEmpty())
            throw new IllegalArgumentException("Id inválido! É necessário informar um id");

        repositorioConsultas.cancelar(id, data, hora);
    }

    public void remarcarConsulta(String id, LocalDate data, LocalTime hora, LocalDate novaData, LocalTime novaHora) throws ConsultaNaoExisteException, IllegalArgumentException, ConsultaJaExisteException{
        if(id.isEmpty())
            throw new IllegalArgumentException("Id inválido! É necessário informar um id");
        if (hora.getHour() < 8 || hora.getHour() >= 16)
            throw new IllegalArgumentException("Horário inválido! Escolha um horário entre 8h e 15h.");
        if( novaData == null)
            throw new IllegalArgumentException("A nova data escolhida");
        if (novaHora.getHour() < 8 || novaHora.getHour() >= 16)
            throw new IllegalArgumentException("Horário inválido! Escolha um horário entre 8h e 15h.");
        Consulta consulta = repositorioConsultas.remarcar(id, data, hora, novaData, novaHora);
        if(consulta != null) {
            repositorioConsultas.cancelar(consulta.getId(), consulta.getData(), consulta.getHora());
            repositorioConsultas.agendar(consulta);
        }
    }

    public List<Consulta> buscarConsultasPorMedico(String crmMedico) throws ConsultaNaoExisteException {
        List<Consulta> consultas = repositorioConsultas.buscarPorMedico(crmMedico);
        if (consultas.isEmpty()) {
            throw new ConsultaNaoExisteException("Não foram encontradas consultas para o médico com CRM: " + crmMedico);
        }
        return consultas;
    }

    public List<Consulta> buscarConsultasPorPaciente(String cpfPaciente) throws ConsultaNaoExisteException {
        List<Consulta> consultas = repositorioConsultas.buscarPorPaciente(cpfPaciente);
        if (consultas.isEmpty()) {
            throw new ConsultaNaoExisteException("Não foram encontradas consultas para o paciente com CPF: " + cpfPaciente);
        }
        return consultas;
    }

    public List<Consulta> buscarConsultasPorDataHora(LocalDate data, LocalTime hora) throws ConsultaNaoExisteException {
        List<Consulta> consultas = repositorioConsultas.buscarPorDataHora(data, hora);
        if (consultas.isEmpty()) {
            throw new ConsultaNaoExisteException(
                    "Não foram encontradas consultas na data " + data + " às " + hora);
        }
        return consultas;
    }

}
