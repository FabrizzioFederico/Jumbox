package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Usuario;
import BLL.VentaProducto;




public class ControllerVenta {
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static int crearVenta(int idUsuario, double total) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO venta (id_usuario, fecha, total) VALUES (?, NOW(), ?)" 
                );
            
            stmt.setInt(1, idUsuario);
            stmt.setDouble(2, total);
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
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
}
