package Testes;

import Dados.Repositorios.RepositorioMedicos_Pacientes;
import Negocio.ClassesBasicas.Medico;
import Negocio.ClassesBasicas.Paciente;
import Negocio.Constantes.Especialidade;
import Negocio.Constantes.Plano;
import Negocio.Constantes.Sexo;

public class TestRepositorioPessoas {
        public static void main(String[] args) {
            RepositorioMedicos_Pacientes repo = new RepositorioMedicos_Pacientes();

            // Criando médico e paciente
            Medico medico = new Medico("Dr. João", "12345678900", "01/01/1980", Sexo.MASCULINO, "CRM1234", Especialidade.CARDIOLOGISTA);
            Paciente paciente = new Paciente("Maria Silva", "98765432100", "15/06/1995", Sexo.FEMININO, Plano.SUS);

            // Adicionar médico e paciente
            System.out.println("Adicionado médico? " + repo.adicionar(medico)); // true
            System.out.println("Adicionado paciente? " + repo.adicionar(paciente)); // true

            // Tentar adicionar novamente
            System.out.println("Adicionado médico novamente? " + repo.adicionar(medico)); // false

            // Buscar pessoas
            System.out.println("Médico encontrado? " + repo.buscar("12345678900")); // true
            System.out.println("Paciente encontrado? " + repo.buscar("98765432100")); // true
            System.out.println("CPF não cadastrado encontrado? " + repo.buscar("00000000000")); // false

            // Atualizar nome
            System.out.println("Atualizado? " + repo.atualizar("12345678900", "Dr. João Carlos")); // true
            System.out.println("Médico atualizado: " + repo.ler().get(0).getNome()); // Dr. João Carlos

            // Listar todas as pessoas
            System.out.println("Pessoas no repositório: " + repo.ler());

            // Remover pessoas
            System.out.println("Paciente removido? " + repo.remover(paciente)); // true
            System.out.println("Médico removido? " + repo.remover(medico)); // true
            System.out.println("Lista após remoção: " + repo.ler()); // []
        }
    }