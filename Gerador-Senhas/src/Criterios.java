public class Criterios {
    private int comprimento;
    private boolean incluirMaiusculas;
    private boolean incluirMinusculas;
    private boolean incluirNumeros;
    private boolean incluirCaracteresEspeciais;

    public Criterios(int comprimento, boolean incluirMaiusculas, boolean incluirMinusculas, boolean incluirNumeros, boolean incluirCaracteresEspeciais) {
        this.comprimento = comprimento;
        this.incluirMaiusculas = incluirMaiusculas;
        this.incluirMinusculas = incluirMinusculas;
        this.incluirNumeros = incluirNumeros;
        this.incluirCaracteresEspeciais = incluirCaracteresEspeciais;
    }

    public int getComprimento() {
        return comprimento;
    }

    public boolean isIncluirMaiusculas() {
        return incluirMaiusculas;
    }

    public boolean isIncluirMinusculas() {
        return incluirMinusculas;
    }

    public boolean isIncluirNumeros() {
        return incluirNumeros;
    }

    public boolean isIncluirCaracteresEspeciais() {
        return incluirCaracteresEspeciais;
    }

    public boolean saoValidos() {
        return comprimento > 0 && (incluirMaiusculas || incluirMinusculas || incluirNumeros || incluirCaracteresEspeciais);
    }
}
