package repository;

public enum Local {
	FLORES(1, "Flores"),
    PALERMO(2, "Palermo"),
    BOEDO(3, "Boedo"),
    BALVANERA(4, "Balvanera"),
    ALMAGRO(5, "Almagro");
    
    private final int id;
    private final String nombre;
    
    Local(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    public static Local getById(int id) {
        for (Local l : values()) {
            if (l.id == id) return l;
        }
        return null;
    }
}
