import java.util.HashMap;

public class TablaDeSimbolos {
    // Guarda el nombre de la variable y su valor actual
    private HashMap<String, Object> memoria = new HashMap<>();

    public void declarar(String nombre, String tipo) {
        if (memoria.containsKey(nombre)) {
            throw new RuntimeException("Error Semántico: Variable '" + nombre + "' ya fue declarada.");
        }
        // Inicializamos: los int empiezan en 0, los bool en false
        memoria.put(nombre, tipo.equals("int") ? 0 : false);
    }

    public void asignar(String nombre, Object valor) {
        if (!memoria.containsKey(nombre)) {
            throw new RuntimeException("Error Semántico: Intentas asignar valor a '" + nombre + "' pero no está declarada.");
        }
        memoria.put(nombre, valor);
    }

    public Object obtener(String nombre) {
        if (!memoria.containsKey(nombre)) {
            throw new RuntimeException("Error Semántico: Intentas usar la variable '" + nombre + "' pero no está declarada.");
        }
        return memoria.get(nombre);
    }

    public void imprimirMemoria() {
        System.out.println("\n--- MEMORIA AL FINALIZAR ---");
        for (String var : memoria.keySet()) {
            System.out.println(var + " = " + memoria.get(var));
        }
    }
}