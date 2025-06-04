package repository;

import javax.swing.JOptionPane;

public interface Validaciones {
	public static String validarNombreSinIngreso(String data) {
	    if (data == null || data.trim().isEmpty()) {
	        return null; 
	    }

	    for (int i = 0; i < data.length(); i++) {
	        char c = data.charAt(i);
	        if (!Character.isAlphabetic(c) && c != ' ') {
	            return null;
	        }
	    }

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
	
	public static String validarAlfanumerico(String alfa) {
	    if (alfa == null || alfa.trim().isEmpty()) {
	        return null;
	    }
	    
	    for (int i = 0; i < alfa.length(); i++) {
	        char c = alfa.charAt(i);
	        if (!Character.isLetterOrDigit(c) && c != ' ') {
	            return null;
	        }
	    }
	    
	    return alfa.trim();
	}
	
	public static String validarContraseniaSinIngreso(String contrasenia) {
	    if (contrasenia == null || contrasenia.trim().isEmpty()) {
	        return null;
	    }
	    
	    int mayuscula = 0, minuscula = 0, numero = 0;
	    
	    for (char c : contrasenia.toCharArray()) {
	        if (Character.isLowerCase(c)) minuscula++;
	        else if (Character.isUpperCase(c)) mayuscula++;
	        else if (Character.isDigit(c)) numero++;
	    }
	    
	    
	    if (contrasenia.length() < 8 || mayuscula < 1 || minuscula < 1 || numero < 1) {
	        return null;
	    }
	    
	    return contrasenia;
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


	public static int validarNumeroPositivo(String texto) {
	    if (texto == null || texto.trim().isEmpty()) {
	        return -1; 
	    }
	    
	    int numero = Integer.parseInt(texto.trim());
	    if (numero >= 0) {
	    	return numero; 
	    }

	    return -1; 
	}
	
	public static String validarEmail(String email) {
	    
	    if (email == null) {
	        return null; 
	    }
	    
	    if (email.trim().isEmpty()) {
	        return null; 
	    }
	    
	    
	    int arrobaIndex = email.indexOf('@');
	    if (arrobaIndex <= 0 || arrobaIndex != email.lastIndexOf('@')) {
	        return null; 
	    }
	    
	    int puntoIndex = email.indexOf('.', arrobaIndex);
	    if (puntoIndex <= arrobaIndex + 1 || puntoIndex == email.length() - 1) {
	        return null; 
	    }
	    
	    if (email.contains(" ")) {
	        return null; 
	    }
	    
	    
	    for (int i = 0; i < email.length(); i++) {
	        char c = email.charAt(i);
	        if (!(Character.isLetterOrDigit(c) || c == '@' || c == '.' || c == '_' || c == '-' || c == '+')) {
	            return null; 
	        }
	    }
	    
	    return email.trim();
	}
}
