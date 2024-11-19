package IU;

import Negocio.NegocioConsultas;
import Negocio.NegocioPaciente;
import Negocio.ClassesBasicas.Consulta;
import Negocio.ClassesBasicas.Medico;
import Negocio.ClassesBasicas.Paciente;
import Negocio.Execoes.ExcecoesConsulta.ConsultaJaExisteException;
import Negocio.Execoes.ExcecoesConsulta.ConsultaNaoExisteException;
import Negocio.Execoes.ExcecoesPessoa.PessoaJaExisteException;
import Negocio.Execoes.ExcecoesPessoa.PessoaNaoExisteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Fachada {
    private final NegocioPaciente negocioPaciente;
    private final NegocioConsultas negocioConsultas;

    public Fachada(NegocioPaciente negocioPaciente, NegocioConsultas negocioConsultas) {
        this.negocioPaciente = negocioPaciente;
        this.negocioConsultas = negocioConsultas;
    }

    // Operações de Paciente
    public void cadastrarPaciente(Paciente paciente) throws PessoaJaExisteException {
        negocioPaciente.adicionar(paciente);
    }

    public Paciente buscarPaciente(String cpf) throws PessoaNaoExisteException {
        return (Paciente) negocioPaciente.retornaPessoa(cpf);
    }

    public void removerPaciente(String cpf) throws PessoaNaoExisteException {
        negocioPaciente.remover(cpf);
    }

    public ArrayList<Paciente> listarPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        for (var pessoa : negocioPaciente.ler()) {
            if (pessoa instanceof Paciente) {
                pacientes.add((Paciente) pessoa);
            }
        }
        return pacientes;
    }

    // Operações de Médico
    public void cadastrarMedico(Medico medico) throws PessoaJaExisteException {
        negocioPaciente.adicionar(medico);
    }

    public Medico buscarMedico(String cpf) throws PessoaNaoExisteException {
        return (Medico) negocioPaciente.retornaPessoa(cpf);
    }

    public List<Medico> listarMedicos() {
        List<Medico> medicos = new ArrayList<>();
        for (var pessoa : negocioPaciente.ler()) {
            if (pessoa instanceof Medico) {
                medicos.add((Medico) pessoa);
            }
        }
        return medicos;
    }

    // Operações de Consultas
    public void agendarConsulta(Consulta consulta) throws ConsultaJaExisteException {
        negocioConsultas.agendarConsulta(consulta);
    }

    public Consulta buscarConsulta(String id, LocalDate data, LocalTime hora) throws ConsultaNaoExisteException {
        return negocioConsultas.buscarConsulta(id, data, hora);
    }

    public void remarcarConsulta(String id, LocalDate data, LocalTime hora, LocalDate novaData, LocalTime novaHora) throws ConsultaNaoExisteException, IllegalArgumentException, ConsultaJaExisteException {
        remarcarConsulta(id, data, hora, novaData, novaHora);
    }
        public void cancelarConsulta(String id, LocalDate data, LocalTime hora) throws ConsultaNaoExisteException {
        negocioConsultas.cancelarConsulta(id, data, hora);
    }

    public List<Consulta> listarConsultasPorMedico(String crmMedico) throws ConsultaNaoExisteException {
        return negocioConsultas.buscarConsultasPorMedico(crmMedico);
    }

    public List<Consulta> listarConsultasPorPaciente(String cpfPaciente) throws ConsultaNaoExisteException {
        return negocioConsultas.buscarConsultasPorPaciente(cpfPaciente);
    }
}

