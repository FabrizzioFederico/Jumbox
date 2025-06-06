package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Producto;
import DLL.ControllerProducto;

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
		private JTextField inpId_sucursal;
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
			
			JLabel nombre = new JLabel("nombre");
			nombre.setBounds(48, 47, 116, 14);
			contentPane.add(nombre);
			
			inpNombre = new JTextField();
			inpNombre.setBounds(48, 72, 116, 20);
			inpNombre.setText(producto.getNombre());
			contentPane.add(inpNombre);
			inpNombre.setColumns(10);
			
			precio = new JLabel("precio");
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
			
			stock = new JLabel("stock");
			stock.setBounds(48, 156, 116, 14);
			contentPane.add(stock);
			
			inpId_sucursal = new JTextField();
			inpId_sucursal.setText(String.valueOf(producto.getid_sucursal()));       
			inpId_sucursal.setColumns(10);
			inpId_sucursal.setBounds(48, 240, 116, 20);
			contentPane.add(inpId_sucursal);
			
			id_sucursal = new JLabel("id_sucursal");
			id_sucursal.setBounds(48, 215, 116, 14);
			contentPane.add(id_sucursal);
			
			btnNewButton = new JButton("Editar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Producto nuevo = producto;
					nuevo.setNombre(inpNombre.getText());
					Double precio = Double.parseDouble(inpPrecio.getText());
					nuevo.setPrecio(precio);
					int stock = Integer.parseInt(inpStock.getText());
					nuevo.setStock(stock);
					int idSucursal = Integer.parseInt(inpId_sucursal.getText());
					nuevo.setid_sucursal(idSucursal);
					
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
					
					VistaProducto nuevo = new 	VistaProducto();
					nuevo.setVisible(true);
					dispose();
				}
			});
			btnNewButton_1.setBounds(181, 277, 89, 23);
			contentPane.add(btnNewButton_1);
		}
	}
	
	
