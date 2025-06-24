package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Usuario;
import BLL.VentaProducto;
import BLL.Venta;


public class ControllerVenta {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static int crearVenta(int idUsuario, int idSucursal, double total) {
		try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO venta (id_sucursal, fecha, total, id_usuario) VALUES (?, NOW(), ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setInt(1, idSucursal);
            stmt.setDouble(2, total);
            stmt.setInt(3, idUsuario);
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("No se pudo crear la venta, ninguna fila afectada.");
            }
            
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se obtuvo el ID de la venta creada.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static boolean registrarProductoEnVenta(VentaProducto vp) {
        try {        	
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO ventaproducto (id_venta, id_producto, cantidad, precio_unitario) VALUES (?,?,?,?)");
            
            stmt.setInt(1, vp.getId_venta());
            stmt.setInt(2, vp.getId_producto());
            stmt.setInt(3, vp.getCantidad());
            stmt.setDouble(4, vp.getPrecio_unitario());
            
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    
    public static LinkedList<Venta> obtenerVentasPorUsuario(int idUsuario) {
        LinkedList<Venta> ventas = new LinkedList<>();
        
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT id, id_sucursal, fecha, total FROM venta WHERE id_usuario = ? ORDER BY fecha DESC");
            stmt.setInt(1, idUsuario);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Venta venta = new Venta(
                    rs.getInt("id"),
                    idUsuario,
                    rs.getInt("id_sucursal"),
                    rs.getTimestamp("fecha"),
                    rs.getDouble("total")
                );
                ventas.add(venta);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ventas;
    }
    

    public static LinkedList<VentaProducto> obtenerProductosDeVenta(int idVenta) {
        LinkedList<VentaProducto> productos = new LinkedList<>();
        
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT id, id_producto, cantidad, precio_unitario FROM ventaproducto WHERE id_venta = ?");
            stmt.setInt(1, idVenta);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                VentaProducto vp = new VentaProducto(
                    rs.getInt("id"),
                    idVenta,
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_unitario")
                );
                productos.add(vp);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productos;
    }
    

    public static boolean reducirStock(int idProducto, int cantidad) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE producto SET stock = stock - ? WHERE id = ? AND stock >= ?");
            
            stmt.setInt(1, cantidad);
            stmt.setInt(2, idProducto);
            stmt.setInt(3, cantidad);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
    
    
