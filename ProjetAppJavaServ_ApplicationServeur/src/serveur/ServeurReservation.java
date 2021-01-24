package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurReservation implements Runnable{
private ServerSocket listen_socket;
private static final int PORT_RESERVATION = 3000;
    
    // Cree un serveur TCP - objet de la classe ServerSocket
    ServeurReservation(int port) throws IOException {
        listen_socket = new ServerSocket(PORT_RESERVATION);
    }

    // Le serveur ecoute et accepte les connexions.
    // pour chaque connexion, il cree un ServiceInversion, 
    // qui va la traiter, et le lance
    public void run() {
        try {
            System.err.println("Lancement du serveur au port "+this.listen_socket.getLocalPort());
            while(true)
                new Thread(new ServiceReservation(listen_socket.accept())).start();
        }
        catch (IOException e) { 
            try {this.listen_socket.close();} catch (IOException e1) {}
            System.err.println("Arrêt du serveur au port "+this.listen_socket.getLocalPort());
        }
    }

     // restituer les ressources --> finalize
    protected void finalize() throws Throwable {
        try {this.listen_socket.close();} catch (IOException e1) {}
    }
}
	

