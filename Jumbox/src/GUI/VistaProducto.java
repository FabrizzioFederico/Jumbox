package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Producto;
import BLL.Usuario;
import DLL.ControllerProducto;
import java.awt.BorderLayout;

public class VistaProducto extends JFrame {
	public VistaProducto() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 434, 261);
		getContentPane().add(lblNewLabel_1);
	}

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Producto productoSeleccionado;
    private JTextField inpFiltro;

  

    public VistaProducto() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 10, 760, 20);
        contentPane.add(lblSeleccionado);

        model = new DefaultTableModel(
        		new String[]{"ID", 
        				"Nombre",
        				"Precio",
        				"Stock",
        				"Contraseña"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 760, 200);
        contentPane.add(scrollPane);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 270, 150, 40);
        contentPane.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(170, 270, 150, 40);
        contentPane.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(330, 270, 150, 40);
        contentPane.add(btnEliminar);

        // Acción al seleccionar fila
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    productoSeleccionado = new Producto(
                        (int) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        (double) model.getValueAt(row, 2),
                        (int) model.getValueAt(row, 3),
                        (int) model.getValueAt(row, 4),
                        (int) model.getValueAt(row, 5));
                    
                    lblSeleccionado.setText("Seleccionado: ID=" + productoSeleccionado.getId()
                            + ", Nombre=" + productoSeleccionado.getNombre()
                            + ", Precio=" + productoSeleccionado.getPrecio()
                            + ", Stock=" + productoSeleccionado.getStock()
                            + ", Id_Sucursal=" + productoSeleccionado.getid_sucursal()
                            + ", VentaProducto_id_venta=" + productoSeleccionado.getVentaProducto_id_venta()
                            );
                    
                   
                }
            }
        });
        inpFiltro = new JTextField();
        inpFiltro.setBounds(560, 273, 100, 34);
        contentPane.add(inpFiltro);
        inpFiltro.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("criterio");
        lblNewLabel.setBounds(560, 251, 46, 14);
        contentPane.add(lblNewLabel);
        
        JButton btnfiltrar = new JButton("filtrar");
        btnfiltrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		cargarTablaFILTRAR(inpFiltro.getText());
        		
        	}
        });
        btnfiltrar.setBounds(560, 331, 111, 40);
        contentPane.add(btnfiltrar);
        
        JButton btnLimpia = new JButton("Limpiar");
        btnLimpia.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  cargarTabla();
        		  inpFiltro.setText("");
        	}
        });
        btnLimpia.setBounds(560, 384, 111, 40);
        contentPane.add(btnLimpia);
        // Cargar datos
        cargarTabla();

        // Acción: Agregar producto
        btnAgregar.addActionListener(e -> {
            JTextField nombreField = new JTextField();
            JTextField precioField = new JTextField();
            JTextField stockField = new JTextField();
            JTextField id_sucursalField = new JTextField();
            JTextField id_venta_productoField = new JTextField();

            Object[] fields = {
                "Nombre:", nombreField,
                "Precio:", precioField,
                "Stock:", stockField,
                "Id_sucursal:", id_sucursalField,
                "Id_venta_producto:", id_venta_productoField
               	};

            int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                Producto nuevo = new Producto(0,
                        nombreField.getText(),
                        precioField.getText(),
                        stockField.getText(),
                        id_sucursalField.getText(),
                        0);

                DLLControllerProducto.agregarProducto(nuevo);
                cargarTabla();
            }
        });

        // Acción: Editar producto
        btnEditar.addActionListener(e -> {
            if (productoSeleccionado != null) {

            	EditarUsuario editar = new EditarUsuario(usuarioSeleccionado);
            	editar.setVisible(true);
            	dispose();
            	
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
            }
        });

        // Acción: Eliminar producto
        btnEliminar.addActionListener(e -> {
            if (productoSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar a " + productoSeleccionado.getNombre() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Asumimos que hay un método DLLProducto.eliminarProducto(id)
                    JOptionPane.showMessageDialog(null, "Función de eliminación aún no implementada.");
                    // DLLProducto.eliminarProducto(productoSeleccionado.getId());
                    cargarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un producto.");
            }
        });
    }

    private void cargarTabla() {
        model.setRowCount(0);
        //Alfabeticamente de Z a A
       
//        LinkedList<Producto> ordenados = DLLControllerProducto.mostrarProductos().stream()
//        	    .filter(producto -> producto.getNombre() != null && !producto.getNombre().isEmpty())
//        	    .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Producto>ordenados = DLLControllerProducto.mostrarProductos().stream()
        	    .sorted(Comparator.comparingInt(Producto::getId).reversed())
        	    .collect(Collectors.toCollection(LinkedList::new));

        for (Producto producto : ordenados) {
            model.addRow(new Object[]{
            		producto.getId(), 
            		producto.getNombre(),
            		producto.getPrecio(), 
            		producto.getStock(),
            		producto.getid_sucursal(),
            		producto.getVentaProducto_id_venta());
            }
        	
    }
    private void cargarTablaFILTRAR(String filtro) {
        model.setRowCount(0);
        LinkedList<Producto> productos = DLLControllerProducto.mostrarProductos();
        for (Producto producto : productos) {
        	if(producto.getNombre().toLowerCase().startsWith(
        			filtro.toLowerCase())
        			||
        			producto.getNombre().contains(filtro)) {
            model.addRow(new Object[]{
            		producto.getId(), 
            		producto.getNombre(),
            		producto.getPrecio(), 
            		producto.getStock(),
            		producto.getid_sucursal(),
            		producto.getVentaProducto_id_venta()
            		}
            );
        	}
        }
    }
    
    private void cargarTablaFILTRARStream(String filtro) {
        model.setRowCount(0);
        
        LinkedList<Producto> filtradasPorLetra = DLLControllerProducto.mostrarProductos().stream()
        	    .filter(producto -> producto.getNombre() != null && producto.getNombre().startsWith(filtro))
        	    .collect(Collectors.toCollection(LinkedList::new));

        for (Producto producto : filtradasPorLetra) {
        	
            model.addRow(new Object[]{
            		producto.getId(), 
            		producto.getNombre(),
            		producto.getPrecio(), 
            		producto.getStock(),
            		producto.getid_sucursal(),
            		producto.getVentaProducto_id_venta()
            		}
            );
        	
        }
    }
}
