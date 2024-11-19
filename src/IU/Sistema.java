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
import Negocio.Execoes.ExcecoesPessoa.PessoaNaoExisteException;
import Negocio.NegocioConsultas;
import Negocio.NegocioPaciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            System.out.println("7. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> cadastrarPaciente(scanner);
                case 2 -> cadastrarMedico(scanner);
                case 3 -> agendarConsulta(scanner);
                case 4 -> listarPacientes();
                case 5 -> listarMedicos();
                case 6 -> buscarConsulta(scanner);
                case 7 -> {
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
        String nome = scanner.next();
        scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        scanner.nextLine();
        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
        String dataNascimento = scanner.next();
        scanner.nextLine();
        System.out.print("Sexo (MASCULINO/FEMININO): ");
        Sexo sexo = Sexo.valueOf(scanner.next().toUpperCase());
        scanner.nextLine();
        System.out.print("Plano (SUS/PRATA/GOLD): ");
        Plano plano = Plano.valueOf(scanner.next().toUpperCase());
        scanner.nextLine();

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
        String nome = scanner.next();
        scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        scanner.nextLine();
        System.out.print("Data de Nascimento (YYYY-MM-DD): ");
        String dataNascimento = scanner.next();
        scanner.nextLine();
        System.out.print("Sexo (MASCULINO/FEMININO): ");
        Sexo sexo = Sexo.valueOf(scanner.next().toUpperCase());
        scanner.nextLine();
        System.out.print("CRM: ");
        String crm = scanner.next();
        scanner.nextLine();
        System.out.print("Especialidade: ");
        Especialidade especialidade = Especialidade.valueOf(scanner.next().toUpperCase());
        scanner.nextLine();

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
            scanner.nextLine();

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

    }
}
