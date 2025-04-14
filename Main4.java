import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.crypto.SecretKey;

public class Main4 {
    private final static String ALGORITMO ="AES";
    private final static String ARCHIVO_LLAVE = "llave.secret";
    private final static String ARCHIVO_CIFRADO = "mensajeCifrado.dat";
    public static void main(String[] args) {
        try {
            FileInputStream archivoLlave = new FileInputStream(ARCHIVO_LLAVE);
            ObjectInputStream oisLlave = new ObjectInputStream(archivoLlave);
            SecretKey llave = (SecretKey) oisLlave.readObject();
            oisLlave.close();

            // 2. Recuperar el texto cifrado desde el archivo
            FileInputStream archivoTexto = new FileInputStream(ARCHIVO_CIFRADO);
            ObjectInputStream oisTexto = new ObjectInputStream(archivoTexto);
            byte[] textoCifrado = (byte[]) oisTexto.readObject();
            oisTexto.close();

            // 3. Descifrar el texto
            byte[] textoDescifrado = Simetrico.descifrar(llave, textoCifrado);
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
