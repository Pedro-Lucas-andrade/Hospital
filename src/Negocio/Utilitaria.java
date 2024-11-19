package Negocio;

public class Utilitaria {

    public static boolean validarCpf(String cpf) {
        // Verifica se o CPF tem exatamente 11 caracteres
        if (cpf != null && cpf.length() == 11) {
            // Verifica se todos os caracteres são dígitos
            for (char c : cpf.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true; // CPF válido
        }
        return false; // CPF inválido
    }
}