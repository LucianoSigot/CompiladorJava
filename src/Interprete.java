public class Interprete {
    private TablaDeSimbolos tabla = new TablaDeSimbolos();

    public void ejecutar(NodoMain main) {
        System.out.println("\n--- INICIANDO INTÉRPRETE ---");

        // 1. Guarda las declaraciones en memoria[cite: 3]
        for (NodoDeclaracion d : main.declaraciones) {
            tabla.declarar(d.id, d.tipo);
        }

        // 2. Ejecuta las sentencias matemáticas[cite: 3]
        for (Sentencia s : main.sentencias) {
            if (s instanceof NodoAsignacion) {
                NodoAsignacion asig = (NodoAsignacion) s;
                // Resuelve la matemática a la derecha del igual
                Object resultado = evaluar(asig.expr);
                tabla.asignar(asig.id, resultado);

            } else if (s instanceof NodoReturn) {
                NodoReturn ret = (NodoReturn) s;
                if (ret.expr != null) {
                    System.out.println(">> EL PROGRAMA RETORNÓ: " + evaluar(ret.expr));
                } else {
                    System.out.println(">> EL PROGRAMA RETORNÓ: void");
                }
                break; // Corta la ejecución como un return real
            }
        }

        // Muestra cómo quedaron las variables tras hacer todas las cuentas
        tabla.imprimirMemoria();
    }

    // Método recursivo para resolver ecuaciones de abajo hacia arriba[cite: 3]
    private Object evaluar(Expresion expr) {
        if (expr instanceof NodoNumero) return ((NodoNumero) expr).valor;
        if (expr instanceof NodoBooleano) return ((NodoBooleano) expr).valor;
        if (expr instanceof NodoIdentificador) return tabla.obtener(((NodoIdentificador) expr).id);

        if (expr instanceof NodoOperacion) {
            NodoOperacion op = (NodoOperacion) expr;
            // Se llama a sí mismo hasta resolver toda la rama izquierda y derecha
            Integer valIzq = (Integer) evaluar(op.izq);
            Integer valDer = (Integer) evaluar(op.der);

            if (op.operador.equals("+")) return valIzq + valDer;
            if (op.operador.equals("*")) return valIzq * valDer;
        }
        return null;
    }
}