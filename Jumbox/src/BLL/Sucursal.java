package BLL;
import java.util.LinkedList;

public class Sucursal {
	private int id;
	private String nombre;
	private String direccion;
	private Cliente cliente;
	private Repartidor repartidor;
	private Encargado_Stock
	encargado_stock;
	private LinkedList<Producto> productos =
	new LinkedList<Producto>();
	private LinkedList<Venta> ventas = new
	LinkedList<Venta>();
	
	public Sucursal(int id, String nombre, String direccion, Cliente cliente, Repartidor repartidor,
			Encargado_Stock encargado_stock, LinkedList<Producto> productos, LinkedList<Venta> ventas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.cliente = cliente;
		this.repartidor = repartidor;
		this.encargado_stock = encargado_stock;
		this.productos = productos;
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", cliente=" + cliente
				+ ", repartidor=" + repartidor + ", encargado_stock=" + encargado_stock + ", productos=" + productos
				+ ", ventas=" + ventas + "]";
	}
	
	
	
}
