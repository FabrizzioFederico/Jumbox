package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Usuario;
import DLL.ControllerUsuario;
import repository.Rol;
import repository.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class RegistroCliente extends JFrame {

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
	public RegistroCliente( Usuario usuario ) {
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 800, 500);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        JButton btnAgregar = new JButton("Agregar");
	        btnAgregar.setBounds(10, 270, 150, 40);
	        contentPane.add(btnAgregar);
		
        btnAgregar.addActionListener(e -> {
            JTextField nombreField = new JTextField();
            JTextField emailField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField direccionField = new JTextField();
            JTextField idSucursalField = new JTextField();
           // JComboBox<Rol> rolComboBox = new JComboBox<>(Rol.values());

            Object[] fields = {
                "Nombre:", nombreField,
                "Email:", emailField,
                "Contraseña:", passwordField,
                "Dirección:", direccionField,
                "Sucursal (ID numérico):", idSucursalField,
                "Rol:", "CLIENTE"
            };

            while (true) {
                int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
                
                if (option != JOptionPane.OK_OPTION) {
                    return;
                }
                
                if (Validaciones.validarNombreSinIngreso(nombreField.getText()) == null) {
                    JOptionPane.showMessageDialog(null, "Nombre inválido\nSolo letras y espacios permitidos", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                if (Validaciones.validarEmail(emailField.getText()) == null) {
                    JOptionPane.showMessageDialog(null, "Email inválido\nFormato: usuario@dominio.com", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                
                if (Validaciones.validarContraseniaSinIngreso(new String(passwordField.getPassword())) == null) {
                    JOptionPane.showMessageDialog(null, "Contraseña inválida\nDebe tener:\n- 8+ caracteres\n- 1 mayúscula\n- 1 minúscula\n- 1 número", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                
                if (Validaciones.validarAlfanumerico(direccionField.getText()) == null) {
                    JOptionPane.showMessageDialog(null, "Dirección Incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    continue;
               }
                
                if (Validaciones.validarNumeroPositivo(idSucursalField.getText()) < 0) {
                     JOptionPane.showMessageDialog(null, "ID Sucursal debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                     continue;
                }
                
                
                Usuario nuevo = new Usuario(0,
                    nombreField.getText().trim(),
                    emailField.getText().trim(),
                    new String(passwordField.getPassword()),
                    direccionField.getText().trim(),
                    Integer.parseInt(idSucursalField.getText()),
                    "CLIENTE"
//                  ((Rol) rolComboBox.getSelectedItem()).name()            
                );

                ControllerUsuario.Registrarse(nuevo);
                break;
            }
        });
		
		
	}
	
}