package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VistaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAdmin frame = new VistaAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 580);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 221, 218));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Email", "Contrase\u00F1a", "Direccion", "Sucursal", "Rol", "ID Venta", "ID Venta Producto"
			}
		));
		table.setBounds(34, 58, 1006, 290);
		contentPane.add(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(63, 192, 108));
		panel.setBounds(0, 0, 1078, 161);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPanelDeAdministrador = new JLabel("Panel de Administrador - Jumbox");
		lblPanelDeAdministrador.setForeground(new Color(255, 255, 255));
		lblPanelDeAdministrador.setFont(new Font("Montserrat", Font.BOLD, 18));
		lblPanelDeAdministrador.setBounds(42, 15, 374, 27);
		panel.add(lblPanelDeAdministrador);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(34, 376, 453, 145);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Acciones");
		lblNewLabel.setForeground(new Color(63, 192, 108));
		lblNewLabel.setBackground(new Color(63, 192, 108));
		lblNewLabel.setBounds(37, 24, 136, 13);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Montserrat", Font.BOLD, 18));

		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setBackground(new Color(63, 192, 108));
		btnAgregar.setFont(new Font("Montserrat", Font.PLAIN, 14));
		btnAgregar.setBounds(37, 88, 110, 27);
		panel_1.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setBackground(new Color(63, 192, 108));
		btnEditar.setFont(new Font("Montserrat", Font.PLAIN, 14));
		btnEditar.setBounds(167, 88, 99, 27);
		panel_1.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(63, 192, 108));
		btnEliminar.setFont(new Font("Montserrat", Font.PLAIN, 14));
		btnEliminar.setBounds(284, 88, 115, 27);
		panel_1.add(btnEliminar);
		
		JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un usuario de la tabla y una acción a realizar:");
		lblSeleccioneUnUsuario.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblSeleccioneUnUsuario.setBounds(37, 48, 370, 17);
		panel_1.add(lblSeleccioneUnUsuario);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(517, 376, 523, 145);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(63, 192, 108));
		btnNewButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
		btnNewButton.setBounds(263, 87, 96, 27);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(63, 192, 108));
		btnNewButton_1.setFont(new Font("Montserrat", Font.PLAIN, 14));
		btnNewButton_1.setBounds(374, 87, 102, 27);
		panel_2.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(36, 87, 206, 27);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblFiltrarResultados = new JLabel("Filtrar Resultados");
		lblFiltrarResultados.setForeground(new Color(63, 192, 108));
		lblFiltrarResultados.setBackground(new Color(63, 192, 108));
		lblFiltrarResultados.setBounds(35, 23, 176, 17);
		panel_2.add(lblFiltrarResultados);
		lblFiltrarResultados.setFont(new Font("Montserrat", Font.BOLD, 16));
		
		JLabel lblFiltreLosResultados = new JLabel("Filtre los resultados por letras, palabras, números, etc:");
		lblFiltreLosResultados.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblFiltreLosResultados.setBounds(36, 50, 436, 17);
		panel_2.add(lblFiltreLosResultados);
		

		
	}
}
