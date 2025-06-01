package GUI;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.Sucursal;
import BLL.Usuario;
import DLL.ControllerUsuario;

import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaUsuarios extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Usuario usuarioSeleccionado;
    private JTextField inpFiltro;

  

    public VistaUsuarios() {
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
        				"Email",
        				"Contraseña",
        				"Dirección",
        				"Sucursal",
        				"Rol",
        				"ID Venta",
        				"ID Venta Producto"
        				}, 0);
        				// Contraseña por si lo agrego despues
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
                    usuarioSeleccionado = new Usuario(
                        (int) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        (String) model.getValueAt(row, 2),
                        (String) model.getValueAt(row, 3),
                        (String) model.getValueAt(row, 4),
                        (int) model.getValueAt(row, 5),
                        (String) model.getValueAt(row, 6),
                        (int) model.getValueAt(row, 7),
                        (int) model.getValueAt(row, 8)
                     
                    );
                    lblSeleccionado.setText("Seleccionado: ID=" + usuarioSeleccionado.getId()
                            + ", Nombre=" + usuarioSeleccionado.getNombre()
                            + ", Email=" + usuarioSeleccionado.getEmail()
                            + ", Contraseña=" + usuarioSeleccionado.getContrasenia()
                            + ", Dirección=" + usuarioSeleccionado.getDireccion() 
                            + ", Sucursal=" + usuarioSeleccionado.getId_sucursal() 
                            + ", Rol=" + usuarioSeleccionado.getElegido() 
                            + ", ID Venta=" + usuarioSeleccionado.getVenta_id_venta()
                            + ", ID Venta Producto=" + usuarioSeleccionado.getVenta_VentaProducto_id_venta()
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

        // Acción: Agregar usuario
        btnAgregar.addActionListener(e -> {
            JTextField nombreField = new JTextField();
            JTextField emailField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField direccionField = new JTextField();
            JTextField idSucursalField = new JTextField();
            JTextField elegidoField = new JTextField();
            JTextField ventaIdVentaField = new JTextField();
            JTextField ventaVentaProductoIdVenta = new JTextField();
            //Aca Agregar cosas

            Object[] fields = {
                "Nombre:", nombreField,
                "Email:", emailField,
                "Contraseña:", passwordField,
                "Dirección", direccionField,
                "Sucursal", idSucursalField,
                "Rol", elegidoField,
                //"ID Venta", ventaIdVentaField,
                //"Id Venta Producto", ventaVentaProductoIdVenta
            //Aca Agregar cosas
            };

            int option = JOptionPane.showConfirmDialog(null, fields, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                Usuario nuevo = new Usuario(0,
                        nombreField.getText(),
                        emailField.getText(),
                        new String(passwordField.getPassword()),
                        direccionField.getText(),
                        Integer.parseInt(idSucursalField.getText()),
                        elegidoField.getText(),
                        0,0
                        // Aca Agregar cosas
                );

                ControllerUsuario.Registrarse(nuevo);
                cargarTabla();
            }
        });

        // Acción: Editar usuario
        btnEditar.addActionListener(e -> {
            if (usuarioSeleccionado != null) {

            	EditarUsuario editar = new EditarUsuario(usuarioSeleccionado);
            	editar.setVisible(true);
            	dispose();
            	
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
            }
        });

        // Acción: Eliminar usuario
        btnEliminar.addActionListener(e -> {
            if (usuarioSeleccionado != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar a " + usuarioSeleccionado.getNombre() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                	ControllerUsuario.Eliminar(usuarioSeleccionado);
                	dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario.");
            }
        });
    }

    private void cargarTabla() {
        model.setRowCount(0);
        //Alfabeticamente de Z a A
       
//        LinkedList<Usuario> ordenados = DLLUsuario.mostrarUsuarios().stream()
//        	    .filter(usuario -> usuario.getNombre() != null && !usuario.getNombre().isEmpty())
//        	    .sorted(Comparator.comparing(Usuario::getNombre, String.CASE_INSENSITIVE_ORDER).reversed()) //sacan esto para que sea de A a la Z
//        	    .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Usuario>ordenados = ControllerUsuario.mostrarUsuarios().stream()
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
            		usuario.getElegido(),
            		usuario.getVenta_id_venta(),
            		usuario.getVenta_VentaProducto_id_venta()
            		}
            );
    		
        	}
    }
    private void cargarTablaFILTRAR(String filtro) {
        model.setRowCount(0);
        LinkedList<Usuario> usuarios = ControllerUsuario.mostrarUsuarios();
        for (Usuario usuario : usuarios) {
        	if(usuario.getNombre().toLowerCase().startsWith(
        			filtro.toLowerCase())
        			||
        			usuario.getNombre().contains(filtro)) {
            model.addRow(new Object[]{
            		usuario.getId(), 
            		usuario.getNombre(),
            		usuario.getEmail(), 
            		usuario.getContrasenia(),
            		usuario.getDireccion(),
            		usuario.getId_sucursal(),
            		usuario.getElegido(),
            		usuario.getVenta_id_venta(),
            		usuario.getVenta_VentaProducto_id_venta()
            		}
            );
        	}
        }
    }
    
    private void cargarTablaFILTRARStream(String filtro) {
        model.setRowCount(0);
        
        LinkedList<Usuario> filtradasPorLetra = ControllerUsuario.mostrarUsuarios().stream()
        	    .filter(usuario -> usuario.getNombre() != null && usuario.getNombre().startsWith(filtro))
        	    .collect(Collectors.toCollection(LinkedList::new));

        for (Usuario usuario : filtradasPorLetra) {
        	
            model.addRow(new Object[]{
            		usuario.getId(), 
            		usuario.getNombre(),
            		usuario.getEmail(), 
            		usuario.getContrasenia(),
            		usuario.getDireccion(),
            		usuario.getId_sucursal(),
            		usuario.getElegido(),
            		usuario.getVenta_id_venta(),
            		usuario.getVenta_VentaProducto_id_venta()
            		}
            );
        	
        }
    }
}
