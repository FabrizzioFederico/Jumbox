package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Producto;
import DLL.ControllerProducto;
import repository.Local;

import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaProducto extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Producto productoSeleccionado;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaProducto frame = new VistaProducto();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VistaProducto() {
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
        				"Precio",
        				"Stock",
        				"Sucursal"
        				},0);
        				
        
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
        
        JLabel lblPanelDeAdministrador = new JLabel("Panel de Productos - Jumbox");
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
        
        JLabel lblSeleccioneUnProducto = new JLabel("Seleccione un producto de la tabla y una acción a realizar:");
        lblSeleccioneUnProducto.setFont(new Font("Montserrat", Font.PLAIN, 12));
        lblSeleccioneUnProducto.setBounds(37, 48, 370, 17);
        panel_1.add(lblSeleccioneUnProducto);
        
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
                    productoSeleccionado = new Producto(
                        (int) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        (double) model.getValueAt(row, 2),
                        (int) model.getValueAt(row, 3),
                        (int) model.getValueAt(row, 4)
                    );
                }
            }
        });

        btnAgregar.addActionListener(e -> mostrarDialogoAgregarProducto());
        btnEditar.addActionListener(e -> editarProductoSeleccionado());
        btnEliminar.addActionListener(e -> eliminarProductoSeleccionado());
        btnFiltrar.addActionListener(e -> cargarTablaFILTRAR(textField.getText()));
        btnLimpiar.addActionListener(e -> {
            cargarTabla();
            textField.setText("");
        });

        // Cargar datos iniciales
        cargarTabla();
    }

    private void mostrarDialogoAgregarProducto() {
        JDialog dialog = new JDialog(this, "Agregar Producto", true);
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(222, 221, 218));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(222, 221, 218));

        JTextField nombreField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField stockField = new JTextField();
        JComboBox<Local> idSucursalCombox = new JComboBox<>(Local.values());

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Precio:"));
        formPanel.add(precioField);
        formPanel.add(new JLabel("Stock:"));
        formPanel.add(stockField);
        formPanel.add(new JLabel("Sucursal:"));
        formPanel.add(idSucursalCombox);

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
            try {
                Producto nuevo = new Producto(0,
                    nombreField.getText().trim(),
                    Double.parseDouble(precioField.getText()),
                    Integer.parseInt(stockField.getText()),
                    ((Local)idSucursalCombox.getSelectedItem()).getId()
                );

                ControllerProducto.RegistrarProducto(nuevo);
                cargarTabla();
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Precio y Stock deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void editarProductoSeleccionado() {
        if (productoSeleccionado != null) {
            EditarProducto dialog = new EditarProducto(this, productoSeleccionado);
            dialog.setVisible(true);
            cargarTabla(); // Actualizar la tabla después de editar
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void eliminarProductoSeleccionado() {
        if (productoSeleccionado != null) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Eliminar el producto " + productoSeleccionado.getNombre() + "?", 
                "Confirmar", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                ControllerProducto.eliminarProducto(productoSeleccionado);
                cargarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cargarTabla() {
        model.setRowCount(0);
        LinkedList<Producto> ordenados = ControllerProducto.mostrarProductos().stream()
            .sorted(Comparator.comparingInt(Producto::getId))
            .collect(Collectors.toCollection(LinkedList::new));

        for (Producto producto : ordenados) {
            model.addRow(new Object[]{
                producto.getId(), 
                producto.getNombre(),
                producto.getPrecio(), 
                producto.getStock(),
                producto.getid_sucursal()
            });
        }
    }

    private void cargarTablaFILTRAR(String filtro) {
        model.setRowCount(0);
        LinkedList<Producto> productos = ControllerProducto.mostrarProductos();
        for (Producto producto : productos) {
            if(producto.getNombre().toLowerCase().startsWith(filtro.toLowerCase()) ||
               producto.getNombre().contains(filtro)) {
                model.addRow(new Object[]{
                    producto.getId(), 
                    producto.getNombre(),
                    producto.getPrecio(), 
                    producto.getStock(),
                    producto.getid_sucursal()
                });
            }
        }
    }
}
