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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public LinkedList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(LinkedList<Producto> productos) {
		this.productos = productos;
	}

	public LinkedList<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(LinkedList<Venta> ventas) {
		this.ventas = ventas;
	}

	
	public void agregarProducto(Producto producto) {
		if (producto != null && !productos.contains(producto)) {
			productos.add(producto);
		}
	}

	public boolean eliminarProducto(int idProducto) {
		for(Producto producto : productos) {
			if(producto.getId()==idProducto) {
				productos.remove(producto);
				return true;
			}
		}
		return false;
	}


	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", productos=" + productos
				+ ", ventas=" + ventas + "]";
	}

	
	
}
