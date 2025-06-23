
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import BLL.Producto;
import DLL.ControllerProducto;
import repository.Local;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EditarProducto extends JDialog {

    public EditarProducto(JFrame parent, Producto producto) {
        super(parent, "Editar Producto", true);
        
        setSize(500, 350);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(222, 221, 218));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(222, 221, 218));

        JTextField nombreField = new JTextField(producto.getNombre());
        JTextField precioField = new JTextField(String.valueOf(producto.getPrecio()));
        JTextField stockField = new JTextField(String.valueOf(producto.getStock()));
        
        JComboBox<Local> sucursalCombo = new JComboBox<>(Local.values());
        sucursalCombo.setSelectedIndex(producto.getid_sucursal() - 1);

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Precio:"));
        formPanel.add(precioField);
        formPanel.add(new JLabel("Stock:"));
        formPanel.add(stockField);
        formPanel.add(new JLabel("Sucursal:"));
        formPanel.add(sucursalCombo);

        JLabel lblError = new JLabel();
        lblError.setForeground(Color.RED);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(222, 221, 218));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(192, 57, 43));
        btnCancelar.addActionListener(e -> dispose());
        
        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(63, 192, 108));
        btnGuardar.addActionListener(e -> {
            try {
                producto.setNombre(nombreField.getText().trim());
                producto.setPrecio(Double.parseDouble(precioField.getText()));
                producto.setStock(Integer.parseInt(stockField.getText()));
                producto.setid_sucursal(((Local)sucursalCombo.getSelectedItem()).getId());
                
                if (ControllerProducto.actualizarProducto(producto)) {
                    dispose(); // Cierra solo el diálogo
                } else {
                    lblError.setText("Error al actualizar el producto");
                }
            } catch (NumberFormatException ex) {
                lblError.setText("Precio y Stock deben ser números válidos");
            }
        });

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnGuardar);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(lblError, BorderLayout.NORTH);
        
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}
