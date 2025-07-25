package BLL;

public class VentaProducto {
	private int id_venta;
	private int id_producto;
	private int cantidad;
	private double precio_unitario;
	
	public VentaProducto(int id_venta, int id_producto, int cantidad, double precio_unitario) {
		super();
		this.id_venta = id_venta;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
	}

	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	

	@Override
	public String toString() {
		return "VentaProducto [id_venta=" + id_venta + ", id_producto=" + id_producto + ", cantidad=" + cantidad
				+ ", precio_unitario=" + precio_unitario + "]";
	}
	
}
