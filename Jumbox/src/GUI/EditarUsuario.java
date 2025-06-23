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

public class EditarUsuario extends JDialog {

    private VistaAdmin parentFrame;

    public EditarUsuario(VistaAdmin parent, Usuario usuario) {
        super(parent, "Editar Usuario", true);
        this.parentFrame = parent;
        
        setSize(500, 400);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(222, 221, 218));

        // Resto del código de inicialización del formulario...
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(222, 221, 218));

        JTextField nombreField = new JTextField(usuario.getNombre());
        JTextField emailField = new JTextField(usuario.getEmail());
        JPasswordField passwordField = new JPasswordField(usuario.getContrasenia());
        JTextField direccionField = new JTextField(usuario.getDireccion());
        
        JComboBox<Local> idSucursalCombox = new JComboBox<>(Local.values());
        idSucursalCombox.setSelectedIndex(usuario.getId_sucursal() - 1);
        
        JComboBox<Rol> rolComboBox = new JComboBox<>(Rol.values());
        rolComboBox.setSelectedItem(Rol.valueOf(usuario.getElegido()));

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
        formPanel.add(new JLabel("Rol:"));
        formPanel.add(rolComboBox);

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
            if (validarCampos(nombreField, emailField, passwordField, direccionField, lblInfo)) {
                usuario.setNombre(nombreField.getText().trim());
                usuario.setEmail(emailField.getText().trim());
                usuario.setContrasenia(new String(passwordField.getPassword()));
                usuario.setDireccion(direccionField.getText().trim());
                usuario.setId_sucursal(((Local)idSucursalCombox.getSelectedItem()).getId());
                usuario.setElegido(((Rol) rolComboBox.getSelectedItem()).name());
                
                String resultado = ControllerUsuario.Editar(usuario);
                if (resultado.equals("Usuario editado correctamente.")) {
                    parentFrame.cargarTabla(); // Actualizar la tabla en VistaAdmin
                    dispose(); // Cerrar solo el diálogo
                } else {
                    lblInfo.setText(resultado);
                }
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