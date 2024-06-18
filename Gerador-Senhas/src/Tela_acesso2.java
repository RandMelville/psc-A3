import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class Tela_acesso2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtOutraPgina;


    public Tela_acesso2() {
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        createHeaderTextField();
        createCadastrarButton();
        createAtualizarButton();
        createExcluirButton();
    }

    private void createHeaderTextField() {
        txtOutraPgina = new JTextField();
        txtOutraPgina.setText("Bem vindo !!");
        txtOutraPgina.setHorizontalAlignment(SwingConstants.CENTER);
        txtOutraPgina.setForeground(Color.WHITE);
        txtOutraPgina.setFont(new Font("Tahoma", Font.BOLD, 20));
        txtOutraPgina.setEditable(false);
        txtOutraPgina.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtOutraPgina.setBackground(Color.DARK_GRAY);
        txtOutraPgina.setBounds(18, 88, 406, 45);
        contentPane.add(txtOutraPgina);
    }

    private void createCadastrarButton() {
    }

    private void createAtualizarButton() {
    }

    private void createExcluirButton() {
    }
}
