package GUI;

import java.awt.*;
import java.awt.event.*;
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
import BLL.VentaProducto;
import DLL.ControllerProducto;
import DLL.ControllerVenta;
import javax.swing.JSpinner;

public class VistaClientes extends JFrame {
	 private JPanel contentPane;
	    private JTable table;
	    private DefaultTableModel model;
	    private Producto productoSeleccionado;
	    private JTextField inpFiltro;
	    private DefaultTableModel modelCarrito;
	    private JTable tableCarrito;
	    private LinkedList<VentaProducto> carrito = new LinkedList<>();
	    private Usuario usuarioActual;

 public VistaClientes(Usuario usuario) {
	 this.usuarioActual = usuario;
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setBounds(100, 100, 885, 567);
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
     				"Cantidad",
     				"Sucursal"
     				}, 0);
     table = new JTable(model);
     JScrollPane scrollPane = new JScrollPane(table);
     scrollPane.setBounds(10, 40, 760, 200);
     contentPane.add(scrollPane);

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
                     (int) model.getValueAt(row, 4));
                 
                 lblSeleccionado.setText("Seleccionado: ID=" + productoSeleccionado.getId()
                         + ", Nombre=" + productoSeleccionado.getNombre()
                         + ", Precio=" + productoSeleccionado.getPrecio()
                         + ", Stock=" + productoSeleccionado.getStock()
                         + ", Id_Sucursal=" + productoSeleccionado.getid_sucursal()
                         );
                 
                
             }
         }
     });
     // Cargar datos
     cargarTabla();
     initCarrito();
 }
 
 private void initCarrito() {
     // Modelo para la tabla del carrito
     modelCarrito = new DefaultTableModel(
         new String[]{"ID Producto", "Nombre", "Precio Unit.", "Cantidad", "Subtotal"}, 0);
     
     tableCarrito = new JTable(modelCarrito);
     JScrollPane scrollCarrito = new JScrollPane(tableCarrito);
     scrollCarrito.setBounds(10, 350, 760, 100);
     contentPane.add(scrollCarrito);
     
     JButton btnAgregarAlCarrito = new JButton("Agregar al Carrito");
     btnAgregarAlCarrito.setBounds(10, 270, 150, 40);
     btnAgregarAlCarrito.addActionListener(e -> agregarAlCarrito());
     contentPane.add(btnAgregarAlCarrito);
     
     JButton btnFinalizarCompra = new JButton("Finalizar Compra");
     btnFinalizarCompra.setBounds(178, 270, 150, 40);
     btnFinalizarCompra.addActionListener(e -> finalizarCompra());
     contentPane.add(btnFinalizarCompra);
     
    
 }
 
 
 private void agregarAlCarrito() {
	    int row = table.getSelectedRow();
	    if (row == -1) {
	        JOptionPane.showMessageDialog(this, "Seleccione un producto primero");
	        return;
	    }

	    int stockDisponible = (int) model.getValueAt(row, 3); // Columna "Cantidad"
	    if (stockDisponible == 0) {
	        JOptionPane.showMessageDialog(this, "Este producto no tiene stock disponible.");
	        return;
	    }

	    // Pedir cantidad
	    String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese cantidad:");
	    if (cantidadStr == null || cantidadStr.trim().isEmpty()) return;

	    try {
	        int cantidad = Integer.parseInt(cantidadStr);

	        if (cantidad <= 0) {
	            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
	            return;
	        }

	        if (cantidad > stockDisponible) {
	            JOptionPane.showMessageDialog(this, "No hay suficiente stock. Stock disponible: " + stockDisponible);
	            return;
	        }

	        int idProducto = (int) model.getValueAt(row, 0);
	        String nombre = (String) model.getValueAt(row, 1);
	        double precio = (double) model.getValueAt(row, 2);

	        // Verificar si ya está en el carrito
	        for (VentaProducto vp : carrito) {
	            if (vp.getId_producto() == idProducto) {
	                if (vp.getCantidad() + cantidad > stockDisponible) {
	                    JOptionPane.showMessageDialog(this, "No hay suficiente stock para aumentar esa cantidad. Stock disponible: " + stockDisponible);
	                    return;
	                }
	                vp.setCantidad(vp.getCantidad() + cantidad);
	                actualizarTablaCarrito();
	                return;
	            }
	        }

	        // Si no está, agregarlo
	        VentaProducto nuevo = new VentaProducto(0, idProducto, cantidad, precio);
	        carrito.add(nuevo);
	        actualizarTablaCarrito();

	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Cantidad inválida");
	    }
	}
 
 
 private String obtenerNombreProducto(int idProducto) {
	    // Busca en la tabla de productos que ya tienes cargada
	    for (int i = 0; i < model.getRowCount(); i++) {
	        if ((int)model.getValueAt(i, 0) == idProducto) {
	            return (String)model.getValueAt(i, 1); // Retorna el nombre
	        }
	    }
	    return "Producto no encontrado";
	}
 
 private void actualizarTablaCarrito() {
     modelCarrito.setRowCount(0);
     for (VentaProducto vp : carrito) {
         // Necesitarás obtener el nombre del producto (puedes guardarlo en VentaProducto o buscarlo)
         String nombreProducto = obtenerNombreProducto(vp.getId_producto());
         double subtotal = vp.getCantidad() * vp.getPrecio_unitario();
         
         modelCarrito.addRow(new Object[]{
             vp.getId_producto(),
             nombreProducto,
             vp.getPrecio_unitario(),
             vp.getCantidad(),
             subtotal
         });
     }
 }
 
 private void finalizarCompra() {
	    if (carrito.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "El carrito está vacío");
	        return;
	    }

	    for (VentaProducto vp : carrito) {
	        Producto p = ControllerProducto.buscarProducto(vp.getId_producto());
	        if (p == null) {
	            JOptionPane.showMessageDialog(this, "Producto no encontrado (ID: " + vp.getId_producto() + ")");
	            return; 
	        }
	        if (p.getStock() < vp.getCantidad()) {
	            JOptionPane.showMessageDialog(this, "Stock insuficiente para el producto: " + p.getNombre());
	            return; 
	        }
	    }

	    int idVenta = ControllerVenta.crearVenta(usuarioActual.getId(), usuarioActual.getId_sucursal(), calcularTotal());
	    if (idVenta <= 0) {
	        JOptionPane.showMessageDialog(this, "Error al crear la venta.");
	        return;
	    }

	    boolean errorStock = false;

	    for (VentaProducto vp : carrito) {
	        Producto p = ControllerProducto.buscarProducto(vp.getId_producto());

	        if (p.getStock() < vp.getCantidad()) {
	            JOptionPane.showMessageDialog(this, "Stock insuficiente para el producto (al confirmar): " + p.getNombre());
	            errorStock = true;
	            break;
	        }

	        boolean actualizado = ControllerProducto.reducirStock(p.getId(), vp.getCantidad());
	        if (!actualizado) {
	            JOptionPane.showMessageDialog(this, "Error al actualizar stock para: " + p.getNombre());
	            errorStock = true;
	            break;
	        }

	        vp.setId_venta(idVenta);
	        ControllerVenta.registrarProductoEnVenta(vp);
	    }

	    if (errorStock) {
	        JOptionPane.showMessageDialog(this, "No se pudo completar la compra debido a problemas con el stock.");

	        return;
	    }

	    JOptionPane.showMessageDialog(this, "Venta realizada con éxito!");
	    
	    mostrarTicket();
	    
	    carrito.clear();
	    actualizarTablaCarrito();
	    cargarTabla(); 
	}

 
 private double calcularTotal() {
     double total = 0;
     for (VentaProducto vp : carrito) {
         total += vp.getCantidad() * vp.getPrecio_unitario();
     }
     return total;
 }
 
 private void mostrarTicket() {
	    StringBuilder detalle = new StringBuilder("DETALLE DE COMPRA\n");
	    detalle.append("===========================\n");

	    for (VentaProducto vp : carrito) {
	        String nombreProducto = obtenerNombreProducto(vp.getId_producto());
	        double subtotal = vp.getCantidad() * vp.getPrecio_unitario();

	        detalle.append(String.format("%s x%d $%.2f -> Subtotal: $%.2f\n",
	                nombreProducto, vp.getCantidad(), vp.getPrecio_unitario(), subtotal));
	    }

	    detalle.append("===========================\n");
	    detalle.append(String.format("TOTAL A PAGAR: $%.2f", calcularTotal()));

	    JOptionPane.showMessageDialog(this, detalle.toString(), "Resumen de compra", JOptionPane.INFORMATION_MESSAGE);
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
         		producto.getid_sucursal()}
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
         		producto.getid_sucursal()}
         );
     	
     }
 }
}