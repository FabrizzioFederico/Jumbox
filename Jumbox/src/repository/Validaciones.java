package repository;

import javax.swing.JOptionPane;

public interface Validaciones {
	
	public static String validarNombre(String mensaje) {
		String texto = "";
		Boolean flag;
		do {
			flag = true;
			texto = JOptionPane.showInputDialog(mensaje);
			while (texto.isEmpty()) {
				texto = JOptionPane.showInputDialog(mensaje);
			}
			for (int i = 0; i < texto.length(); i++) {
				if (!Character.isAlphabetic(texto.charAt(i))) {
					flag = false;
					break;
				}
			}
		} while (!flag);

		return texto;
	}
	
	public static String ValidarContraseña(String mensaje) {
		String contraseña = "";
		boolean flag = false;
		int mayuscula = 0;
		int minuscula = 0;
		int numero = 0;
		do {
			contraseña = JOptionPane.showInputDialog(mensaje);
			for (int i = 0; i < contraseña.length(); i++) {
				if (Character.isLowerCase(contraseña.charAt(i))) {
					minuscula++;
				} else if (Character.isUpperCase(contraseña.charAt(i))) {
					mayuscula++;
				} else if (Character.isDigit(contraseña.charAt(i))) {
					numero++;
				}
			}

			if (mayuscula >= 1 && minuscula >= 1 && contraseña.length() >= 8 && numero >= 1) {
				flag = true;
			}
		} while (!flag);

		return contraseña;

	}
	
	public static double validarDinero(String mensaje) {
		boolean flag;
		String num = "";
		int cont = 0;
		do {
			flag = true;
			num = JOptionPane.showInputDialog(mensaje);
			while (num.isEmpty()) {
				num = JOptionPane.showInputDialog(mensaje);
			}
			for (int i = 0; i < num.length(); i++) {
				char c = num.charAt(i);
				if (!Character.isDigit(num.charAt(i))) {
					if (c == '.' && cont == 0) {
						cont++;
					} else {
						flag = false;
						break;
					}
				}
			}
			if (flag && Double.parseDouble(num) <= 0) {
				flag = false;
			}
		} while (!flag);
		return Double.parseDouble(num);
	}

	public static int validarNumero(String mensaje) {
		boolean flag;
		String num = "";
		do {
			flag = true;
			num = JOptionPane.showInputDialog(mensaje);
			while (num.isEmpty()) {
				num = JOptionPane.showInputDialog(mensaje);
			}
			for (int i = 0; i < num.length(); i++) {
				if (!Character.isDigit(num.charAt(i))) {
					flag = false;
					break;
				}
			}
			if (flag && Integer.parseInt(num) < 0) {
				flag = false;
			}
		} while (!flag);
		return Integer.parseInt(num);
	}
}
