import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Tela_acesso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private Usuario usuario = new Usuario();
    private JTextField txtUsuario;
    private JLabel lblSenhaGerada;
    private JTextField txtSenha;
    private JTextField txtEmail;
    private JTextField txtTitulo;
    private JButton btnGerarSenha;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private boolean useUppercase = false;
    private boolean useLowercase = false;
    private boolean useNumbers = false;
    private boolean useSpecialChars = false;
    private JButton btnAvanar;


    public Tela_acesso() {
        setTitle("Tela de Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(521, 313);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        createTitleTextField();
        createUserLabelAndTextField();
        createPasswordLabelAndTextField();
        createConfirmPasswordLabelAndTextField();
        createGeneratePasswordButton();
        createOptionsCheckboxes();
        createSaveButton();
        createUpdateButton();
        createDeleteButton();
        createAvancarButton();
    }

    private void createTitleTextField() {
        txtTitulo = new JTextField("Tela de Cadastro");
        txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        txtTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        txtTitulo.setEditable(false);
        txtTitulo.setBackground(Color.DARK_GRAY);
        txtTitulo.setForeground(Color.WHITE);
        txtTitulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtTitulo.setBounds(-39, 11, 534, 45);
        contentPane.add(txtTitulo);
    }

    private void createUserLabelAndTextField() {
        JLabel lblUsuario = new JLabel("E-mail:");
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsuario.setBounds(20, 78, 80, 25);
        contentPane.add(lblUsuario);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtEmail.setBounds(100, 79, 200, 23);
        contentPane.add(txtEmail);
    }

    private void createPasswordLabelAndTextField() {
        JLabel lblSenha = new JLabel("Usuário:");
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSenha.setBounds(20, 123, 80, 25);
        contentPane.add(lblSenha);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsuario.setBounds(100, 123, 200, 25);
        contentPane.add(txtUsuario);
    }

    private void createConfirmPasswordLabelAndTextField() {
        JLabel lblConfirmarSenha = new JLabel("Senha:");
        lblConfirmarSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblConfirmarSenha.setBounds(20, 164, 58, 36);
        contentPane.add(lblConfirmarSenha);

        lblSenhaGerada = new JLabel(""); // Inicialmente vazio
        lblSenhaGerada.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSenhaGerada.setBounds(100, 164, 200, 36);
        contentPane.add(lblSenhaGerada);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtSenha.setBounds(100, 170, 200, 25);
        ((JPasswordField) txtSenha).setEchoChar('*');
        contentPane.add(txtSenha);
    }

    private void createOptionsCheckboxes() {
        checkBox1 = new JCheckBox("Letras maiúsculas");
        checkBox1.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkBox1.setBackground(Color.LIGHT_GRAY);
        checkBox1.setBounds(310, 110, 151, 25);
        checkBox1.addActionListener(e -> useUppercase = checkBox1.isSelected());
        contentPane.add(checkBox1);

        checkBox2 = new JCheckBox("Letras minúsculas");
        checkBox2.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkBox2.setBackground(Color.LIGHT_GRAY);
        checkBox2.setBounds(310, 140, 151, 25);
        checkBox2.addActionListener(e -> useLowercase = checkBox2.isSelected());
        contentPane.add(checkBox2);

        checkBox3 = new JCheckBox("Números");
        checkBox3.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkBox3.setBackground(Color.LIGHT_GRAY);
        checkBox3.setBounds(310, 170, 131, 25);
        checkBox3.addActionListener(e -> useNumbers = checkBox3.isSelected());
        contentPane.add(checkBox3);

        checkBox4 = new JCheckBox("Caracteres especiais");
        checkBox4.setFont(new Font("Tahoma", Font.BOLD, 14));
        checkBox4.setBackground(Color.LIGHT_GRAY);
        checkBox4.setBounds(310, 200, 151, 25);
        checkBox4.addActionListener(e -> useSpecialChars = checkBox4.isSelected());
        contentPane.add(checkBox4);
    }

    private void createGeneratePasswordButton() {
        btnGerarSenha = new JButton("Gerar Senha");
        btnGerarSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnGerarSenha.setBackground(UIManager.getColor("ScrollBar.background"));
        btnGerarSenha.setBounds(321, 78, 120, 25);

        btnGerarSenha.addActionListener(e -> {
            if (!useUppercase && !useLowercase && !useNumbers && !useSpecialChars) {
                JOptionPane.showMessageDialog(null, "Selecione ao menos um tipo de caractere.");
            } else {
                int comprimeto = Integer.parseInt(JOptionPane.showInputDialog(null,"Escolha o comprimeto de 1 ate 9: "));
                usuario.definirCriterios(comprimeto, useUppercase, useLowercase, useNumbers, useSpecialChars);
                String senhaGerada = usuario.gerarSenha();
                lblSenhaGerada.setText("" + senhaGerada);
            }
        });

        contentPane.add(btnGerarSenha);
    }

    private void createSaveButton() {
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSalvar.setBackground(UIManager.getColor("ScrollBar.background"));
        btnSalvar.setBounds(254, 232, 93, 25);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usuario.setNome(txtUsuario.getText());
                usuario.setEmail(txtEmail.getText());
                usuario.setSenha(lblSenhaGerada.getText());
                usuario.adicionarUsuario();
            }
        });
        contentPane.add(btnSalvar);
    }

    private void createUpdateButton() {
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAtualizar.setBackground(UIManager.getColor("ScrollBar.background"));
        btnAtualizar.setBounds(20, 232, 93, 25);
        contentPane.add(btnAtualizar);
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuarioAtualizar = JOptionPane.showInputDialog(null, "Digite o usuário para o qual deseja alterar a senha:");
                if (usuarioAtualizar != null && !usuarioAtualizar.isEmpty()) {
                    int tamanho = Integer.parseInt(JOptionPane.showInputDialog(null,"Escolha cmprimento da senha"));
                    boolean incluirMaiusculas = JOptionPane.showConfirmDialog(null, "Incluir letras maiúsculas?", "Critérios de Senha", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    boolean incluirMinusculas = JOptionPane.showConfirmDialog(null, "Incluir letras minúsculas?", "Critérios de Senha", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    boolean incluirNumeros = JOptionPane.showConfirmDialog(null, "Incluir números?", "Critérios de Senha", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    boolean incluirCaracteresEspeciais = JOptionPane.showConfirmDialog(null, "Incluir caracteres especiais?", "Critérios de Senha", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                    usuario.definirCriterios(tamanho,incluirMaiusculas,incluirMinusculas,incluirNumeros,incluirCaracteresEspeciais);
                    String senhaAtualizar = usuario.gerarSenha();

                    if (senhaAtualizar != null && !senhaAtualizar.isEmpty()) {
                        txtUsuario.setText(usuarioAtualizar);
                        txtSenha.setText(senhaAtualizar);
                        usuario.setNome(txtUsuario.getText());
                        usuario.setSenha(txtSenha.getText());
                        usuario.atualizarUsuario();
                        JOptionPane.showMessageDialog(null, "Senha para o usuário " + usuarioAtualizar + " atualizada com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhuma senha digitada para o usuário " + usuarioAtualizar + ".");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum usuário digitado.");
                }
            }
        });
        contentPane.add(btnAtualizar);
    }


    private void createDeleteButton() {
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnExcluir.setBackground(UIManager.getColor("ScrollBar.background"));
        btnExcluir.setBounds(133, 232, 93, 25);
        btnExcluir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String user = JOptionPane.showInputDialog(null, "Digite o usuário para excluir:");

                if (user == null || user.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite o usuário para excluir.");
                    return;
                }
                JOptionPane.showMessageDialog(null,"Excluído com sucesso!!");
                usuario.setNome(user.trim());
                usuario.deletarUsuario();
            }
        });
        contentPane.add(btnExcluir);
    }
    private void createAvancarButton() {
        btnAvanar = new JButton("Avançar");
        btnAvanar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAvanar.setBackground(UIManager.getColor("ScrollBar.background"));
        btnAvanar.setBounds(371, 232, 93, 25);
        contentPane.add(btnAvanar);

        btnAvanar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tela_acesso2 layout2 = new Tela_acesso2();
                layout2.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnAvanar);
    }

}