package CalculadoraDeNotas;

import javax.swing.JTable;

public class CalcularMedia {

	public static double CalculoMedia(JTable table_1) {
		int numRows = table_1.getRowCount();
        double somaPesos = 0;
        double somaPercentuaisPonderados = 0;
        int numSelecionados = 0;

        for (int i = 0; i < numRows; i++) {
            boolean selecionado = (boolean) table_1.getValueAt(i, 0);
            if (selecionado) {
                double nota = (double) table_1.getValueAt(i, 1);
                double percentual = (double) table_1.getValueAt(i, 2);

                double percentualNota = (nota / percentual) * 100;
                somaPercentuaisPonderados += percentualNota * percentual;
                somaPesos += percentual;
                numSelecionados++;
            }
        }

        if (numSelecionados > 0 && somaPesos > 0) {
            return somaPercentuaisPonderados / somaPesos;
        } else {
            return 0;
        }
    }
}
