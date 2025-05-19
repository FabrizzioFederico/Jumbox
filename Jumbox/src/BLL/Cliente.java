package BLL;

public class Cliente extends Usuario {
	
	private String direccion;

	public Cliente(int id, String nombre, String email, String contrasenia, String direccion, int id_sucursal,
			String elegido, int venta_id_venta, int venta_VentaProducto_id_venta, String direccion2) {
		super(id, nombre, email, contrasenia, direccion, id_sucursal, elegido, venta_id_venta,
				venta_VentaProducto_id_venta);
		direccion = direccion2;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void realizarCompra() {
		
	}
	
	public void verCompra() {
		
	}

	@Override
	public String toString() {
		return "Cliente [direccion=" + direccion + "]";
	}
	
	
}
