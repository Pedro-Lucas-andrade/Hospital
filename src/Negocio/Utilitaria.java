package Negocio;

public class Utilitaria {

    public static boolean validarCpf(String cpf) {

        if (cpf != null && cpf.length() == 11) {

            for (char c : cpf.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}