import java.io.FileReader;
import java_cup.runtime.Symbol;

public class Main {
    public static void main(String[] args) {
        String rutaArchivo = "ej.txt";

        try {
            Lexer lexer = new Lexer(new FileReader(rutaArchivo));
            Parser parser = new Parser(lexer);
            Symbol resultado = parser.parse();
            NodoMain ast = (NodoMain) resultado.value;

            System.out.println("¡Análisis sintáctico exitoso!");

            // 1. Imprime el Árbol AST para que lo veas
            System.out.println("\n--- ÁRBOL DE SINTAXIS ABSTRACTA ---");
            ImpresorAST.imprimir(ast, "");

            // 2. Arranca el intérprete y la tabla de símbolos (Punto 6)
            Interprete interprete = new Interprete();
            interprete.ejecutar(ast);

        } catch (java.io.FileNotFoundException e) {
            System.err.println("Error: No se encontró el archivo " + rutaArchivo);
        } catch (Exception e) {
            System.err.println("Error durante la compilación:");
            e.printStackTrace();
        }
    }
}