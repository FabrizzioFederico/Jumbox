package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import BLL.Usuario;


public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField inpUsuario;
    private JPasswordField inpContrasenia;
    private JButton btnIngresar;
    private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin frame = new VistaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 367);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Iniciar Sesión");
		lblTitulo.setBounds(282, 65, 167, 29);
		lblTitulo.setFont(new Font("Montserrat", Font.PLAIN, 20));
		contentPane.add(lblTitulo);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 244, 328);
		panel.setBackground(new Color(51, 153, 102));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLogo = new JLabel("New label");
        lblLogo.setIcon(new ImageIcon(VistaLogin.class.getResource("/img/logo2.png")));
        lblLogo.setBounds(-10, 0, 253, 328);
        panel.add(lblLogo);
		
		JLabel lblBienvenido = new JLabel("¡Bienvenido a Jumbox!");
		lblBienvenido.setBounds(282, 22, 206, 30);
		lblBienvenido.setBackground(new Color(54, 201, 102));
		lblBienvenido.setForeground(new Color(51, 153, 102));
		lblBienvenido.setFont(new Font("Montserrat", Font.PLAIN, 16));
		contentPane.add(lblBienvenido);
				
		JLabel lblUsuario = new JLabel("Nombre de usuario");
		lblUsuario.setBounds(282, 105, 167, 29);
	    lblUsuario.setFont(new Font("Montserrat", Font.PLAIN, 12));
	    contentPane.add(lblUsuario);		
		
        JLabel lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setBounds(282, 166, 79, 29);
        lblContrasenia.setFont(new Font("Montserrat", Font.PLAIN, 12));
        contentPane.add(lblContrasenia);
        
        inpUsuario = new JTextField();
        inpUsuario.setBounds(282, 134, 218, 25);
        inpUsuario.setFont(new Font("Montserrat", Font.PLAIN, 11));
        contentPane.add(inpUsuario);
        inpUsuario.setColumns(10);
        
        inpContrasenia = new JPasswordField(); 
        inpContrasenia.setBounds(282, 192, 218, 25);
        inpContrasenia.setFont(new Font("Montserrat", Font.PLAIN, 11));
        contentPane.add(inpContrasenia);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(282, 222, 92, 19);
		lblError.setFont(new Font("Montserrat", Font.PLAIN, 11));
		contentPane.add(lblError);

		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(282, 242, 92, 29);
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setBackground(new Color(63, 192, 108));
		btnIngresar.setFont(new Font("Montserrat", Font.PLAIN, 13));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = inpUsuario.getText();
                String contrasenia = new String(inpContrasenia.getPassword());
                //isLogueado
				Usuario logueado = Usuario.login(usuario, contrasenia);
				
				if (logueado == null) {
					lblError.setText("No se encontró");
				} else {
					String mensaje = "Bienvenido " + logueado.getNombre();
				    JOptionPane.showMessageDialog(null, mensaje);
				    
					 switch(logueado.getElegido()) {
				        case "ENCARGADO_STOCK":
				            VistaEncargadoStock pantallaStock = new VistaEncargadoStock(); // Tengo que Implemetarlos
				            pantallaStock.setVisible(true);
				            break;
				            
				        case "ADMIN":
				            VistaUsuarios pantallaAdmin = new VistaUsuarios();
				            pantallaAdmin.setVisible(true);
				            break;
				        case "CLIENTE":
				        default:
				            VistaCliente pantallaCliente = new VistaCliente(); // Tengo que Implemetarlos
				            pantallaCliente.setVisible(true);
				    }
				    dispose();
				}
				
				
			}
		});
		contentPane.add(btnIngresar);
		
		btnRegistrar = new JButton("Registrarme");
		btnRegistrar.setBounds(384, 242, 114, 29);
		btnRegistrar.setBackground(new Color(255, 255, 255));
		btnRegistrar.setFont(new Font("Montserrat", Font.PLAIN, 12));
		contentPane.add(btnRegistrar);
	}
}
