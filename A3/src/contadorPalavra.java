import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class contadorPalavra extends JFrame {

    private JTextArea areatexto;
    private JLabel contadorPalavraLabel;
    private JLabel contadorCaracterLabel;
    private JLabel contadorLinhaLabel;
    private BancoDados dbHelper;

    public contadorPalavra() {
        dbHelper = new BancoDados();
        setTitle("Contador de Palavras");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areatexto = new JTextArea();
        contadorPalavraLabel = new JLabel("Palavras: 0");
        contadorCaracterLabel = new JLabel("Caracteres: 0");
        contadorLinhaLabel = new JLabel("Linhas: 0");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(areatexto), BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(1, 3));
        infoPanel.add(contadorPalavraLabel);
        infoPanel.add(contadorCaracterLabel);
        infoPanel.add(contadorLinhaLabel);
        panel.add(infoPanel, BorderLayout.SOUTH);

        add(panel);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Salvar");
        JButton loadButton = new JButton("Carregar");
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        panel.add(buttonPanel, BorderLayout.NORTH);

        areatexto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCounts();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCounts();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCounts();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveText();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTexts();
            }
        });
    }

    private void updateCounts() {
        String texto = areatexto.getText();
        int contadorPalavra = EstatisticasTexto.contadorPalavras(texto);
        int contadorCaracter = EstatisticasTexto.contadorCaracter(texto);
        int contadorLinha = EstatisticasTexto.contadorLinha(texto);

        contadorPalavraLabel.setText("Palavras: " + contadorPalavra);
        contadorCaracterLabel.setText("Caracteres: " + contadorCaracter);
        contadorLinhaLabel.setText("Linhas: " + contadorLinha);
    }

    private void saveText() {
        String texto = areatexto.getText();
        int contadorPalavra = EstatisticasTexto.contadorPalavras(texto);
        int contadorCaracter = EstatisticasTexto.contadorCaracter(texto);
        int contadorLinha = EstatisticasTexto.contadorLinha(texto);
        dbHelper.inserirtexto(texto, contadorPalavra, contadorCaracter, contadorLinha);
    }

    private void loadTexts() {
        ResultSet rs = dbHelper.gettodostextos();
        StringBuilder allTexts = new StringBuilder();
        try {
            while (rs.next()) {
                allTexts.append("ID: ").append(rs.getInt("idCONTADOR")).append("\n");
                allTexts.append("Texto: ").append(rs.getString("texto")).append("\n");
                allTexts.append("Palavras: ").append(rs.getInt("contadorPalavra")).append("\n");
                allTexts.append("Caracteres: ").append(rs.getInt("contadorCaracter")).append("\n");
                allTexts.append("Linhas: ").append(rs.getInt("contadorLinha")).append("\n");
                allTexts.append("\n");
            }
            JOptionPane.showMessageDialog(this, allTexts.toString(), "Textos Carregados",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}