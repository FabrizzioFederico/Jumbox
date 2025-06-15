package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Producto;
import BLL.Usuario;
import DLL.ControllerProducto;
import repository.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

	public class EditarProducto extends JFrame {

		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JTextField inpNombre;
		private JLabel precio;
		private JTextField inpPrecio;
		private JLabel stock;
		private JTextField inpStock;
		private JLabel id_sucursal;
		private JButton btnNewButton;
		private JButton btnNewButton_1;
		//Constructor 
		public EditarProducto( Producto producto ) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 571);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel nombre = new JLabel("Nombre");
			nombre.setBounds(48, 47, 116, 14);
			contentPane.add(nombre);
			JLabel lblInfo = new JLabel("");
			lblInfo.setBounds(48, 466, 338, 14);
			contentPane.add(lblInfo);
			
			inpNombre = new JTextField();
			inpNombre.setBounds(48, 72, 116, 20);
			inpNombre.setText(producto.getNombre());
			contentPane.add(inpNombre);
			inpNombre.setColumns(10);
			
			precio = new JLabel("Precio");
			precio.setBounds(48, 103, 116, 14);
			contentPane.add(precio);
			
			inpPrecio = new JTextField();
			inpPrecio.setText(String.valueOf(producto.getPrecio()));
			inpPrecio.setColumns(10);
			inpPrecio.setBounds(48, 125, 116, 20);
			contentPane.add(inpPrecio);
			
			inpStock = new JTextField();
			inpStock.setText(String.valueOf(producto.getStock()));   
			inpStock.setColumns(10);
			inpStock.setBounds(48, 181, 116, 20);
			contentPane.add(inpStock);
			
			stock = new JLabel("Stock");
			stock.setBounds(48, 156, 116, 14);
			contentPane.add(stock);
			
			id_sucursal = new JLabel("Sucursal");
			id_sucursal.setBounds(48, 215, 116, 14);
			contentPane.add(id_sucursal);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(48, 240, 116, 22);
			comboBox.addItem("Flores");
			comboBox.addItem("Palermo");
			comboBox.addItem("Boedo");
			comboBox.addItem("Balvanera");
			comboBox.addItem("Almagro");
			contentPane.add(comboBox);
			
			
			btnNewButton = new JButton("Editar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Producto nuevo = producto;
					String nombreValido= Validaciones.validarNombreSinIngreso(inpNombre.getText());
					if (nombreValido == null) {
			        	lblInfo.setText("Nombre inválido: Solo letras y espacios permitidos");
			            return;
			        }
					nuevo.setNombre(nombreValido);
					Double precioValido=Validaciones.validarDinero(inpPrecio.getText());
					if (precioValido == null) {
			        	lblInfo.setText("Precio inválido: Ingrese un precio positivo");
			            return;
			        }
					nuevo.setPrecio(precioValido);
					int stockValido = Validaciones.validarNumeroPositivo(inpStock.getText());
					if (stockValido <0) {
			        	lblInfo.setText("Stock inválido: Ingrese una cantidad positiva");
			            return;
					}
					nuevo.setStock(stockValido);
					
					comboBox.setSelectedItem(nuevo.getid_sucursal());
					int indiceSeleccionado = comboBox.getSelectedIndex(); 
					nuevo.setid_sucursal(indiceSeleccionado + 1);
					
					if (ControllerProducto.actualizarProducto(nuevo)) {
						JOptionPane.showMessageDialog(null, "Se edito");
						
					}else {
						JOptionPane.showMessageDialog(null, "No se edito");
					}
					
				}
			});
			btnNewButton.setBounds(48, 277, 89, 23);
			contentPane.add(btnNewButton);
			
			btnNewButton_1 = new JButton("Volver");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					VistaProducto nuevo = new VistaProducto();
					nuevo.setVisible(true);
					dispose();
				}
			});
			btnNewButton_1.setBounds(181, 277, 89, 23);
			contentPane.add(btnNewButton_1);
			
			
		}
	}
	
	
