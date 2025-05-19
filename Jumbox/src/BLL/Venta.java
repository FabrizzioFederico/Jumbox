package BLL;
import java.util.LinkedList;

public class Venta {
	private LinkedList <Producto> productos = new
	LinkedList<Producto>();

	public Venta(LinkedList<Producto> productos) {
		super();
		this.productos = productos;
	}
}
