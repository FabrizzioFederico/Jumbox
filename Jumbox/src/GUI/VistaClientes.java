
//
//public class VistaClientes extends JFrame {
//	 private JPanel contentPane;
//	    private JTable table;
//	    private DefaultTableModel model;
//	    private Producto productoSeleccionado;
//	    private JTextField inpFiltro;
//	    private DefaultTableModel modelCarrito;
//	    private JTable tableCarrito;
//	    private LinkedList<VentaProducto> carrito = new LinkedList<>();
//	    private Usuario usuarioActual;
//
// public VistaClientes(Usuario usuario) {
//	 this.usuarioActual = usuario;
//     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     setBounds(100, 100, 885, 567);
//     contentPane = new JPanel();
//     contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
//     setContentPane(contentPane);
//     contentPane.setLayout(null);
//
//     JLabel lblSeleccionado = new JLabel("Seleccionado:");
//     lblSeleccionado.setBounds(10, 10, 760, 20);
//     contentPane.add(lblSeleccionado);
//
//     model = new DefaultTableModel(
//     		new String[]{"ID",
//     				"Nombre",
//     				"Precio",
//     				"Cantidad",
//     				"Sucursal"
//     				}, 0);
//     table = new JTable(model);
//     JScrollPane scrollPane = new JScrollPane(table);
//     scrollPane.setBounds(10, 40, 760, 200);
//     contentPane.add(scrollPane);
//
//     // Acción al seleccionar fila
//     table.getSelectionModel().addListSelectionListener(e -> {
//         if (!e.getValueIsAdjusting()) {
//             int row = table.getSelectedRow();
//             if (row != -1) {
//                 productoSeleccionado = new Producto(
//                     (int) model.getValueAt(row, 0),
//                     (String) model.getValueAt(row, 1),
//                     (double) model.getValueAt(row, 2),
//                     (int) model.getValueAt(row, 3),
//                     (int) model.getValueAt(row, 4));
//                 
//                 lblSeleccionado.setText("Seleccionado: ID=" + productoSeleccionado.getId()
//                         + ", Nombre=" + productoSeleccionado.getNombre()
//                         + ", Precio=" + productoSeleccionado.getPrecio()
//                         + ", Stock=" + productoSeleccionado.getStock()
//                         + ", Id_Sucursal=" + productoSeleccionado.getid_sucursal()
//                         );
//                 
//                
//             }
//         }
//     });
//     // Cargar datos
//     cargarTabla();
//     initCarrito();
// }
// 
// private void initCarrito() {
//     // Modelo para la tabla del carrito
//     modelCarrito = new DefaultTableModel(
//         new String[]{"ID Producto", "Nombre", "Precio Unit.", "Cantidad", "Subtotal"}, 0);
//     
//     tableCarrito = new JTable(modelCarrito);
//     JScrollPane scrollCarrito = new JScrollPane(tableCarrito);
//     scrollCarrito.setBounds(10, 350, 760, 100);
//     contentPane.add(scrollCarrito);
//     
//     JButton btnAgregarAlCarrito = new JButton("Agregar al Carrito");
//     btnAgregarAlCarrito.setBounds(10, 270, 150, 40);
//     btnAgregarAlCarrito.addActionListener(e -> agregarAlCarrito());
//     contentPane.add(btnAgregarAlCarrito);
//     
//     JButton btnFinalizarCompra = new JButton("Finalizar Compra");
//     btnFinalizarCompra.setBounds(178, 270, 150, 40);
//     btnFinalizarCompra.addActionListener(e -> finalizarCompra());
//     contentPane.add(btnFinalizarCompra);
//     
//    
// }
// 
// 
// private void agregarAlCarrito() {
//	    int row = table.getSelectedRow();
//	    if (row == -1) {
//	        JOptionPane.showMessageDialog(this, "Seleccione un producto primero");
//	        return;
//	    }
//
//	    int stockDisponible = (int) model.getValueAt(row, 3); // Columna "Cantidad"
//	    if (stockDisponible == 0) {
//	        JOptionPane.showMessageDialog(this, "Este producto no tiene stock disponible.");
//	        return;
//	    }
//
//	    // Pedir cantidad
//	    String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese cantidad:");
//	    if (cantidadStr == null || cantidadStr.trim().isEmpty()) return;
//
//	    try {
//	        int cantidad = Integer.parseInt(cantidadStr);
//
//	        if (cantidad <= 0) {
//	            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
//	            return;
//	        }
//
//	        if (cantidad > stockDisponible) {
//	            JOptionPane.showMessageDialog(this, "No hay suficiente stock. Stock disponible: " + stockDisponible);
//	            return;
//	        }
//
//	        int idProducto = (int) model.getValueAt(row, 0);
//	        String nombre = (String) model.getValueAt(row, 1);
//	        double precio = (double) model.getValueAt(row, 2);
//
//	        // Verificar si ya está en el carrito
//	        for (VentaProducto vp : carrito) {
//	            if (vp.getId_producto() == idProducto) {
//	                if (vp.getCantidad() + cantidad > stockDisponible) {
//	                    JOptionPane.showMessageDialog(this, "No hay suficiente stock para aumentar esa cantidad. Stock disponible: " + stockDisponible);
//	                    return;
//	                }
//	                vp.setCantidad(vp.getCantidad() + cantidad);
//	                actualizarTablaCarrito();
//	                return;
//	            }
//	        }
//
//	        // Si no está, agregarlo
//	        VentaProducto nuevo = new VentaProducto(0, idProducto, cantidad, precio);
//	        carrito.add(nuevo);
//	        actualizarTablaCarrito();
//
//	    } catch (NumberFormatException e) {
//	        JOptionPane.showMessageDialog(this, "Cantidad inválida");
//	    }
//	}
// 
// 
// private String obtenerNombreProducto(int idProducto) {
//	    // Busca en la tabla de productos que ya tienes cargada
//	    for (int i = 0; i < model.getRowCount(); i++) {
//	        if ((int)model.getValueAt(i, 0) == idProducto) {
//	            return (String)model.getValueAt(i, 1); // Retorna el nombre
//	        }
//	    }
//	    return "Producto no encontrado";
//	}
// 
// private void actualizarTablaCarrito() {
//     modelCarrito.setRowCount(0);
//     for (VentaProducto vp : carrito) {
//         // Necesitarás obtener el nombre del producto (puedes guardarlo en VentaProducto o buscarlo)
//         String nombreProducto = obtenerNombreProducto(vp.getId_producto());
//         double subtotal = vp.getCantidad() * vp.getPrecio_unitario();
//         
//         modelCarrito.addRow(new Object[]{
//             vp.getId_producto(),
//             nombreProducto,
//             vp.getPrecio_unitario(),
//             vp.getCantidad(),
//             subtotal
//         });
//     }
// }
// 
// private void finalizarCompra() {
//	    if (carrito.isEmpty()) {
//	        JOptionPane.showMessageDialog(this, "El carrito está vacío");
//	        return;
//	    }
//
//	    for (VentaProducto vp : carrito) {
//	        Producto p = ControllerProducto.buscarProducto(vp.getId_producto());
//	        if (p == null) {
//	            JOptionPane.showMessageDialog(this, "Producto no encontrado (ID: " + vp.getId_producto() + ")");
//	            return; 
//	        }
//	        if (p.getStock() < vp.getCantidad()) {
//	            JOptionPane.showMessageDialog(this, "Stock insuficiente para el producto: " + p.getNombre());
//	            return; 
//	        }
//	    }
//
//	    int idVenta = ControllerVenta.crearVenta(usuarioActual.getId(), usuarioActual.getId_sucursal(), calcularTotal());
//	    if (idVenta <= 0) {
//	        JOptionPane.showMessageDialog(this, "Error al crear la venta.");
//	        return;
//	    }
//
//	    boolean errorStock = false;
//
//	    for (VentaProducto vp : carrito) {
//	        Producto p = ControllerProducto.buscarProducto(vp.getId_producto());
//
//	        if (p.getStock() < vp.getCantidad()) {
//	            JOptionPane.showMessageDialog(this, "Stock insuficiente para el producto (al confirmar): " + p.getNombre());
//	            errorStock = true;
//	            break;
//	        }
//
//	        boolean actualizado = ControllerProducto.reducirStock(p.getId(), vp.getCantidad());
//	        if (!actualizado) {
//	            JOptionPane.showMessageDialog(this, "Error al actualizar stock para: " + p.getNombre());
//	            errorStock = true;
//	            break;
//	        }
//
//	        vp.setId_venta(idVenta);
//	        ControllerVenta.registrarProductoEnVenta(vp);
//	    }
//
//	    if (errorStock) {
//	        JOptionPane.showMessageDialog(this, "No se pudo completar la compra debido a problemas con el stock.");
//
//	        return;
//	    }
//
//	    JOptionPane.showMessageDialog(this, "Venta realizada con éxito!");
//	    
//	    mostrarTicket();
//	    
//	    carrito.clear();
//	    actualizarTablaCarrito();
//	    cargarTabla(); 
//	}
//
// 
// private double calcularTotal() {
//     double total = 0;
//     for (VentaProducto vp : carrito) {
//         total += vp.getCantidad() * vp.getPrecio_unitario();
//     }
//     return total;
// }
// 
// private void mostrarTicket() {
//	    StringBuilder detalle = new StringBuilder("DETALLE DE COMPRA\n");
//	    detalle.append("===========================\n");
//
//	    for (VentaProducto vp : carrito) {
//	        String nombreProducto = obtenerNombreProducto(vp.getId_producto());
//	        double subtotal = vp.getCantidad() * vp.getPrecio_unitario();
//
//	        detalle.append(String.format("%s x%d $%.2f -> Subtotal: $%.2f\n",
//	                nombreProducto, vp.getCantidad(), vp.getPrecio_unitario(), subtotal));
//	    }
//
//	    detalle.append("===========================\n");
//	    detalle.append(String.format("TOTAL A PAGAR: $%.2f", calcularTotal()));
//
//	    JOptionPane.showMessageDialog(this, detalle.toString(), "Resumen de compra", JOptionPane.INFORMATION_MESSAGE);
//	}
//
//
// private void cargarTabla() {
//     model.setRowCount(0);
//
//     LinkedList<Producto>ordenados = ControllerProducto.mostrarProductos().stream()
//     	    .sorted(Comparator.comparingInt(Producto::getId))
//     	    .collect(Collectors.toCollection(LinkedList::new));
//
//     for (Producto producto : ordenados) {
//         model.addRow(new Object[]{
//         		producto.getId(), 
//         		producto.getNombre(),
//         		producto.getPrecio(), 
//         		producto.getStock(),
//         		producto.getid_sucursal()}
//         		);
//         }
// }
// private void cargarTablaFILTRAR(String filtro) {
//     model.setRowCount(0);
//     LinkedList<Producto> productos = ControllerProducto.mostrarProductos();
//     for (Producto producto : productos) {
//     	if(producto.getNombre().toLowerCase().startsWith(
//     			filtro.toLowerCase())
//     			||
//     			producto.getNombre().contains(filtro)) {
//         model.addRow(new Object[]{
//         		producto.getId(), 
//         		producto.getNombre(),
//         		producto.getPrecio(), 
//         		producto.getStock(),
//         		producto.getid_sucursal()
//         		}
//         );
//     	}
//     }
// }
// 
// private void cargarTablaFILTRARStream(String filtro) {
//     model.setRowCount(0);
//     
//     LinkedList<Producto> filtradasPorLetra = ControllerProducto.mostrarProductos().stream()
//     	    .filter(producto -> producto.getNombre() != null && producto.getNombre().startsWith(filtro))
//     	    .collect(Collectors.toCollection(LinkedList::new));
//
//     for (Producto producto : filtradasPorLetra) {
//     	
//         model.addRow(new Object[]{
//         		producto.getId(), 
//         		producto.getNombre(),
//         		producto.getPrecio(), 
//         		producto.getStock(),
//         		producto.getid_sucursal()}
//         );
//     	
//     }
// }
//}




package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import BLL.Producto;
import BLL.Usuario;
import BLL.VentaProducto;
import DLL.ControllerProducto;
import DLL.ControllerVenta;

import java.awt.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class VistaClientes extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Producto productoSeleccionado;
    private DefaultTableModel modelCarrito;
    private JTable tableCarrito;
    private LinkedList<VentaProducto> carrito = new LinkedList<>();
    private Usuario usuarioActual;
    private JTextField textField;
    private JLabel lblTotalCarrito;

    public VistaClientes(Usuario usuario) {
        this.usuarioActual = usuario;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 750); 
        contentPane = new JPanel();
        contentPane.setBackground(new Color(222, 221, 218));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        model = new DefaultTableModel(
         		new String[]{"ID",
         				"Nombre",
         				"Precio",
         				"Cantidad",
         				"Sucursal"
         				}, 0);
         table = new JTable(model);
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBounds(34, 58, 1006, 200);
         contentPane.add(scrollPane);

        // Panel superior
        JPanel panel = new JPanel();
        panel.setBackground(new Color(63, 192, 108));
        panel.setBounds(0, 0, 1078, 161);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblPanelDeCliente = new JLabel("Panel de Cliente - Jumbox");
        lblPanelDeCliente.setForeground(Color.WHITE);
        lblPanelDeCliente.setFont(new Font("Montserrat", Font.BOLD, 18));
        lblPanelDeCliente.setBounds(42, 15, 374, 27);
        panel.add(lblPanelDeCliente);
        
        // Panel de acciones
        JPanel panelAcciones = new JPanel();
        panelAcciones.setBackground(Color.WHITE);
        panelAcciones.setBounds(34, 280, 453, 145);
        contentPane.add(panelAcciones);
        panelAcciones.setLayout(null);
        
        JLabel lblAcciones = new JLabel("Acciones");
        lblAcciones.setForeground(new Color(63, 192, 108));
        lblAcciones.setFont(new Font("Montserrat", Font.BOLD, 18));
        lblAcciones.setBounds(37, 24, 136, 13);
        panelAcciones.add(lblAcciones);
        
        JButton btnAgregarCarrito = new JButton("Agregar al Carrito");
        btnAgregarCarrito.setForeground(Color.WHITE);
        btnAgregarCarrito.setBackground(new Color(63, 192, 108));
        btnAgregarCarrito.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnAgregarCarrito.setBounds(37, 88, 180, 27);
        panelAcciones.add(btnAgregarCarrito);
        
        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.setForeground(Color.WHITE);
        btnFinalizarCompra.setBackground(new Color(63, 192, 108));
        btnFinalizarCompra.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnFinalizarCompra.setBounds(227, 88, 180, 27);
        panelAcciones.add(btnFinalizarCompra);
        
        JLabel lblSeleccioneProducto = new JLabel("Seleccione productos para agregar al carrito:");
        lblSeleccioneProducto.setFont(new Font("Montserrat", Font.PLAIN, 12));
        lblSeleccioneProducto.setBounds(37, 48, 370, 17);
        panelAcciones.add(lblSeleccioneProducto);

        // Panel de filtrado
        JPanel panelFiltro = new JPanel();
        panelFiltro.setBackground(Color.WHITE);
        panelFiltro.setBounds(517, 280, 523, 145);
        contentPane.add(panelFiltro);
        panelFiltro.setLayout(null);
        
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setBackground(new Color(63, 192, 108));
        btnFiltrar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnFiltrar.setBounds(263, 87, 96, 27);
        panelFiltro.add(btnFiltrar);
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setBackground(new Color(63, 192, 108));
        btnLimpiar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnLimpiar.setBounds(374, 87, 102, 27);
        panelFiltro.add(btnLimpiar);
        
        textField = new JTextField();
        textField.setBounds(36, 87, 206, 27);
        panelFiltro.add(textField);
        textField.setColumns(10);
        
        JLabel lblFiltrarResultados = new JLabel("Filtrar Resultados");
        lblFiltrarResultados.setForeground(new Color(63, 192, 108));
        lblFiltrarResultados.setFont(new Font("Montserrat", Font.BOLD, 16));
        lblFiltrarResultados.setBounds(35, 23, 176, 17);
        panelFiltro.add(lblFiltrarResultados);
        
        JLabel lblFiltreLosResultados = new JLabel("Filtre los resultados por nombre de producto:");
        lblFiltreLosResultados.setFont(new Font("Montserrat", Font.PLAIN, 12));
        lblFiltreLosResultados.setBounds(36, 50, 436, 17);
        panelFiltro.add(lblFiltreLosResultados);

        initCarrito();
        
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

        btnAgregarCarrito.addActionListener(e -> mostrarDialogoAgregarAlCarrito());
        btnFinalizarCompra.addActionListener(e -> finalizarCompra());
        btnFiltrar.addActionListener(e -> cargarTablaFILTRAR(textField.getText()));
        btnLimpiar.addActionListener(e -> {
            cargarTabla();
            textField.setText("");
        });

        cargarTabla();
    }

    private void initCarrito() {        
    	  modelCarrito = new DefaultTableModel(
                new String[]{"ID Producto", "Nombre", "Precio Unit.", "Cantidad", "Subtotal"}, 0);
            
          tableCarrito = new JTable(modelCarrito);
          JScrollPane scrollCarrito = new JScrollPane(tableCarrito);
          scrollCarrito.setBounds(34, 450, 1006, 150);
          contentPane.add(scrollCarrito);
        
        JLabel lblCarrito = new JLabel("Carrito de Compras");
        lblCarrito.setFont(new Font("Montserrat", Font.BOLD, 16));
        lblCarrito.setForeground(new Color(63, 192, 108));
        lblCarrito.setBounds(34, 450, 200, 20);
        contentPane.add(lblCarrito);
        
        lblTotalCarrito = new JLabel("Total: $0.00");
        lblTotalCarrito.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblTotalCarrito.setForeground(new Color(63, 192, 108));
        lblTotalCarrito.setBounds(850, 640, 200, 20);
        contentPane.add(lblTotalCarrito);
    }

    private void mostrarDialogoAgregarAlCarrito() {
        int row = table.getSelectedRow();
        if (row == -1) {
            mostrarError("Seleccione un producto primero");
            return;
        }

        int stockDisponible = (int) model.getValueAt(row, 3);
        if (stockDisponible == 0) {
            mostrarError("Este producto no tiene stock disponible.");
            return;
        }

        JDialog dialog = new JDialog(this, "Agregar al Carrito", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(new Color(222, 221, 218));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(222, 221, 218));

        JLabel lblCantidad = new JLabel("Cantidad (Disponible: " + stockDisponible + "):");
        JTextField txtCantidad = new JTextField("1");
        
        formPanel.add(lblCantidad);
        formPanel.add(txtCantidad);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(222, 221, 218));
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(192, 57, 43));
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setBackground(new Color(63, 192, 108));
        btnAgregar.addActionListener(e -> {
            try {
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                
                if (cantidad <= 0) {
                    mostrarError("La cantidad debe ser mayor a cero");
                    return;
                }
                
                if (cantidad > stockDisponible) {
                    mostrarError("No hay suficiente stock. Disponible: " + stockDisponible);
                    return;
                }
                
                agregarProductoAlCarrito(row, cantidad);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                mostrarError("Ingrese un número válido");
            }
        });

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnAgregar);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void agregarProductoAlCarrito(int row, int cantidad) {
        int idProducto = (int) model.getValueAt(row, 0);
        String nombre = (String) model.getValueAt(row, 1);
        double precio = (double) model.getValueAt(row, 2);
        int stockDisponible = (int) model.getValueAt(row, 3);

        for (VentaProducto vp : carrito) {
            if (vp.getId_producto() == idProducto) {
                int cantidadTotal = vp.getCantidad() + cantidad;
                if (cantidadTotal > stockDisponible) {
                    mostrarError("No hay suficiente stock. Ya tienes " + vp.getCantidad() + 
                                " en el carrito. Stock disponible: " + stockDisponible);
                    return;
                }
                vp.setCantidad(cantidadTotal);
                actualizarTablaCarrito();
                return;
            }
        }

        VentaProducto nuevo = new VentaProducto(0, idProducto, cantidad, precio);
        carrito.add(nuevo);
        actualizarTablaCarrito();
    }

    private void actualizarTablaCarrito() {
        modelCarrito.setRowCount(0);
        double total = 0;
        
        for (VentaProducto vp : carrito) {
            String nombreProducto = obtenerNombreProducto(vp.getId_producto());
            double subtotal = vp.getCantidad() * vp.getPrecio_unitario();
            total += subtotal;
            
            modelCarrito.addRow(new Object[]{
                vp.getId_producto(),
                nombreProducto,
                String.format("$%.2f", vp.getPrecio_unitario()),
                vp.getCantidad(),
                String.format("$%.2f", subtotal)
            });
        }
        
        lblTotalCarrito.setText(String.format("Total: $%.2f", total));
    }

    private void finalizarCompra() {
        if (carrito.isEmpty()) {
            mostrarError("El carrito está vacío");
            return;
        }

        for (VentaProducto vp : carrito) {
            Producto p = ControllerProducto.buscarProducto(vp.getId_producto());
            if (p == null || p.getStock() < vp.getCantidad()) {
                mostrarError("Stock insuficiente para: " + (p != null ? p.getNombre() : "producto ID " + vp.getId_producto()));
                return;
            }
        }

        Object[] options = {"Confirmar", "Cancelar"};
        int confirm = JOptionPane.showOptionDialog(this,
            crearPanelConfirmacion(),
            "Confirmar Compra",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int idVenta = ControllerVenta.crearVenta(usuarioActual.getId(), usuarioActual.getId_sucursal(), calcularTotal());
        if (idVenta <= 0) {
            mostrarError("Error al crear la venta.");
            return;
        }

        boolean error = false;
        for (VentaProducto vp : carrito) {
            Producto p = ControllerProducto.buscarProducto(vp.getId_producto());
            
            if (!ControllerProducto.reducirStock(p.getId(), vp.getCantidad())) {
                error = true;
                break;
            }

            vp.setId_venta(idVenta);
            if (!ControllerVenta.registrarProductoEnVenta(vp)) {
                error = true;
                break;
            }
        }

        if (error) {
            mostrarError("Ocurrió un error al procesar la venta. Por favor intente nuevamente.");
        } else {
            mostrarTicket();
            carrito.clear();
            actualizarTablaCarrito();
            cargarTabla();
        }
    }
    
    private JPanel crearPanelConfirmacion() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(222, 221, 218));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel lblTitulo = new JLabel("¿Confirmar compra por:");
        lblTitulo.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblTitulo.setForeground(new Color(63, 192, 108));
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        JLabel lblTotal = new JLabel(String.format("$%.2f", calcularTotal()));
        lblTotal.setFont(new Font("Montserrat", Font.BOLD, 18));
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTotal, BorderLayout.CENTER);
        
        return panel;
    }

    private void mostrarTicket() {
        JDialog ticketDialog = new JDialog(this, "Ticket de Compra", true);
        ticketDialog.setSize(400, 500);
        ticketDialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel lblTitulo = new JLabel("JUMBOX - Ticket de Compra");
        lblTitulo.setFont(new Font("Montserrat", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(63, 192, 108));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        JTextArea areaDetalle = new JTextArea();
        areaDetalle.setEditable(false);
        areaDetalle.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaDetalle.setBackground(Color.WHITE);
        
        StringBuilder detalle = new StringBuilder();
        detalle.append("--------------------------------\n");
        detalle.append(String.format("%-20s %6s\n", "Producto", "Subtotal"));
        detalle.append("--------------------------------\n");
        
        for (VentaProducto vp : carrito) {
            String nombreProducto = obtenerNombreProducto(vp.getId_producto());
            if (nombreProducto.length() > 20) {
                nombreProducto = nombreProducto.substring(0, 17) + "...";
            }
            double subtotal = vp.getCantidad() * vp.getPrecio_unitario();
            
            detalle.append(String.format("%-20s $%6.2f\n", nombreProducto, subtotal));
        }
        
        detalle.append("--------------------------------\n");
        detalle.append(String.format("%-20s $%6.2f\n", "TOTAL:", calcularTotal()));
        detalle.append("--------------------------------\n");
        detalle.append("\nGracias por su compra!");
        
        areaDetalle.setText(detalle.toString());
        panel.add(new JScrollPane(areaDetalle), BorderLayout.CENTER);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setBackground(new Color(63, 192, 108));
        btnCerrar.addActionListener(e -> ticketDialog.dispose());
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(Color.WHITE);
        panelBoton.add(btnCerrar);
        
        panel.add(panelBoton, BorderLayout.SOUTH);
        ticketDialog.add(panel);
        ticketDialog.setVisible(true);
    }

    private String obtenerNombreProducto(int idProducto) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if ((int)model.getValueAt(i, 0) == idProducto) {
                return (String)model.getValueAt(i, 1);
            }
        }
        return "Producto no encontrado";
    }

    private double calcularTotal() {
        return carrito.stream()
            .mapToDouble(vp -> vp.getCantidad() * vp.getPrecio_unitario())
            .sum();
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
            if(producto.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
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

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, 
            mensaje, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}