import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main3 {
    private final static String ALGORITMO ="AES";
    private final static String ARCHIVO_LLAVE = "llave.secret";
    private final static String ARCHIVO_CIFRADO = "mensajeCifrado.dat";
    public static void main(String[] args) {
        try {
            // Leer texto por teclado
            long tiempoInicial = System.nanoTime();
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

            FileOutputStream archivoLlave = new FileOutputStream(ARCHIVO_LLAVE);
            ObjectOutputStream oosLlave = new ObjectOutputStream(archivoLlave);
            oosLlave.writeObject(secretKey);
            oosLlave.close();
            System.out.println("Llave secreta guardada en: " + ARCHIVO_LLAVE);

            // Cifrar el texto
            byte[] textoCifrado = Simetrico.cifrar(secretKey, mensaje);
            System.out.print("Texto cifrado (bytes): ");
            imprimir(textoCifrado);

            FileOutputStream archivoTexto = new FileOutputStream(ARCHIVO_CIFRADO);
            ObjectOutputStream oosTexto = new ObjectOutputStream(archivoTexto);
            oosTexto.writeObject(textoCifrado);
            oosTexto.close();
            System.out.println("Texto cifrado guardado en: " + ARCHIVO_CIFRADO);

            // Descifrar el texto
            byte[] textoDescifrado = Simetrico.descifrar(secretKey, textoCifrado);
            System.out.print("Texto descifrado (bytes): ");
            imprimir(textoDescifrado);

            // Convertir byte[] a String
            String textoRecuperado = new String(textoDescifrado);
            System.out.println("Texto recuperado: " + textoRecuperado);
            long tiempoFinal = System.nanoTime();
            System.out.println("Tiempo que tarda: "+(tiempoFinal-tiempoInicial));

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
