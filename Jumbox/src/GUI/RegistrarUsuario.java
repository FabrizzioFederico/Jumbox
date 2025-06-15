package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import DLL.ControllerUsuario;
import repository.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class RegistrarUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpNombre;
	private JLabel mail;
	private JTextField inpMail;
	private JTextField inpContrasenia;
	private JLabel contrasenia;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel tipo;
	private JLabel sucursal;
	private JTextField inpSucursal;
	private JLabel direccion;
	private JTextField inpDireccion;
	//Constructor 
	public RegistrarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(48, 72, 116, 14);
		contentPane.add(nombre);
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(48, 466, 338, 14);
		contentPane.add(lblInfo);
		inpNombre = new JTextField();
		inpNombre.setBounds(48, 97, 116, 20);
		inpNombre.setText("");
		contentPane.add(inpNombre);
		inpNombre.setColumns(10);
		
		mail = new JLabel("Mail");
		mail.setBounds(48, 128, 116, 14);
		contentPane.add(mail);
		
		inpMail = new JTextField();
		inpMail.setText("");
		inpMail.setColumns(10);
		inpMail.setBounds(48, 153, 116, 20);
		contentPane.add(inpMail);
		
		contrasenia = new JLabel("Contraseña");
		contrasenia.setBounds(48, 310, 116, 14);
		contentPane.add(contrasenia);
		
		inpContrasenia = new JPasswordField();
		inpContrasenia.setColumns(10);
		inpContrasenia.setBounds(48, 335, 116, 20);
		contentPane.add(inpContrasenia);
		
		sucursal = new JLabel("Sucursal");
		sucursal.setBounds(48, 254, 116, 14);
		contentPane.add(sucursal);
		
		inpSucursal = new JTextField();
		inpSucursal.setText("");
		inpSucursal.setColumns(10);
		inpSucursal.setBounds(48, 279, 116, 20);
		contentPane.add(inpSucursal);
		
		direccion = new JLabel("Direccion");
		direccion.setBounds(48, 184, 116, 14);
		contentPane.add(direccion);
		
		inpDireccion = new JTextField();
		inpDireccion.setText("");
		inpDireccion.setColumns(10);
		inpDireccion.setBounds(48, 211, 116, 20);
		contentPane.add(inpDireccion);
		
		
		
		btnNewButton = new JButton("Registrarse");
		btnNewButton.setBounds(48, 424, 116, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario nuevo = new Usuario();
				String nombreValidado = Validaciones.validarNombreSinIngreso(inpNombre.getText());
		        if (nombreValidado == null) {
		        	lblInfo.setText("Nombre inválido: Solo letras y espacios permitidos");
		            return;
		        }
		        nuevo.setNombre(nombreValidado);
		        String emailValidado = Validaciones.validarEmail(inpMail.getText());
		        if (emailValidado == null) {
		            lblInfo.setText("Email inválido o Vacio");
		            return;
		        }
				nuevo.setEmail(inpMail.getText());
				String passValidada = Validaciones.validarContraseniaSinIngreso(inpContrasenia.getText());
				if (passValidada == null) {
		            lblInfo.setText("Contraseña inválida: Requiere 8+ caracteres \n 1 mayúscula \n 1 minúscula");
		            return;
				}
				nuevo.setContrasenia(inpContrasenia.getText());
				nuevo.setDireccion(inpDireccion.getText());
				int idSucursal = Integer.parseInt(inpSucursal.getText().isEmpty()?"0":inpSucursal.getText());
			    nuevo.setId_sucursal(idSucursal);
				nuevo.setElegido("CLIENTE");
			    
				
				
				Usuario.Registrarse(nuevo);
				JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente!!!");
				
				VistaClientes vistaCliente = new VistaClientes(nuevo);
				vistaCliente.setVisible(true);
				dispose();
				
				
			}
		});
		
		
	}
}
