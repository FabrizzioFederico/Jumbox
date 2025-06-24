package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import BLL.Usuario;
import DLL.ControllerUsuario;
import repository.Local;
import repository.Rol;
import repository.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class RegistrarUsuario extends JDialog {

    private VistaAdmin parentFrame;

    public RegistrarUsuario() {
        
        
        setSize(500, 400);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(222, 221, 218));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(222, 221, 218));

        JTextField nombreField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField direccionField = new JTextField();
        
        JComboBox<Local> idSucursalCombox = new JComboBox<>(Local.values());
        
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Contraseña:"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Dirección:"));
        formPanel.add(direccionField);
        formPanel.add(new JLabel("Sucursal:"));
        formPanel.add(idSucursalCombox);

        JLabel lblInfo = new JLabel();
        lblInfo.setForeground(Color.RED);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(222, 221, 218));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(192, 57, 43));
        btnCancelar.addActionListener(e -> dispose());
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(63, 192, 108));
        btnGuardar.addActionListener(e -> {
        	Usuario nuevo = new Usuario();
            if (validarCampos(nombreField, emailField, passwordField, direccionField, lblInfo)) {
                nuevo.setNombre(nombreField.getText().trim());
                nuevo.setEmail(emailField.getText().trim());
                nuevo.setContrasenia(new String(passwordField.getPassword()));
                nuevo.setDireccion(direccionField.getText().trim());
                Local sucursalSeleccionada = (Local) idSucursalCombox.getSelectedItem();
                nuevo.setId_sucursal(sucursalSeleccionada.getId());
                nuevo.setElegido("CLIENTE");
                
                Usuario.Registrarse(nuevo);
				JOptionPane.showMessageDialog(null, "Usuario Creado Correctamente!!!");
				
				VistaClientes vistaCliente = new VistaClientes(nuevo);
				vistaCliente.setVisible(true);
				dispose();
            }
        });

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(lblInfo, BorderLayout.NORTH);
        
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    private boolean validarCampos(JTextField nombreField, JTextField emailField, 
                                JPasswordField passwordField, JTextField direccionField,
                                JLabel lblError) {
        if (Validaciones.validarNombreSinIngreso(nombreField.getText()) == null) {
            lblError.setText("Nombre inválido: Solo letras y espacios permitidos");
            return false;
        }

        if (Validaciones.validarEmail(emailField.getText()) == null) {
            lblError.setText("Email inválido o vacío");
            return false;
        }

        if (Validaciones.validarContraseniaSinIngreso(new String(passwordField.getPassword())) == null) {
            lblError.setText("Contraseña inválida: 8+ caracteres, 1 mayúscula, 1 minúscula");
            return false;
        }
        
        if (Validaciones.validarAlfanumerico(direccionField.getText()) == null) {
            lblError.setText("Dirección incorrecta");
            return false;
        }
        
        lblError.setText("");
        return true;
    }
}