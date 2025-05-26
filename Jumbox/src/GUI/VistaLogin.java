package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpUsuario;
	private JTextField inpContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
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
	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 367);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Iniciar Sesión");
		lblTitulo.setFont(new Font("Montserrat", Font.PLAIN, 20));
		lblTitulo.setBounds(282, 65, 167, 29);
		contentPane.add(lblTitulo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 102));
		panel.setBounds(0, 0, 244, 328);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(VistaLogin.class.getResource("/img/logo2.png")));
		lblNewLabel_1.setBounds(-10, 0, 253, 328);
		panel.add(lblNewLabel_1);
		
		JLabel lblBienvenido = new JLabel("¡Bienvenido a Jumbox!");
		lblBienvenido.setBackground(new Color(54, 201, 102));
		lblBienvenido.setForeground(new Color(51, 153, 102));
		lblBienvenido.setFont(new Font("Montserrat", Font.PLAIN, 16));
		lblBienvenido.setBounds(282, 22, 206, 30);
		contentPane.add(lblBienvenido);
		
		JLabel lblNewLabel = new JLabel("Nombre de usuario");
		lblNewLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblNewLabel.setBounds(282, 105, 167, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Montserrat", Font.PLAIN, 12));
		lblContrasea.setBounds(282, 166, 79, 29);
		contentPane.add(lblContrasea);
		
		inpUsuario = new JTextField();
		inpUsuario.setFont(new Font("Montserrat", Font.PLAIN, 11));
		inpUsuario.setForeground(new Color(0, 0, 0));
		inpUsuario.setBackground(new Color(255, 255, 255));
		inpUsuario.setBounds(282, 134, 218, 25);
		contentPane.add(inpUsuario);
		inpUsuario.setColumns(10);
		
		inpContrasenia = new JTextField();
		inpContrasenia.setFont(new Font("Montserrat", Font.PLAIN, 11));
		inpContrasenia.setBounds(282, 192, 218, 25);
		contentPane.add(inpContrasenia);
		inpContrasenia.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIngresar.setBackground(new Color(63, 192, 108));
		btnIngresar.setFont(new Font("Montserrat", Font.PLAIN, 13));
		btnIngresar.setBounds(282, 242, 92, 29);
		contentPane.add(btnIngresar);
		
		JButton btnRegistrar = new JButton("Registrarme");
		btnRegistrar.setBackground(new Color(255, 255, 255));
		btnRegistrar.setFont(new Font("Montserrat", Font.PLAIN, 12));
		btnRegistrar.setBounds(384, 242, 114, 29);
		contentPane.add(btnRegistrar);
	}
}
