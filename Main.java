import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main {
    private final static String ALGORITMO ="AES";
    public static void main(String[] args) {
        try {
            // Leer texto por teclado
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese un mensaje para cifrar: ");
            String mensaje = scanner.nextLine();

            // Imprimir texto original
            System.out.println("Texto original: " + mensaje);

            // Convertir a byte[]
            byte[] textoClaro = mensaje.getBytes();
            System.out.print("Texto claro (bytes): ");
            imprimir(textoClaro);

            // Generar la llave secreta
            KeyGenerator keygen = KeyGenerator.getInstance(ALGORITMO);
            SecretKey secretKey = keygen.generateKey();

            // Cifrar el texto
            byte[] textoCifrado = Simetrico.cifrar(secretKey, mensaje);
            System.out.print("Texto cifrado (bytes): ");
            imprimir(textoCifrado);

            // Descifrar el texto
            byte[] textoDescifrado = Simetrico.descifrar(secretKey, textoCifrado);
            System.out.print("Texto descifrado (bytes): ");
            imprimir(textoDescifrado);

            // Convertir byte[] a String
            String textoRecuperado = new String(textoDescifrado);
            System.out.println("Texto recuperado: " + textoRecuperado);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public static void imprimir(byte[] contenido) {
        int i = 0;
        for (; i < contenido.length - 1; i++) {
            System.out.print(contenido[i] + " ");
        }
        System.out.println(contenido[i] + " ");
    }

}
