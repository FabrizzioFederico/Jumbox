//package BLL;
//import java.util.LinkedList;
//
//public class Venta {
//	private LinkedList <Producto> productos = new
//	LinkedList<Producto>();
//
//	public Venta(LinkedList<Producto> productos) {
//		super();
//		this.productos = productos;
//	}
//}

package BLL;

import java.sql.Timestamp;
import java.util.LinkedList;

public class Venta {
    private int id;
    private int id_usuario;
    private int id_sucursal;
    private Timestamp fecha;
    private double total;
    private LinkedList<VentaProducto> productos;

    public Venta(int id, int id_usuario, int id_sucursal, Timestamp fecha, double total) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_sucursal = id_sucursal;
        this.fecha = fecha;
        this.total = total;
        this.productos = new LinkedList<>();
    }

    // Getters
    public int getId() { return id; }
    public int getId_usuario() { return id_usuario; }
    public int getId_sucursal() { return id_sucursal; }
    public Timestamp getFecha() { return fecha; }
    public double getTotal() { return total; }
    public LinkedList<VentaProducto> getProductos() { return productos; }

    // Método para agregar productos
    public void agregarProducto(VentaProducto producto) {
        productos.add(producto);
    }
}