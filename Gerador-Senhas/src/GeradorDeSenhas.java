import java.util.Random;

public class GeradorDeSenhas {
    private Criterios criterios;
    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZÇ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyzç";
    private static final String NUMEROS = "0123456789";
    private static final String CARACTERES_ESPECIAIS = "!@#$%^&*(){}-_+=<>?/|";

    public GeradorDeSenhas(Criterios criterios) {
        this.criterios = criterios;
    }

    public String gerarSenha() {
        if (!criterios.saoValidos()) {
            throw new IllegalArgumentException("Critérios inválidos para a geração da senha.");
        }

        StringBuilder caracteres = new StringBuilder();
        Random random = new Random();

        if (criterios.isIncluirMaiusculas()) {
            caracteres.append(MAIUSCULAS);
        }
        if (criterios.isIncluirMinusculas()) {
            caracteres.append(MINUSCULAS);
        }
        if (criterios.isIncluirNumeros()) {
            caracteres.append(NUMEROS);
        }
        if (criterios.isIncluirCaracteresEspeciais()) {
            caracteres.append(CARACTERES_ESPECIAIS);
        }

        StringBuilder senha = new StringBuilder(criterios.getComprimento());
        for (int i = 0; i < criterios.getComprimento(); i++) {
            senha.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }

        return senha.toString();
    }
}
