package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Producto;
import BLL.Usuario;
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
			nombre.setBounds(48, 72, 116, 14);
			contentPane.add(nombre);
			
			inpNombre = new JTextField();
			inpNombre.setBounds(48, 97, 116, 20);
			inpNombre.setText(producto.getNombre());
			contentPane.add(inpNombre);
			inpNombre.setColumns(10);
			
			precio = new JLabel("precio");
			precio.setBounds(48, 128, 116, 14);
			contentPane.add(precio);
			
			inpPrecio = new JTextField();
			inpPrecio.setText(producto.getPrecio());
			inpPrecio.setColumns(10);
			inpPrecio.setBounds(48, 153, 116, 20);
			contentPane.add(inpPrecio);
			
			inpStock = new JTextField();
			inpStock.setText(producto.getStock());
			inpStock.setColumns(10);
			inpStock.setBounds(48, 209, 116, 20);
			contentPane.add(inpStock);
			
			stock = new JLabel("stock");
			stock.setBounds(48, 184, 116, 14);
			contentPane.add(stock);
			
			inpId_sucursal = new JTextField();
			inpId_sucursal.setText(producto.getid_sucursal());
			inpId_sucursal.setColumns(10);
			inpId_sucursal.setBounds(48, 209, 116, 20);
			contentPane.add(inpId_sucursal);
			
			id_sucursal = new JLabel("id_sucursal");
			id_sucursal.setBounds(48, 184, 116, 14);
			contentPane.add(id_sucursal);
			
			btnNewButton = new JButton("Editar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Producto nuevo = producto;
					nuevo.setNombre(inpNombre.getText());
					nuevo.setPrecio(inpPrecio.getText());
					nuevo.setStock(inpStock.gerText());
					nuevo.setId_sucursal(inpId_sucursal.getText());
					
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
	
	
