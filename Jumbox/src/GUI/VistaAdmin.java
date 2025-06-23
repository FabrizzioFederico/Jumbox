package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Usuario;
import DLL.ControllerUsuario;
import repository.Rol;
import repository.Validaciones;
import repository.Local;

import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaAdmin extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Usuario usuarioSeleccionado;
    private JTextField textField;

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

    public VistaAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 580);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(222, 221, 218));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        model = new DefaultTableModel(
        		new String[]{"ID", 
        				"Nombre",
        				"Email",
        				"Contraseña",
        				"Dirección",
        				"Sucursal",
        				"Rol"
        				}, 0);
        				// Contraseña por si lo agrego despues
        
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setBounds(10, 40, 760, 200);
        scrollPane.setBounds(34, 58, 1006, 290);
        contentPane.add(scrollPane);
        
        
        
        

        // Panel superior
        JPanel panel = new JPanel();
        panel.setBackground(new Color(63, 192, 108));
        panel.setBounds(0, 0, 1078, 161);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblPanelDeAdministrador = new JLabel("Panel de Administrador - Jumbox");
        lblPanelDeAdministrador.setForeground(Color.WHITE);
        lblPanelDeAdministrador.setFont(new Font("Montserrat", Font.BOLD, 18));
        lblPanelDeAdministrador.setBounds(42, 15, 374, 27);
        panel.add(lblPanelDeAdministrador);
        
        // Panel de acciones
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(34, 376, 453, 145);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Acciones");
        lblNewLabel.setForeground(new Color(63, 192, 108));
        lblNewLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        lblNewLabel.setBounds(37, 24, 136, 13);
        panel_1.add(lblNewLabel);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setBackground(new Color(63, 192, 108));
        btnAgregar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnAgregar.setBounds(37, 88, 110, 27);
        panel_1.add(btnAgregar);
        
        JButton btnEditar = new JButton("Editar");
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBackground(new Color(63, 192, 108));
        btnEditar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnEditar.setBounds(167, 88, 99, 27);
        panel_1.add(btnEditar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(63, 192, 108));
        btnEliminar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnEliminar.setBounds(284, 88, 115, 27);
        panel_1.add(btnEliminar);
        
        JLabel lblSeleccioneUnUsuario = new JLabel("Seleccione un usuario de la tabla y una acción a realizar:");
        lblSeleccioneUnUsuario.setFont(new Font("Montserrat", Font.PLAIN, 12));
        lblSeleccioneUnUsuario.setBounds(37, 48, 370, 17);
        panel_1.add(lblSeleccioneUnUsuario);
        
        // Panel de filtrado
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(517, 376, 523, 145);
        contentPane.add(panel_2);
        panel_2.setLayout(null);
        
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setBackground(new Color(63, 192, 108));
        btnFiltrar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnFiltrar.setBounds(263, 87, 96, 27);
        panel_2.add(btnFiltrar);
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setBackground(new Color(63, 192, 108));
        btnLimpiar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnLimpiar.setBounds(374, 87, 102, 27);
        panel_2.add(btnLimpiar);
        
        textField = new JTextField();
        textField.setBounds(36, 87, 206, 27);
        panel_2.add(textField);
        textField.setColumns(10);
        
        JLabel lblFiltrarResultados = new JLabel("Filtrar Resultados");
        lblFiltrarResultados.setForeground(new Color(63, 192, 108));
        lblFiltrarResultados.setFont(new Font("Montserrat", Font.BOLD, 16));
        lblFiltrarResultados.setBounds(35, 23, 176, 17);
        panel_2.add(lblFiltrarResultados);
        
        JLabel lblFiltreLosResultados = new JLabel("Filtre los resultados por letras, palabras, números, etc:");
        lblFiltreLosResultados.setFont(new Font("Montserrat", Font.PLAIN, 12));
        lblFiltreLosResultados.setBounds(36, 50, 436, 17);
        panel_2.add(lblFiltreLosResultados);

        // Listeners
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    usuarioSeleccionado = new Usuario(
                        (int) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        (String) model.getValueAt(row, 2),
                        (String) model.getValueAt(row, 3),
                        (String) model.getValueAt(row, 4),
                        (int) model.getValueAt(row, 5),
                        (String) model.getValueAt(row, 6)
                    );
                }
            }
        });

        btnAgregar.addActionListener(e -> mostrarDialogoAgregarUsuario());
        btnEditar.addActionListener(e -> editarUsuarioSeleccionado());
        btnEliminar.addActionListener(e -> eliminarUsuarioSeleccionado());
        btnFiltrar.addActionListener(e -> cargarTablaFILTRAR(textField.getText()));
        btnLimpiar.addActionListener(e -> {
            cargarTabla();
            textField.setText("");
        });

        // Cargar datos iniciales
        cargarTabla();
    }

    private void mostrarDialogoAgregarUsuario() {
        JDialog dialog = new JDialog(this, "Agregar Usuario", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(222, 221, 218));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(222, 221, 218));

        JTextField nombreField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField direccionField = new JTextField();
        JComboBox<Local> idSucursalCombox = new JComboBox<>(Local.values());
        JComboBox<Rol> rolComboBox = new JComboBox<>(Rol.values());

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(222, 221, 218));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(192, 57, 43));
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(63, 192, 108));
        btnGuardar.addActionListener(e -> {
            if (validarCampos(nombreField, emailField, passwordField, direccionField)) {
                Usuario nuevo = new Usuario(0,
                    nombreField.getText().trim(),
                    emailField.getText().trim(),
                    new String(passwordField.getPassword()),
                    direccionField.getText().trim(),
                    ((Local)idSucursalCombox.getSelectedItem()).getId(),
                    ((Rol) rolComboBox.getSelectedItem()).name()
                );
                ControllerUsuario.Registrarse(nuevo);
                cargarTabla();
                dialog.dispose();
            }
        });

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private boolean validarCampos(JTextField nombreField, JTextField emailField, 
                                JPasswordField passwordField, JTextField direccionField) {
        if (Validaciones.validarNombreSinIngreso(nombreField.getText()) == null) {
            mostrarError("Nombre inválido\nSolo letras y espacios permitidos");
            return false;
        }

        if (Validaciones.validarEmail(emailField.getText()) == null) {
            mostrarError("Email inválido\nFormato: usuario@dominio.com");
            return false;
        }

        if (Validaciones.validarContraseniaSinIngreso(new String(passwordField.getPassword())) == null) {
            mostrarError("Contraseña inválida\nDebe tener:\n- 8+ caracteres\n- 1 mayúscula\n- 1 minúscula\n- 1 número");
            return false;
        }
        
        if (Validaciones.validarAlfanumerico(direccionField.getText()) == null) {
            mostrarError("Dirección Incorrecta");
            return false;
        }
        
        return true;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void editarUsuarioSeleccionado() {
        if (usuarioSeleccionado != null) {
            EditarUsuario editar = new EditarUsuario(usuarioSeleccionado);
            editar.setVisible(true);
            dispose();
        } else {
            mostrarError("Seleccione un usuario.");
        }
    }

    private void eliminarUsuarioSeleccionado() {
        if (usuarioSeleccionado != null) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Eliminar a " + usuarioSeleccionado.getNombre() + "?", 
                "Confirmar", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                ControllerUsuario.Eliminar(usuarioSeleccionado);
                dispose();
                VistaAdmin vistaAdmin = new VistaAdmin();
                vistaAdmin.setVisible(true);
            }
        } else {
            mostrarError("Seleccione un usuario.");
        }
    }

    private void cargarTabla() {
        model.setRowCount(0);
        LinkedList<Usuario> ordenados = ControllerUsuario.mostrarUsuarios().stream()
            .sorted(Comparator.comparingInt(Usuario::getId).reversed())
            .collect(Collectors.toCollection(LinkedList::new));

        for (Usuario usuario : ordenados) {
            model.addRow(new Object[]{
                usuario.getId(), 
                usuario.getNombre(),
                usuario.getEmail(), 
                usuario.getContrasenia(),
                usuario.getDireccion(),
                usuario.getId_sucursal(),
                usuario.getElegido()
            });
        }
    }

    private void cargarTablaFILTRAR(String filtro) {
        model.setRowCount(0);
        LinkedList<Usuario> usuarios = ControllerUsuario.mostrarUsuarios();
        for (Usuario usuario : usuarios) {
            if(usuario.getNombre().toLowerCase().startsWith(filtro.toLowerCase()) ||
               usuario.getNombre().contains(filtro)) {
                model.addRow(new Object[]{
                    usuario.getId(), 
                    usuario.getNombre(),
                    usuario.getEmail(), 
                    usuario.getContrasenia(),
                    usuario.getDireccion(),
                    usuario.getId_sucursal(),
                    usuario.getElegido()
                });
            }
        }
    }
}
