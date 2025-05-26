package GUI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import BLL.Producto;
import BLL.Usuario;
import DLL.ControllerProducto;
import DLL.ControllerUsuario;
import repository.Rol;
import repository.Validaciones;

public class Main {
    public static void main(String[] args) {
        
        ControllerUsuario controller = new ControllerUsuario();
        
        String[] acciones = { "Login", "Registrar", "Ver Usuarios","Agregar Producto", "Buscar producto" , "Eliminar productos","Mostrar Productos", "Salir" };
        int menu = 0;
        
        do {
            menu = JOptionPane.showOptionDialog(null, "Bienvenido", null, 0, 0, null, acciones, acciones[0]);

            switch (menu) {
                case 0:
                    String nombre = "";
                    while (nombre.isEmpty()) {
                        nombre = JOptionPane.showInputDialog("Ingrese nombre");
                        if (nombre.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Incorrecto");
                        }
                    }

                    String contrasenia = "";
                    while (contrasenia.isEmpty()) {
                        contrasenia = JOptionPane.showInputDialog("Ingrese contraseña");
                        if (contrasenia.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Incorrecto");
                        }
                    }

                    Usuario usuario = controller.login(nombre, contrasenia);
                    if (usuario != null) {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + "!!!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                    break;
                    
                case 1:
                	ControllerUsuario.Registrarse(new Usuario(Validaciones.validarNombre("Ingresa tu nombre"),Validaciones.validarEmail("Ingrese su Mail"),Validaciones.ValidarContraseña("Ingrese su contraseña"),Validaciones.validarAlfanumerico("Ingrese su domicilio"),1,((Rol)JOptionPane.showInputDialog(null,"Seleccione una Opción","Jumbox",0,null,Rol.values(),Rol.values()[0])).name(),1,1));
                    break;
                case 2:
                	String nombresUsuarios="";
                	if (ControllerUsuario.mostrarUsuarios().isEmpty()) {
						JOptionPane.showMessageDialog(null, "La lista está vacía");
					} else {
						for (Usuario u : ControllerUsuario.mostrarUsuarios()) {
	    					nombresUsuarios = nombresUsuarios + "ID Cuenta: "+ u.getId() + " | Nombre: " + u.getNombre() + " | Email: " + u.getEmail() + " | Rol: " + u.getElegido() + " | Dirección: " + u.getDireccion() + "\n";
	    				}
	    				String[] nombreUsuarios = nombresUsuarios.split("\n");
	    				JOptionPane.showMessageDialog(null, nombreUsuarios);
					}
                	break;
                case 3:
                	ControllerProducto.agregarProducto(new Producto("Lays", 40, 5, 1,1));
                case 4:
                	JOptionPane.showMessageDialog(null, ControllerProducto.buscarProducto(1)==null?"Producto no encontrado":ControllerProducto.buscarProducto(1));
                	break;
                case 5: 
                	ControllerProducto.eliminarProducto(6);
                case 6: 
                	String nombresProductos="";
                	if (ControllerProducto.mostrarProductos().isEmpty()) {
						JOptionPane.showMessageDialog(null, "La lista está vacía");
					} else {
						for (Producto p : ControllerProducto.mostrarProductos()) {
	    					nombresProductos = nombresProductos + "ID Producto: "+ p.getId() + " | Nombre: " + p.getNombre() + " | Precio: " + p.getPrecio() + " | Stock: " + p.getStock() + "\n";
	    				}
	    				String[] nombreProductos = nombresProductos.split("\n");
	    				JOptionPane.showMessageDialog(null, nombreProductos);
					}
                	break;
            }
        } while (menu != 7);
    }
}
