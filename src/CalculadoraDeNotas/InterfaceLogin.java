package CalculadoraDeNotas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class InterfaceLogin {

	private JFrame frame;
	private JTextField tfUsuario;
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceLogin window = new InterfaceLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login - Calculadora de Notas");
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogin.setBounds(188, 38, 44, 25);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setBounds(92, 60, 239, 20);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(72, 98, 56, 19);
		frame.getContentPane().add(lblUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(140, 97, 208, 25);
		frame.getContentPane().add(tfUsuario);
		tfUsuario.setColumns(20);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(72, 139, 50, 25);
		frame.getContentPane().add(lblSenha);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(140, 139, 208, 25);
		frame.getContentPane().add(tfSenha);
		
		JButton btnLogin = new JButton("Fazer Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				String username = tfUsuario.getText();
				String password = new String(tfSenha.getPassword());
		        if (login.verificarCredenciais(username, password)) {
		        	login.obterAlunoId(username);
		        	lblStatus.setForeground(new Color(0, 128, 0));
		        	lblStatus.setText("Login bem-sucedido!");
		        	frame.setVisible(false);
		        	InterfaceCalculadora interfaceCalculadora = new InterfaceCalculadora();
		        	interfaceCalculadora.frame.setVisible(true);
		        	interfaceCalculadora.importarNotas(username);
		        } else {
		    		lblStatus.setForeground(new Color(255, 0, 0));
		        	lblStatus.setText("Usuário ou senha incorretos");
		        }
			}
		});
		btnLogin.setBounds(72, 187, 276, 39);
		frame.getContentPane().add(btnLogin);
		

		

	}
}
