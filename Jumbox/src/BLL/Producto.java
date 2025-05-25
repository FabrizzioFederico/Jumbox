package BLL;

public class Producto {
	private int id;
	private String nombre;
	private double precio;
	private int stock;
	private int id_sucursal;
	private int VentaProducto_id_venta;
	
	public Producto( String nombre, double precio, int stock, int id_sucursal, int VentaProducto_id_venta) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.id_sucursal= id_sucursal;
		this.VentaProducto_id_venta= VentaProducto_id_venta;
	}
	
	public Producto(int id, String nombre, double precio, int stock, int id_sucursal, int VentaProducto_id_venta) {
		super();
		this.id= id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.id_sucursal= id_sucursal;
		this.VentaProducto_id_venta= VentaProducto_id_venta;
	}
	
	public Producto() {
		
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getid_sucursal() {
		return id_sucursal;
	}

	public void setid_sucursal(int id_sucursal) {
		this.id_sucursal = id_sucursal;
	}
	
	public int getVentaProducto_id_venta() {
		return VentaProducto_id_venta;
	}

	public void setVentaProducto_id_venta(int VentaProducto_id_venta) {
		this.VentaProducto_id_venta = VentaProducto_id_venta;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", stock=" + stock
				+ ", id_sucursal=" + id_sucursal + ", VentaProducto_id_venta=" + VentaProducto_id_venta + "]";
	}
	
	
	
	
	
}
