package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import DLL.ControllerProducto;
import repository.Local;


public class VistaProducto extends JFrame {
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
        				"Id_sucursal"
        				}, 0);
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
                        (int) model.getValueAt(row, 4)
                        );
                    
                    lblSeleccionado.setText("Seleccionado: ID=" + productoSeleccionado.getId()
                            + ", Nombre=" + productoSeleccionado.getNombre()
                            + ", Precio=" + productoSeleccionado.getPrecio()
                            + ", Stock=" + productoSeleccionado.getStock()
                            + ", Id_Sucursal=" + productoSeleccionado.getid_sucursal()
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
            JComboBox<Local> idSucursalCombox = new JComboBox<>(Local.values());

            Object[] fields = {
                "Nombre:", nombreField,
                "Precio:", precioField,
                "Stock:", stockField,
                "Id_sucursal:", idSucursalCombox
               	};

            int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Producto", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                Producto nuevo = new Producto(0,
                        nombreField.getText(),
                        Double.parseDouble(precioField.getText()),
                        Integer.parseInt(stockField.getText()),
                        ((Local)idSucursalCombox.getSelectedItem()).getId()
                        );

                ControllerProducto.RegistrarProducto(nuevo);
                cargarTabla();
            }
        });

        // Acción: Editar producto
        btnEditar.addActionListener(e -> {
            if (productoSeleccionado != null) {

            	EditarProducto editar = new EditarProducto(productoSeleccionado);
            	editar.setVisible(true);
            	dispose();
            	
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un producto.");
            }
        });

        // Acción: Eliminar producto
        btnEliminar.addActionListener(e -> {
            if (productoSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar a " + productoSeleccionado.getNombre() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ControllerProducto.eliminarProducto(productoSeleccionado);
                	dispose();
                	VistaProducto vistaProducto = new VistaProducto();
                    vistaProducto.setVisible(true);
                    cargarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un producto.");
            }
        });
    }

    private void cargarTabla() {
        model.setRowCount(0);
   
        LinkedList<Producto>ordenados = ControllerProducto.mostrarProductos().stream()
        	    .sorted(Comparator.comparingInt(Producto::getId))
        	    .collect(Collectors.toCollection(LinkedList::new));

        for (Producto producto : ordenados) {
            model.addRow(new Object[]{
            		producto.getId(), 
            		producto.getNombre(),
            		producto.getPrecio(), 
            		producto.getStock(),
            		producto.getid_sucursal()
            		}
            		);
            }
    }
    private void cargarTablaFILTRAR(String filtro) {
        model.setRowCount(0);
        LinkedList<Producto> productos = ControllerProducto.mostrarProductos();
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
            		producto.getid_sucursal()
            		}
            );
        	}
        }
    }
    
    private void cargarTablaFILTRARStream(String filtro) {
        model.setRowCount(0);
        
        LinkedList<Producto> filtradasPorLetra = ControllerProducto.mostrarProductos().stream()
        	    .filter(producto -> producto.getNombre() != null && producto.getNombre().startsWith(filtro))
        	    .collect(Collectors.toCollection(LinkedList::new));

        for (Producto producto : filtradasPorLetra) {
        	
            model.addRow(new Object[]{
            		producto.getId(), 
            		producto.getNombre(),
            		producto.getPrecio(), 
            		producto.getStock(),
            		producto.getid_sucursal()
            		}
            );
        	
        }
    }
}
