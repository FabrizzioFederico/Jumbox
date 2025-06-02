package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
	private JLabel sucursal;
	private JTextField inpSucursal;
	private JLabel direccion;
	private JTextField inpDireccion;
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
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(48, 466, 338, 14);
		contentPane.add(lblInfo);
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
		
		contrasenia = new JLabel("Contraseña");
		contrasenia.setBounds(48, 310, 116, 14);
		contentPane.add(contrasenia);
		
		inpContrasenia = new JTextField();
		inpContrasenia.setText(usuario.getContrasenia());
		inpContrasenia.setColumns(10);
		inpContrasenia.setBounds(48, 335, 116, 20);
		contentPane.add(inpContrasenia);
		
		sucursal = new JLabel("Sucursal");
		sucursal.setBounds(48, 254, 116, 14);
		contentPane.add(sucursal);
		
		inpSucursal = new JTextField();
		inpSucursal.setText(String.valueOf(usuario.getId_sucursal()));
		inpSucursal.setColumns(10);
		inpSucursal.setBounds(48, 279, 116, 20);
		contentPane.add(inpSucursal);
		
		direccion = new JLabel("Direccion");
		direccion.setBounds(48, 184, 116, 14);
		contentPane.add(direccion);
		
		inpDireccion = new JTextField();
		inpDireccion.setText(usuario.getDireccion());
		inpDireccion.setColumns(10);
		inpDireccion.setBounds(48, 211, 116, 20);
		contentPane.add(inpDireccion);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("CLIENTE");
		comboBox.addItem("ENCARGADO_STOCK");
		comboBox.addItem("REPARTIDOR");
		comboBox.addItem("ADMIN");
		comboBox.setSelectedItem(usuario.getElegido());
		comboBox.setBounds(48, 391, 116, 22);
		contentPane.add(comboBox);
		btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//"UPDATE `usuario` SET `nombre`=?,`email`=?,`contrasenia`=?,`direccion`=?,`id_sucursal`=?,`rol`=?,`Venta_id_venta`=?,`Venta_VentaProducto_id_venta`=? WHERE id = ?"
				Usuario nuevo = usuario;
				nuevo.setNombre(Validaciones.validarNombreSinIngreso(inpNombre.getText()));
				nuevo.setEmail(inpMail.getText());
				nuevo.setContrasenia(inpContrasenia.getText());
				nuevo.setDireccion(inpDireccion.getText());
				int idSucursal = Integer.parseInt(inpSucursal.getText().isEmpty()?"0":inpSucursal.getText());
			    nuevo.setId_sucursal(idSucursal);
				nuevo.setElegido((String)comboBox.getSelectedItem());
			    nuevo.setVenta_id_venta(nuevo.getVenta_id_venta());
			    nuevo.setVenta_VentaProducto_id_venta(nuevo.getVenta_VentaProducto_id_venta());
			    nuevo.setId(nuevo.getId());
				
				
			    lblInfo.setText(Usuario.Editar(nuevo) );
				
				
			}
		});
		btnNewButton.setBounds(48, 424, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VistaUsuarios nuevo = new VistaUsuarios();
				nuevo.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(171, 424, 89, 23);
		contentPane.add(btnNewButton_1);
		
		tipo = new JLabel("Tipo");
		tipo.setBounds(48, 366, 116, 14);
		contentPane.add(tipo);
		
	
		
	
	}
}
