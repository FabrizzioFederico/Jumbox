package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Usuario;


public class ControllerUsuario{

    private static Connection con = Conexion.getInstance().getConnection();


    public static Usuario login(String nombre, String contrasenia) {
    	Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuario WHERE nombre = ? AND contrasenia = ?"
            );
            stmt.setString(1, nombre);
            stmt.setString(2, usuario.encriptar(contrasenia));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_usuario");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                String password = rs.getString("contrasenia");
                int id_sucursal = rs.getInt("id_sucursal");
                String elegido = rs.getString("rol");
                int venta_id_venta = rs.getInt("Venta_id_venta");
                int venta_VentaProducto_id_venta = rs.getInt("Venta_VentaProducto_id_venta");
                
                
                usuario =  new Usuario(id, nombre, email, usuario.desencriptar(password), direccion, id_sucursal, elegido, venta_id_venta , venta_VentaProducto_id_venta);
  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static void agregarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO usuario (nombre, email, contrasenia, direccion, id_sucursal, rol, venta_id_venta, Venta_VentaProducto_id_venta) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.encriptar(usuario.getContrasenia()));
            statement.setString(4, usuario.getDireccion());
            statement.setInt(5, usuario.getId_sucursal());
            statement.setString(6, usuario.getElegido());
            statement.setInt(7, usuario.getVenta_id_venta());
            statement.setInt(8, usuario.getVenta_VentaProducto_id_venta());
            

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario agregado correctamente.");
            }
            
        } catch(MySQLIntegrityConstraintViolationException e) { // Preguntar al Gami Existente (Validaciones) o Campos Vacios
        	JOptionPane.showMessageDialog(null, "Usuario Existente o Campos Vacios");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void Registrarse(Usuario nuevo) {
		LinkedList<Usuario> creados = mostrarUsuarios();
		for (Usuario creado : creados) {
			if (creado.getEmail().equalsIgnoreCase(nuevo.getEmail())) {
				JOptionPane.showMessageDialog(null, "Usuario creado, con este mismo mail");
				return;
			}
		}
		agregarUsuario(nuevo);
		
	}


    public static LinkedList<Usuario> mostrarUsuarios() {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String contrasenia = rs.getString("contrasenia");
                String direccion = rs.getString("direccion");
                int idSucursal = rs.getInt("Id_sucursal");
                String elegido = rs.getString("elegido");
                int idVenta_id_venta = rs.getInt("Id_venta");
                int idVenta_VentaProducto_id_venta = rs.getInt("idVenta_VentaProducto_id_venta");
               
                usuarios.add(new Usuario(id, nombre, email, contrasenia, direccion, idSucursal, elegido, idVenta_id_venta, idVenta_VentaProducto_id_venta));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
