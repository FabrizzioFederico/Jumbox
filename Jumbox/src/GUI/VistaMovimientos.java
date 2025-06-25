package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import BLL.Usuario;
import BLL.Venta;
import BLL.VentaProducto;
import BLL.Producto;
import DLL.ControllerVenta;
import DLL.ControllerProducto;

public class VistaMovimientos extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Usuario usuarioActual;

    public VistaMovimientos(Usuario usuario) {
        this.usuarioActual = usuario;
        setTitle("Historial de Compras - " + usuario.getNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(63, 192, 108));
        panelSuperior.setBounds(0, 0, 900, 60);
        contentPane.add(panelSuperior);
        panelSuperior.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Historial de Compras");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Montserrat", Font.BOLD, 18));
        lblTitulo.setBounds(20, 15, 300, 30);
        panelSuperior.add(lblTitulo);


        model = new DefaultTableModel(
         		new String[]{"ID Venta",
         				"Fecha",
         				"Total",
         				"Sucursal",
         				"Detalles"
         				}, 0);
//         table = new JTable(model);
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBounds(34, 58, 1006, 200);
         contentPane.add(scrollPane);
         
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);

        JButton btnDetalles = new JButton("Ver Detalles");
        btnDetalles.setBounds(20, 450, 150, 30);
        btnDetalles.setForeground(Color.WHITE);
        btnDetalles.setBackground(new Color(63, 192, 108));
        btnDetalles.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnDetalles.addActionListener(e -> mostrarDetalleVenta());
        contentPane.add(btnDetalles);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(190, 450, 150, 30);
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setBackground(new Color(63, 192, 108));
        btnActualizar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnActualizar.addActionListener(e -> cargarDatos());
        contentPane.add(btnActualizar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(720, 450, 150, 30);
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setBackground(new Color(192, 57, 43));
        btnCerrar.setFont(new Font("Montserrat", Font.PLAIN, 14));
        btnCerrar.addActionListener(e -> dispose());
        contentPane.add(btnCerrar);

        cargarDatos();
    }

    private void cargarDatos() {
        model.setRowCount(0); 
        
        LinkedList<Venta> ventas = ControllerVenta.obtenerVentasPorUsuario(usuarioActual.getId());
        
        if (ventas.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron compras registradas", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        for (Venta venta : ventas) {
            model.addRow(new Object[]{
                venta.getId(),
                venta.getFecha(),
                venta.getTotal(),
                venta.getId_sucursal(),
                "Ver detalles"
            });
        }
    }

    private void mostrarDetalleVenta() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione una venta primero", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idVenta = (int) model.getValueAt(row, 0);
        LinkedList<VentaProducto> productos = ControllerVenta.obtenerProductosDeVenta(idVenta);
        
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No se encontraron productos para esta venta", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(this, "Detalles de Venta #" + idVenta, true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        DefaultTableModel modelDetalle = new DefaultTableModel(
            new Object[]{"Producto", "Precio Unit.", "Cantidad", "Subtotal"}, 0);

        double total = 0;
        for (VentaProducto vp : productos) {
            Producto p = ControllerProducto.buscarProducto(vp.getId_producto());
            String nombre = (p != null) ? p.getNombre() : "Producto no encontrado";
            double subtotal = vp.getPrecio_unitario() * vp.getCantidad();
            total += subtotal;

            modelDetalle.addRow(new Object[]{
                nombre,
                vp.getPrecio_unitario(),
                vp.getCantidad(),
                subtotal
            });
        }

        JTable tableDetalle = new JTable(modelDetalle);
        tableDetalle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tableDetalle.setRowHeight(25);

        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel lblTotal = new JLabel(String.format("Total: $%,.2f", total));
        lblTotal.setFont(new Font("Montserrat", Font.BOLD, 14));
        panelTotal.add(lblTotal);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialog.dispose());

        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelTotal, BorderLayout.CENTER);
        panelSur.add(btnCerrar, BorderLayout.SOUTH);

        dialog.add(new JScrollPane(tableDetalle), BorderLayout.CENTER);
        dialog.add(panelSur, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}