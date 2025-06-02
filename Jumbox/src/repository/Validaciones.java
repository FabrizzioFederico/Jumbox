package repository;

import javax.swing.JOptionPane;

public interface Validaciones {
	public static String validarNombreSinIngreso(String data) {
        boolean esValido;
        
        do {
            esValido = false;
            try {
                
                for (int i = 0; i < data.length(); i++) {
                    char c = data.charAt(i);
                    if (!Character.isAlphabetic(c) && c != ' ') {
                        JOptionPane.showMessageDialog(null, 
                            "El nombre solo puede contener:\n" +
                            "- Letras \n" +
                            "- Espacios \n" +
                            "Carácter no válido encontrado: '" + c + "'");
                        esValido = true;
                        break;
                    }
                }
                
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return null;
            }
        } while (!esValido);

        return data.trim();
    }
	public static String validarNombre(String mensaje) {
        String texto = "";
        boolean esValido;
        
        do {
            esValido = true;
            try {
                
                texto = JOptionPane.showInputDialog(mensaje);
                
                
                if (texto == null) {
                    throw new NullPointerException("Operación cancelada por el usuario");
                }
                
                
                while (texto.trim().isEmpty()) {
                    texto = JOptionPane.showInputDialog("El nombre no puede estar vacío.\n" + mensaje);
                    if (texto == null) {
                        throw new NullPointerException("Operación cancelada por el usuario");
                    }
                }
                
                
                for (int i = 0; i < texto.length(); i++) {
                    char c = texto.charAt(i);
                    if (!Character.isAlphabetic(c) && c != ' ') {
                        JOptionPane.showMessageDialog(null, 
                            "El nombre solo puede contener:\n" +
                            "- Letras \n" +
                            "- Espacios \n" +
                            "Carácter no válido encontrado: '" + c + "'");
                        esValido = false;
                        break;
                    }
                }
                
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return null;
            }
        } while (!esValido);

        return texto.trim();
    }
	
	public static String validarAlfanumerico(String mensaje) {
        String texto = "";
        boolean esValido;
        
        do {
            esValido = true;
            try {
                
                texto = JOptionPane.showInputDialog(mensaje);
                
                
                if (texto == null) {
                    throw new NullPointerException("Operación cancelada por el usuario");
                }
                
                
                if (texto.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo no puede estar vacío");
                    esValido = false;
                    continue;
                }
                
                
                for (int i = 0; i < texto.length(); i++) {
                    char c = texto.charAt(i);
                    if (!Character.isAlphabetic(c) && !Character.isDigit(c) && c != ' ') {
                        JOptionPane.showMessageDialog(null, 
                            "Solo se permiten letras y números.\n" +
                            "Carácter no válido: '" + c + "'");
                        esValido = false;
                        break;
                    }
                }
                
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return null;
            }
        } while (!esValido);

        return texto;
    }
	
	public static String ValidarContraseña(String mensaje) {
        String contraseña = "";
        boolean esValida = false;
        
        do {
            int mayuscula = 0;
            int minuscula = 0;
            int numero = 0;
            int especial = 0;
            
            try {
                
                contraseña = JOptionPane.showInputDialog(mensaje + "\nRequisitos:\n- Mínimo 8 caracteres\n- Al menos 1 mayúscula\n- Al menos 1 minúscula\n- Al menos 1 número");
                
                
                if (contraseña == null) {
                    throw new NullPointerException("Operación cancelada por el usuario");
                }
                
                
                if (contraseña.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía");
                    continue;
                }
                
                
                for (int i = 0; i < contraseña.length(); i++) {
                    char c = contraseña.charAt(i);
                    if (Character.isLowerCase(c)) {
                        minuscula++;
                    } else if (Character.isUpperCase(c)) {
                        mayuscula++;
                    } else if (Character.isDigit(c)) {
                        numero++;
                    } else {
                        especial++;
                    }
                }
                
                
                if (contraseña.length() < 8) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres");
                } else if (mayuscula < 1) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos 1 mayúscula");
                } else if (minuscula < 1) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos 1 minúscula");
                } else if (numero < 1) {
                    JOptionPane.showMessageDialog(null, "La contraseña debe contener al menos 1 número");
                } else {
                    esValida = true;
                }
                
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return null;
            }
        } while (!esValida);

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
	
	public static String validarEmail(String mensaje) {
        String email = "";
        boolean esValido;
        
        do {
            esValido = true;
            try {
                
                email = JOptionPane.showInputDialog(mensaje);
                
                
                if (email == null) {
                    throw new NullPointerException("Operación cancelada por el usuario");
                }
                
                
                while (email.trim().isEmpty()) {
                    email = JOptionPane.showInputDialog("El correo no puede estar vacío.\n" + mensaje);
                    if (email == null) {
                        throw new NullPointerException("Operación cancelada por el usuario");
                    }
                }
                
                
                if (!esEmailValido(email)) {
                    JOptionPane.showMessageDialog(null, 
                        "Formato de correo inválido. Debe contener:\n" +
                        "- Un símbolo @\n" +
                        "- Texto antes y después del @\n" +
                        "- Un punto después del @\n" +
                        "Ejemplo válido: usuario@dominio.com");
                    esValido = false;
                }
                
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return null;
            }
        } while (!esValido);

        return email;
    }
    
    public static boolean esEmailValido(String email) {
        
        int arrobaIndex = email.indexOf('@');
        if (arrobaIndex <= 0 || arrobaIndex != email.lastIndexOf('@')) {
            return false;
        }
        
        
        int puntoIndex = email.indexOf('.', arrobaIndex);
        if (puntoIndex <= arrobaIndex + 1 || puntoIndex == email.length() - 1) {
            return false;
        }
        
       
        if (email.contains(" ")) {
            return false;
        }
        
        
        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '@' || c == '.' || c == '_' || c == '-' || c == '+')) {
                return false;
            }
        }
        
        return true;
    }
}
