package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Mediatheque.Mediatheque;
import exception.ReservationException;

public class ServiceRetour implements Runnable{

        private final Socket abonne;
        ServiceRetour(Socket socket) {
            this.abonne = socket;
        }

        public void run() {
            System.out.println("**Connexion démarrée");
            try {
                BufferedReader in = new BufferedReader( new InputStreamReader(abonne.getInputStream	()));
                PrintWriter out = new PrintWriter (abonne.getOutputStream(), true);
                
                String nDocument = in.readLine();
                int numeroDocument = Integer.parseInt(nDocument);
                boolean DocumentTrouve = false;
            	for(int j = 0; j < Mediatheque.getListeDocuments().size(); j++)
            			if(Mediatheque.getListeDocuments().get(j).numero() == numeroDocument) 
            			{
            				DocumentTrouve = true;
							Mediatheque.getListeDocuments().get(j).retour();
            			}
            	
            	String reponse = "";
            	
                if(!DocumentTrouve) {
                	reponse = "Impossible, le numéro du document est incorrect\n";
                }
                
                else {
                	reponse = "Traitement de la demande faite avec succès.\n";
                }
                	
                
                out.println(reponse);			
            }
            catch (IOException e) {
            }
            //Fin du service d'inversion
            System.out.println("**Connexion terminée");
            try {abonne.close();} catch (IOException e2) {}
        }

        protected void finalize() throws Throwable {
             abonne.close(); 
        }
}