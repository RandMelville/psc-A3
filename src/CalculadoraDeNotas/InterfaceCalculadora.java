package CalculadoraDeNotas;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import CalculadoraDeNotas.Login.AlunoIdAtual;

import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;

public class InterfaceCalculadora {

	JFrame frame;
	private JTable table_1;
	private JTextField lblMedia;
	private int alunoIdLogado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCalculadora window = new InterfaceCalculadora();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceCalculadora() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Calculadora de Notas");
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		alunoIdLogado = 1;

		JPanel painelCadastro = new JPanel();
		painelCadastro.setBounds(10, 24, 864, 135);
		frame.getContentPane().add(painelCadastro);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblNota);

		JFormattedTextField tfNota = new JFormattedTextField();
		frame.getContentPane().add(tfNota);
		FormatacaoNumeros.formatarNumeros(tfNota);

		JLabel lblPercentual = new JLabel("Percentual");
		lblPercentual.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblPercentual);

		JFormattedTextField tfPercentual = new JFormattedTextField();
		frame.getContentPane().add(tfPercentual);
		FormatacaoNumeros.formatarNumeros(tfPercentual);

		JLabel lblMateria = new JLabel("Matéria");
		lblMateria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblMateria);

		JComboBox selMateria = new JComboBox();
		selMateria.setModel(new DefaultComboBoxModel(new String[] { "", "Biologia", "Educação Física", "Geografia",
				"Informática", "Inglês", "Matemática", "Português" }));
		selMateria.setToolTipText("");
		frame.getContentPane().add(selMateria);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblTipo);

		JComboBox selTipo = new JComboBox();
		selTipo.setModel(new DefaultComboBoxModel(new String[] { "", "Participação", "Prova", "Trabalho" }));
		selTipo.setToolTipText("");
		frame.getContentPane().add(selTipo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 187, 755, 262);
		frame.getContentPane().add(scrollPane);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Selecionado", "Nota", "Percentual", "Matéria", "Tipo" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Object.class, Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		TableColumnModel columnModel = table_1.getColumnModel();
		columnModel.getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
			private final JCheckBox checkBox = new JCheckBox();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (value instanceof Boolean) {
					checkBox.setSelected((Boolean) value);
				}
				checkBox.setHorizontalAlignment(SwingConstants.CENTER);
				table.setRowHeight(row, checkBox.getPreferredSize().height);
				return checkBox;
			}
		});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table_1.setDefaultRenderer(Object.class, centerRenderer);

		scrollPane.setViewportView(table_1);

		JButton btnAdicionarNota = new JButton("Adicionar nota");
		btnAdicionarNota.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionarNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double nota = (Double) tfNota.getValue();
				double percentual = (Double) tfPercentual.getValue();
				String materia = (String) selMateria.getSelectedItem();
				String tipo = (String) selTipo.getSelectedItem();
				AdicionarNota incluir = new AdicionarNota();
				if (nota <= percentual) {
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					model.addRow(new Object[] { false, nota, percentual, materia, tipo });
					incluir.setNota(nota);
					incluir.setPercentual(percentual);
					incluir.setMateria(materia);
					incluir.setTipo(tipo);
					incluir.adicionarNota();
				} else {
					JOptionPane.showMessageDialog(null, "O valor da nota não pode ser maior que o percentual da nota.");
				}
			}
		});

		GroupLayout gl_painelCadastro = new GroupLayout(painelCadastro);
		gl_painelCadastro.setHorizontalGroup(gl_painelCadastro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelCadastro.createSequentialGroup().addGap(53).addGroup(gl_painelCadastro
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painelCadastro.createSequentialGroup()
								.addComponent(lblNota, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addGap(13)
								.addComponent(tfNota, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblPercentual, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addGap(13)
								.addComponent(tfPercentual, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblMateria, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addGap(13)
								.addComponent(selMateria, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addGap(13)
								.addComponent(selTipo, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAdicionarNota, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(56, Short.MAX_VALUE)));
		gl_painelCadastro.setVerticalGroup(gl_painelCadastro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelCadastro.createSequentialGroup().addGap(33).addGroup(gl_painelCadastro
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_painelCadastro.createSequentialGroup().addGap(2).addComponent(lblNota,
								GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(tfNota, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_painelCadastro.createSequentialGroup().addGap(2).addComponent(lblPercentual,
								GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(tfPercentual, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_painelCadastro.createSequentialGroup().addGap(4).addComponent(lblMateria,
								GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(selMateria, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_painelCadastro.createSequentialGroup().addGap(4).addComponent(lblTipo,
								GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(selTipo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)).addGap(34)
						.addComponent(btnAdicionarNota, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(55, Short.MAX_VALUE)));
		painelCadastro.setLayout(gl_painelCadastro);

		lblMedia = new JTextField();
		lblMedia.setBackground(new Color(240, 240, 240));
		lblMedia.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMedia.setText("MÉDIA:");
		lblMedia.setBounds(65, 455, 364, 30);
		frame.getContentPane().add(lblMedia);
		lblMedia.setColumns(10);

		JButton btnCalcularMedia = new JButton("Calcular Média");
		btnCalcularMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double media = CalcularMedia.CalculoMedia(table_1);
				NumberFormat formatter = NumberFormat.getNumberInstance();
				formatter.setMinimumFractionDigits(2);
				formatter.setMaximumFractionDigits(2);

				lblMedia.setText("MÉDIA: " + formatter.format(media));
				if (media >= 70) {
					lblMedia.setForeground(new Color(0, 128, 0));
				} else if (media < 50) {
					lblMedia.setForeground(new Color(255, 0, 0));

				} else {
					lblMedia.setForeground(new Color(255, 128, 0));
				}
			}

		});
		btnCalcularMedia.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCalcularMedia.setBounds(439, 455, 200, 30);
		frame.getContentPane().add(btnCalcularMedia);

		JButton btnApagar = new JButton("Apagar notas");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTable tabelaNotas = table_1;
				DefaultTableModel model = (DefaultTableModel) tabelaNotas.getModel();
			    int linhasSelecionadas = tabelaNotas.getSelectedRowCount();

			    if (linhasSelecionadas == 0) {
			        JOptionPane.showMessageDialog(null, "Nenhuma nota selecionada.");
			        return;
			    }

			    DeletarNota deletarNota = new DeletarNota(); 
			    deletarNota.deletarNota(model);
			}
		});
		btnApagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnApagar.setBounds(660, 455, 160, 30);
		frame.getContentPane().add(btnApagar);

	}

	public void importarNotas(String username) {
		ImportarNotas importar = new ImportarNotas();
		Login usuarioAtual = new Login();
		int alunoId = AlunoIdAtual.getAlunoId();
		usuarioAtual.obterAlunoId(username);
		List<Object[]> dados = importar.carregarDados(alunoId);

		DefaultTableModel model = (DefaultTableModel) table_1.getModel();

		for (Object[] linha : dados) {
			model.addRow(linha);
		}
	}

}
