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
	
	
	
}
