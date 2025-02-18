package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurEmprunt implements Runnable{
    private ServerSocket listen_socket;
    private static final int PORT_EMPRUNT = 4000;
    
    // Cree un serveur TCP - objet de la classe ServerSocket
    ServeurEmprunt(int port) throws IOException {
        listen_socket = new ServerSocket(PORT_EMPRUNT);
    }
    
    @Override
    public void run() {
        try {
            System.err.println("Lancement du serveur au port "+this.listen_socket.getLocalPort());
            while(true)
                new Thread(new ServiceEmprunt(listen_socket.accept())).start();
        }
        catch (IOException e) { 
            try {this.listen_socket.close();} catch (IOException e1) {}
            System.err.println("Arret du serveur au port "+this.listen_socket.getLocalPort());
        }    
    }
    
     // restituer les ressources --> finalize
    protected void finalize() throws Throwable {
        try {this.listen_socket.close();} catch (IOException e1) {}
    }
}
