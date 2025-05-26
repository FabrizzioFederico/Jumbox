package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Producto;
import BLL.Usuario;

public class ControllerProducto {
	
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static void agregarProducto(Producto producto) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO producto (nombre, precio, stock, id_sucursal, VentaProducto_id_venta) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getStock());
            statement.setInt(4, producto.getid_sucursal());
            statement.setInt(5, producto.getVentaProducto_id_venta());
            
            
            

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Producto agregado correctamente.");
            }
            
        } catch(MySQLIntegrityConstraintViolationException e) { // Preguntar al Gami Existente (Validaciones) o Campos Vacios
        	JOptionPane.showMessageDialog(null, "Producto Existente o Campos Vacios");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static LinkedList<Producto> mostrarProductos() {
        LinkedList<Producto> productos = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM producto");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                int idSucursal = rs.getInt("id_sucursal");
                int VentaProducto_id_venta = rs.getInt("VentaProducto_id_venta");
               
                productos.add(new Producto(id, nombre, precio,  stock,  idSucursal, VentaProducto_id_venta));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
	
	public static void actualizarProducto(Producto producto) {
	    try {
	        PreparedStatement statement = con.prepareStatement(
	            "UPDATE producto SET nombre = ?, precio = ?, stock = ?, id_sucursal = ?, VentaProducto_id_venta = ? WHERE id_producto = ?"
	        );
	        statement.setString(1, producto.getNombre());
	        statement.setDouble(2, producto.getPrecio());
	        statement.setInt(3, producto.getStock());
	        statement.setInt(4, producto.getid_sucursal());
	        statement.setInt(5, producto.getVentaProducto_id_venta());
	        statement.setInt(6, producto.getId()); 

	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	            System.out.println("Producto actualizado correctamente.");
	        } else {
	            System.out.println("No se encontró el producto para actualizar.");
	        }
	    } catch (MySQLIntegrityConstraintViolationException e) {
	        JOptionPane.showMessageDialog(null, "Error de integridad: datos duplicados o campos vacíos.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	public static Producto buscarProducto(int id_producto) {
	    Producto producto = null;

	    try {
	        PreparedStatement statement = con.prepareStatement(
	            "SELECT * FROM producto WHERE id_producto = ?"
	        );
	        statement.setInt(1, id_producto);

	        ResultSet rs = statement.executeQuery();

	        if (rs.next()) {
	        	producto = new Producto();
	            producto.setId(rs.getInt("id_producto"));
	            producto.setNombre(rs.getString("nombre"));
	            producto.setPrecio(rs.getDouble("precio"));
	            producto.setStock(rs.getInt("stock"));
	            producto.setid_sucursal(rs.getInt("id_sucursal"));
	            producto.setVentaProducto_id_venta(rs.getInt("VentaProducto_id_venta"));
	        } else {
	            return null;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return producto;
	}
	
	public static void eliminarProducto(int id_producto) {
	    try {
	        PreparedStatement statement = con.prepareStatement(
	            "DELETE FROM producto WHERE id_producto = ?"
	        );
	        statement.setInt(1, id_producto);

	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	            JOptionPane.showMessageDialog(null, "Producto eliminado con éxito");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró el producto para eliminar.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}
