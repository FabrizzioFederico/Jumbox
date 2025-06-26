package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Usuario;


public class ControllerUsuario{

    private static Connection con = Conexion.getInstance().getConnection();


    public static Usuario login(String email, String contrasenia) {

        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuario WHERE email = ? AND contrasenia = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, new Usuario().encriptar(contrasenia));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String password = rs.getString("contrasenia");
                int id_sucursal = rs.getInt("id_sucursal");
                String elegido = rs.getString("rol");
                
                // Crear nuevo usuario y desencriptar la contraseña
                Usuario usuario = new Usuario();
                String contraseniaDesencriptada = usuario.desencriptar(password);
                
                return new Usuario(id, nombre, email, contraseniaDesencriptada, 
                                 direccion, id_sucursal, elegido);
                
               // usuario =  new Usuario(id, nombre, email, usuario.desencriptar(password), direccion, id_sucursal, elegido, venta_id_venta , venta_VentaProducto_id_venta);
  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void agregarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO usuario (nombre, email, contrasenia, direccion, id_sucursal, rol) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.encriptar(usuario.getContrasenia()));
            statement.setString(4, usuario.getDireccion());
            statement.setInt(5, usuario.getId_sucursal());
            statement.setString(6, usuario.getElegido());
            

            int filas = statement.executeUpdate();
            if (filas > 0) {
            	ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                    System.out.println("Usuario agregado correctamente. ID: " + usuario.getId());
                }
            }
            
        } catch(MySQLIntegrityConstraintViolationException e) {
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
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String contrasenia = rs.getString("contrasenia");
                String direccion = rs.getString("direccion");
                int idSucursal = rs.getInt("Id_sucursal");
                String elegido = rs.getString("rol");
               
                usuarios.add(new Usuario(id, nombre, email, contrasenia, direccion, idSucursal, elegido));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
    public static String Editar(Usuario usuario) {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE `usuario` SET `nombre`=?,`email`=?,`contrasenia`=?,`direccion`=?,`id_sucursal`=?,`rol`=? WHERE id_usuario = ?");
			//"UPDATE `usuario` SET `nombre`=?,`email`=?,`tipo`=?,`password`=?  WHERE id = ?"
			stmt.setString(1, usuario.getNombre());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.encriptar(usuario.getContrasenia()));
			stmt.setString(4,usuario.getDireccion());
			stmt.setInt(5,usuario.getId_sucursal());
			stmt.setString(6,usuario.getElegido());
			stmt.setInt(7,usuario.getId());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				return"Usuario editado correctamente.";
				 
			}
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			return "Mail existente";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}
    
    public static boolean Eliminar(Usuario usuario) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM `usuario` WHERE id_usuario = ?");
			stmt.setInt(1,usuario.getId());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Usuario eliminado correctamente.");
			}
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "No existe usuario con ese ID");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
    
    public static Usuario buscarUsuario(int id_buscado) { // esto se va a cambiar con la interfaz Gráfica
    	Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
            stmt.setInt(1, id_buscado);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String contrasenia = rs.getString("contrasenia");
                String direccion = rs.getString("direccion");
                int idSucursal = rs.getInt("Id_sucursal");
                String elegido = rs.getString("rol");
               
                usuario = new Usuario(id, nombre, email, contrasenia, direccion, idSucursal, elegido);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return usuario;
    }
}
