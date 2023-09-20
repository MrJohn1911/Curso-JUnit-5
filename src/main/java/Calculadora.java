public class Calculadora {

    public int soma(int... a) {
        int soma = 0;

        for (int numero: a) {
            soma += numero;
        }

        return soma;
    }

    public float divisao(int numerador, int denominador) {
        return (float) numerador/denominador;
    }
}
