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
import DLL.ControllerUsuario;
import repository.Local;
import repository.Rol;
import repository.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EditarProducto extends JDialog {
	
	private VistaProducto parentFrame;

    public EditarProducto(VistaProducto parent, Producto producto) {
        super(parent, "Editar Producto", true);
        this.parentFrame = parent;
        
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

        JLabel lblInfo = new JLabel();
        lblInfo.setForeground(Color.RED);
        
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
            if (validarCampos(nombreField, precioField, stockField, lblInfo)) {
                producto.setNombre(nombreField.getText().trim());
                producto.setPrecio(Double.parseDouble(precioField.getText().trim()));
                producto.setStock(Integer.parseInt(stockField.getText().trim()));
                producto.setid_sucursal(((Local)sucursalCombo.getSelectedItem()).getId());
                
                String resultado = ControllerProducto.actualizarProducto(producto);
                if (resultado.equals("Producto editado correctamente.")) {
                    parentFrame.cargarTabla();
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
    
    private boolean validarCampos(JTextField nombreField, JTextField precioField, 
    							  JTextField stockField, JLabel lblError) {
    	if (Validaciones.validarNombreSinIngreso(nombreField.getText()) == null) {
    		lblError.setText("Nombre inválido: Solo letras y espacios permitidos");
    		return false;
    	}

    	if (Validaciones.validarDinero(precioField.getText()) == null) {
    		lblError.setText("Dinero inválido o vacío");
    		return false;
    	}

    	if (Validaciones.validarNumeroPositivo(stockField.getText()) < 0) {
    		lblError.setText("Stock inválido o vacío");
    		return false;
    	}
    	
    	lblError.setText("");
    	return true;
    	}
}