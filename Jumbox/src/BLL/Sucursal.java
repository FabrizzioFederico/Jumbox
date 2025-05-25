package BLL;
import java.util.LinkedList;

public class Sucursal {
	private int id;
	private String nombre;
	private String direccion;
	private LinkedList<Producto> productos =
	new LinkedList<Producto>();
	private LinkedList<Venta> ventas = new
	LinkedList<Venta>();
	
	public Sucursal(int id, String nombre, String direccion, LinkedList<Producto> productos, LinkedList<Venta> ventas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.productos = productos;
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", productos=" + productos
				+ ", ventas=" + ventas + "]";
	}

	
	
}
