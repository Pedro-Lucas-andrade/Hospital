package IU;

import Dados.Repositorios.RepositorioConsultas;
import Dados.Repositorios.RepositorioMedicos_Pacientes;
import Negocio.ClassesBasicas.Medico;
import Negocio.ClassesBasicas.Paciente;
import Negocio.ClassesBasicas.Consulta;
import Negocio.Constantes.Sexo;
import Negocio.Constantes.Plano;
import Negocio.Constantes.Especialidade;
import Negocio.Execoes.ExcecoesConsulta.ConsultaJaExisteException;
import Negocio.Execoes.ExcecoesConsulta.ConsultaNaoExisteException;
import Negocio.Execoes.ExcecoesPessoa.PessoaNaoExisteException;
import Negocio.NegocioConsultas;
import Negocio.NegocioPaciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Sistema {
    private static Fachada fachada;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarSistema();

        while (true) {
            System.out.println("\n=== Sistema de Gestão Médica ===");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Cadastrar Médico");
            System.out.println("3. Agendar Consulta");
            System.out.println("4. Listar Pacientes");
            System.out.println("5. Listar Médicos");
            System.out.println("6. Buscar Consulta");
            System.out.println("7. Cancelar Consulta");
            System.out.println("8. Remarcar Consulta");
            System.out.println("9. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a linha restante

            switch (opcao) {
                case 1 -> cadastrarPaciente(scanner);
                case 2 -> cadastrarMedico(scanner);
                case 3 -> agendarConsulta(scanner);
                case 4 -> listarPacientes();
                case 5 -> listarMedicos();
                case 6 -> buscarConsulta(scanner);
                case 7 -> cancelarConsulta(scanner);
                case 8 -> remarcarConsulta(scanner);
                case 9 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void inicializarSistema() {
        fachada = new Fachada(new NegocioPaciente(new RepositorioMedicos_Pacientes()), new NegocioConsultas(new RepositorioConsultas()));
    }

    private static void cadastrarPaciente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Sexo (MASCULINO/FEMININO): ");
        Sexo sexo = Sexo.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Plano (SUS/PRATA/GOLD): ");
        Plano plano = Plano.valueOf(scanner.nextLine().toUpperCase());

        Paciente paciente = new Paciente(nome, cpf, dataNascimento, sexo, plano);
        try {
            fachada.cadastrarPaciente(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    private static void cadastrarMedico(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Sexo (MASCULINO/FEMININO): ");
        Sexo sexo = Sexo.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("CRM: ");
        String crm = scanner.nextLine();
        System.out.print("Especialidade: ");
        Especialidade especialidade = Especialidade.valueOf(scanner.nextLine().toUpperCase());

        Medico medico = new Medico(nome, cpf, dataNascimento, sexo, crm, especialidade);
        try {
            fachada.cadastrarMedico(medico);
            System.out.println("Médico cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar médico: " + e.getMessage());
        }
    }

    public static void agendarConsulta(Scanner scanner) {
        try {
            System.out.println("\nDigite os dados para agendar a consulta:");

            // Receber dados do médico
            System.out.print("CPF do Médico: ");
            String cpfMedico = scanner.nextLine();

            System.out.print("CPF do Paciente: ");
            String cpfPaciente = scanner.nextLine();

            Medico medico = fachada.buscarMedico(cpfMedico);
            Paciente paciente = fachada.buscarPaciente(cpfPaciente);

            if (medico != null && paciente != null) {
                // Receber dados da consulta
                System.out.print("Data da consulta (yyyy-mm-dd): ");
                String dataConsultaString = scanner.nextLine();
                LocalDate dataConsulta = LocalDate.parse(dataConsultaString);

                System.out.print("Hora da consulta (HH:mm): ");
                String horaConsultaString = scanner.nextLine();
                LocalTime horaConsulta = LocalTime.parse(horaConsultaString);

                // Criar e agendar a consulta
                Consulta consulta = new Consulta(medico, paciente, dataConsulta, horaConsulta);
                fachada.agendarConsulta(consulta);
                System.out.println("Consulta agendada com sucesso!");
            } else {
                System.out.println("Médico ou Paciente não encontrado.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao converter data ou hora. Verifique o formato (yyyy-mm-dd para a data e HH:mm para a hora).");
        } catch (ConsultaJaExisteException e) {
            System.out.println("Erro: Já existe uma consulta agendada para este médico e paciente no mesmo horário.");
        } catch (Exception e) {
            System.out.println("Erro ao agendar consulta: " + e.getMessage());
        }
    }

    private static void listarPacientes() {
        fachada.listarPacientes().forEach(System.out::println);
    }

    private static void listarMedicos() {
        fachada.listarMedicos().forEach(System.out::println);
    }

    private static void buscarConsulta(Scanner scanner) {
        try {
            System.out.print("Informe id da consulta: ");
            String id = scanner.nextLine();
            System.out.print("Informe a data da consulta (yyyy-mm-dd): ");
            String dataConsulta = scanner.nextLine();
            System.out.print("Informe a hora da consulta (HH:mm): ");
            String horaConsulta = scanner.nextLine();

            Consulta consulta = fachada.buscarConsulta(id, LocalDate.parse(dataConsulta), LocalTime.parse(horaConsulta));
            System.out.println(consulta);
        } catch (ConsultaNaoExisteException e) {
            System.out.println("Consulta não encontrada: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao converter data ou hora.");
        }
    }

    private static void cancelarConsulta(Scanner scanner) {
        try {
            System.out.print("Informe o ID da consulta para cancelar: ");
            String idConsulta = scanner.nextLine();
            System.out.print("Informe a data da consulta (yyyy-mm-dd): ");
            String dataConsulta = scanner.nextLine();
            System.out.print("Informe a hora da consulta (HH:mm): ");
            String horaConsulta = scanner.nextLine();

            fachada.cancelarConsulta(idConsulta, LocalDate.parse(dataConsulta), LocalTime.parse(horaConsulta));
            System.out.println("Consulta cancelada com sucesso!");
        } catch (ConsultaNaoExisteException e) {
            System.out.println("Consulta não encontrada: " + e.getMessage());
        }
    }

    private static void remarcarConsulta(Scanner scanner) {
        try {
            System.out.print("Informe o ID da consulta para remarcar: ");
            String idConsulta = scanner.nextLine();
            System.out.print("Informe a data original da consulta (yyyy-mm-dd): ");
            String dataConsulta = scanner.nextLine();
            System.out.print("Informe a hora original da consulta (HH:mm): ");
            String horaConsulta = scanner.nextLine();

            System.out.print("Informe a nova data (yyyy-mm-dd): ");
            String novaData = scanner.nextLine();
            System.out.print("Informe a nova hora (HH:mm): ");
            String novaHora = scanner.nextLine();

            fachada.remarcarConsulta(idConsulta, LocalDate.parse(dataConsulta), LocalTime.parse(horaConsulta),
                    LocalDate.parse(novaData), LocalTime.parse(novaHora));

            System.out.println("Consulta remarcada com sucesso!");
        } catch (ConsultaNaoExisteException | ConsultaJaExisteException e) {
            System.out.println("Erro ao remarcar consulta: " + e.getMessage());
        }
    }
}
