package BLL;

import javax.swing.JOptionPane;

import DLL.ControllerUsuario;
import repository.Encriptador;

public class Usuario implements Encriptador{
    protected int id;
    protected String nombre;
    protected String email;
    protected String contrasenia;
    protected String direccion;
    protected int id_sucursal;
    protected String elegido;
    
    
    public Usuario(int id, String nombre, String email, String contrasenia, String direccion, int id_sucursal,
			String elegido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.contrasenia = contrasenia;
		this.direccion = direccion;
		this.id_sucursal = id_sucursal;
		this.elegido = elegido;
	}
    
    public Usuario() {
    	
    }
    
	public Usuario(String nombre, String email, String contrasenia, String direccion, int id_sucursal,
			String elegido) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.contrasenia = contrasenia;
		this.direccion = direccion;
		this.id_sucursal = id_sucursal;
		this.elegido = elegido;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(int id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getElegido() {
		return elegido;
	}

	public void setElegido(String elegido) {
		this.elegido = elegido;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", contrasenia=" + contrasenia
				+ ", direccion=" + direccion + ", id_sucursal=" + id_sucursal + ", elegido=" + elegido + "]";
	}
	
	
	
	

//	public static Usuario login(String nombre, String password) {
//		
//		if (nombre.isEmpty() || password.isEmpty()) {
//			JOptionPane.showMessageDialog(null, "No puede ser vacio");
//		}else {
//			
//			return Usuario.login(nombre, password);
//		}
//		return null;
//	}

	public static Usuario login(String nombre, String contrasenia) {
		
		if (nombre == null || contrasenia == null || 
		        nombre.isEmpty() || contrasenia.isEmpty()) {
			return null;
		}else {
			
			return ControllerUsuario.login(nombre, contrasenia);
		}
	}




	public static String Editar(Usuario usuario) {
			
			//if (usuario.getNombre()) {
			//	return "El campo"
			//}
		
			if (usuario.getNombre().isEmpty() ||usuario.getEmail().isEmpty()) {
				return  "Este campo no puede ir vacio";
			}else {
				
				return ControllerUsuario.Editar(usuario);
			}
			
		}
	public static void Registrarse(Usuario nuevo) {
		if (nuevo.getNombre().isEmpty() || nuevo.getContrasenia().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Este campo no puede ir vacio");
		}else {
			
			 ControllerUsuario.Registrarse(nuevo);
		}
	}
	
	
	
	
    

}
