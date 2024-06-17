public class EstatisticasTexto {

    public static int contadorPalavras(String texto) {
        if (texto == null  texto.isEmpty()) {
            return 0;
        }
        String[] palavras = texto.trim().split("\s+");
        return palavras.length;
    }

    public static int contadorCaracter(String texto) {
        if (texto == null) {
            return 0;
        }
        return texto.length();
    }

    public static int contadorLinha(String texto) {
        if (texto == null  texto.isEmpty()) {
            return 0;
        }
        return texto.split("\n").length;
    }
}