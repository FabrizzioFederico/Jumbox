package BLL;

public class Producto {
	private int id;
	private String nombre;
	private double precio;
	private int stock;
	private int id_sucursal;
	
	public Producto( String nombre, double precio, int stock, int id_sucursal) { //Sin ID
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.id_sucursal= id_sucursal;
	}
	
	public Producto(int id, String nombre, double precio, int stock, int id_sucursal) {//Con ID
		super();
		this.id= id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.id_sucursal= id_sucursal;
	}
	
	public Producto()  {//Vacío
		
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

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", stock=" + stock
				+ ", id_sucursal=" + id_sucursal + "]";
	}
	
	
	
	
	
}
