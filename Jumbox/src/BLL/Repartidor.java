package BLL;

public class Repartidor extends Usuario{

	public Repartidor(int id, String nombre, String email, String contrasenia, String direccion, int id_sucursal,
			String elegido, int venta_id_venta, int venta_VentaProducto_id_venta) {
		super(id, nombre, email, contrasenia, direccion, id_sucursal, elegido, venta_id_venta,
				venta_VentaProducto_id_venta);
	}
	
	public void enviarPedido() {
		
	}
}
