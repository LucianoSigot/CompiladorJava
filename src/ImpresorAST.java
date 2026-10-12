

public class ImpresorAST {
    public static void imprimir(NodoAST nodo, String prefijo) {
        if (nodo instanceof NodoMain) {
            NodoMain n = (NodoMain) nodo;
            System.out.println(prefijo + "Programa (Main) - Retorna: " + n.tipoRetorno);
            System.out.println(prefijo + "  Declaraciones:");
            for (NodoDeclaracion d : n.declaraciones) imprimir(d, prefijo + "    ");
            System.out.println(prefijo + "  Sentencias:");
            for (Sentencia s : n.sentencias) imprimir(s, prefijo + "    ");

        } else if (nodo instanceof NodoDeclaracion) {
            NodoDeclaracion n = (NodoDeclaracion) nodo;
            System.out.println(prefijo + "Declara: " + n.tipo + " " + n.id);

        } else if (nodo instanceof NodoAsignacion) {
            NodoAsignacion n = (NodoAsignacion) nodo;
            System.out.println(prefijo + "Asignación a variable '" + n.id + "':");
            imprimir(n.expr, prefijo + "  ");

        } else if (nodo instanceof NodoReturn) {
            NodoReturn n = (NodoReturn) nodo;
            System.out.println(prefijo + "Return:");
            if (n.expr != null) imprimir(n.expr, prefijo + "  ");
            else System.out.println(prefijo + "  (vacío)");

        } else if (nodo instanceof NodoOperacion) {
            NodoOperacion n = (NodoOperacion) nodo;
            System.out.println(prefijo + "Operación: [ " + n.operador + " ]");
            imprimir(n.izq, prefijo + "  ");
            imprimir(n.der, prefijo + "  ");

        } else if (nodo instanceof NodoIdentificador) {
            System.out.println(prefijo + "Variable: " + ((NodoIdentificador) nodo).id);

        } else if (nodo instanceof NodoNumero) {
            System.out.println(prefijo + "Número: " + ((NodoNumero) nodo).valor);

        } else if (nodo instanceof NodoBooleano) {
            System.out.println(prefijo + "Booleano: " + ((NodoBooleano) nodo).valor);
        }
    }
}