import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main2 {
    private final static String ALGORITMO ="AES";

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
            SecretKey k1= keygen.generateKey();
            KeyGenerator keygen2 = KeyGenerator.getInstance(ALGORITMO);
            SecretKey k2= keygen2.generateKey();

            // Cifrar el texto
            byte[] tc1 = Simetrico.cifrar(k1, mensaje);
            System.out.print("Texto cifrado (bytes): ");
            imprimir(tc1);

            // Descifrar el texto
            byte[] tc2 = Simetrico.descifrar(k2,tc1);
            System.out.print("Texto descifrado (bytes): ");
            imprimir(tc2);

            // Convertir byte[] a String
            String textoRecuperado = new String(tc2);
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
