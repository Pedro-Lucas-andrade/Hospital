package Dados.Auxiliares;

public class Ano {
    private int ano;
    private Mes[] arrayMeses = new Mes[12];
    private int[] diasMeses = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public Ano(int ano) {
        this.ano = ano;
        if (isBissexto(ano)) {
            diasMeses[1] = 29;
        }
        for (int i = 0; i < 12; i++) {
            this.arrayMeses[i] = new Mes(diasMeses[i]);
        }
    }

    public Mes[] getArrayMeses() {
        return arrayMeses;
    }

    public int getAno() {
        return this.ano;
    }

    public int getDiasMeses(int mes){
        return diasMeses[mes];
    }

    // Método para verificar se o ano é bissexto
    private boolean isBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }
}
