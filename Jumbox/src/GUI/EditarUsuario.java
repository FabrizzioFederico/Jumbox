package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import DLL.ControllerUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EditarUsuario extends JFrame {

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
	//Constructor 
	public EditarUsuario( Usuario usuario ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(48, 72, 116, 14);
		contentPane.add(nombre);
		
		inpNombre = new JTextField();
		inpNombre.setBounds(48, 97, 116, 20);
		inpNombre.setText(usuario.getNombre());
		contentPane.add(inpNombre);
		inpNombre.setColumns(10);
		
		mail = new JLabel("Mail");
		mail.setBounds(48, 128, 116, 14);
		contentPane.add(mail);
		
		inpMail = new JTextField();
		inpMail.setText(usuario.getEmail());
		inpMail.setColumns(10);
		inpMail.setBounds(48, 153, 116, 20);
		contentPane.add(inpMail);
		
		inpContrasenia = new JTextField();
		inpContrasenia.setText(usuario.getContrasenia());
		inpContrasenia.setColumns(10);
		inpContrasenia.setBounds(48, 209, 116, 20);
		contentPane.add(inpContrasenia);
		
		contrasenia = new JLabel("Contraseña");
		contrasenia.setBounds(48, 184, 116, 14);
		contentPane.add(contrasenia);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Cliente");
		comboBox.addItem("Encargado Stock");
		comboBox.addItem("Repartidor");
		comboBox.addItem("Admin");

		comboBox.setBounds(48, 244, 116, 22);
		contentPane.add(comboBox);
		btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario nuevo = usuario;
				nuevo.setNombre(inpNombre.getText());
				nuevo.setEmail(inpMail.getText());
				nuevo.setElegido((String)comboBox.getSelectedItem());
				nuevo.setContrasenia(inpContrasenia.getText());
				
				if (ControllerUsuario.Editar(nuevo)) {
					JOptionPane.showMessageDialog(null, "Se edito");
					
				}else {
					JOptionPane.showMessageDialog(null, "No se edito");
				}
				
			}
		});
		btnNewButton.setBounds(48, 277, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VistaUsuarios nuevo = new 	VistaUsuarios();
				nuevo.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(181, 277, 89, 23);
		contentPane.add(btnNewButton_1);
		
		tipo = new JLabel("tipo");
		tipo.setBounds(48, 231, 116, 14);
		contentPane.add(tipo);
		
	
	}
}
