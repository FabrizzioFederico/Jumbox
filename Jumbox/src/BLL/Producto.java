package BLL;

public class Producto {
	private Sucursal sucursal;
	private int id;
	private String nombre;
	private double precio;
	private int stock;
	
	public Producto(Sucursal sucursal, int id, String nombre, double precio, int stock) {
		super();
		this.sucursal = sucursal;
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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
	
	
	
}
