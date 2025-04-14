import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class MainA {
    private final static String ALGORITMO ="RSA";
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
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
            generator.initialize(1024);
            KeyPair keyPair = generator.generateKeyPair();
            PublicKey llavePublica = keyPair.getPublic();
            PrivateKey llavePrivada = keyPair.getPrivate();


            // Cifrar el texto
            byte[] textoCifrado = Asimetrico.cifrar(llavePublica,ALGORITMO, mensaje);
            System.out.print("Texto cifrado (bytes): ");
            imprimir(textoCifrado);

            // Descifrar el texto
            byte[] textoDescifrado = Asimetrico.descifrar(llavePrivada,ALGORITMO, textoCifrado);
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
