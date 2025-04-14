import java.util.Scanner;

public class MainI {
    
    public static void main(String[] args) {
        try {
            // Leer texto por teclado
            Scanner scanner = new Scanner(System.in);
            System.out.print("Escriba un mensaje de texto: ");
            String mensaje = scanner.nextLine();
            System.out.print("Ingrese el numero de la opcion para calcular el Digest del mensaje:\n1. MD5 \n2. SHA-1 \n");
            Integer opcion = Integer.parseInt(scanner.nextLine());

            // Imprimir texto original
            System.out.println("Texto original: " + mensaje);
            byte[] mensaje1 = mensaje.getBytes();

            if(opcion == 1){
                // Calcular digest con MD5
                byte[] digest = Digest.getDigest("MD5", mensaje1);
                System.out.print("Digest MD5 obtenido: ");
                Digest.imprimirHexa(digest);
            } else if(opcion == 2){
                // Calcular digest con SHA-1
                byte[] digest = Digest.getDigest("SHA-1", mensaje1);
                System.out.print("Digest SHA-1 obtenido: ");
                Digest.imprimirHexa(digest);
            } else {
                System.out.println("Opcion no valida");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
