package CalculadoraDeNotas;

import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.text.*;

public class FormatacaoNumeros {
	public static void formatarNumeros(JFormattedTextField textField) {
		NumberFormatter formatacaoCaixaTexto = new NumberFormatter(NumberFormat.getNumberInstance());
		formatacaoCaixaTexto.setValueClass(Double.class);
		formatacaoCaixaTexto.setAllowsInvalid(false);
		textField.setFormatterFactory(new DefaultFormatterFactory(formatacaoCaixaTexto));
	}
}
