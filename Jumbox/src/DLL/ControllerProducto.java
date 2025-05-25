package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Producto;

public class ControllerProducto {
	
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static void agregarProducto(Producto producto) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO producto (nombre, precio, stock, id_sucursal, Venta_VentaProducto_id_venta) VALUES (?, ?, ?, ?, ?)"
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
	
	
	
}
