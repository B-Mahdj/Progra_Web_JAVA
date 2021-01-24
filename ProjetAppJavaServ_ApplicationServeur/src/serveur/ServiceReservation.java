package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Mediatheque.Mediatheque;
import exception.ReservationException;

public class ServiceReservation implements Runnable{

        private final Socket abonne;
        ServiceReservation(Socket socket) {
            this.abonne = socket;
        }

        public void run() {
            System.out.println("**Connexion démarrée");
            try {
                BufferedReader in = new BufferedReader( new InputStreamReader(abonne.getInputStream	()));
                PrintWriter out = new PrintWriter (abonne.getOutputStream(), true);
                
                String nAbonne = in.readLine();
                String nDocument = "";
                
                while(nDocument == "") {
                	nDocument = in.readLine();
                }
                
                int numeroDocument = Integer.parseInt(nDocument);
                int numeroAbonne = Integer.parseInt(nAbonne);
                boolean AbonneTrouve = false;
                boolean DocumentTrouve = false;
                
                for(int i = 0; i < Mediatheque.getListeAbonnes().size(); i++)
                {
                	for(int j = 0; j < Mediatheque.getListeDocuments().size(); j++)
                	{
                		if(Mediatheque.getListeAbonnes().get(i).getNumero() == numeroAbonne) {
                			AbonneTrouve = true;
                			if(Mediatheque.getListeDocuments().get(j).numero() == numeroDocument) {
                				DocumentTrouve = true;
                				try {
    								Mediatheque.getListeDocuments().get(j).reservationPour(Mediatheque.getListeAbonnes().get(i));
    							} catch (ReservationException e) {

    								e.printStackTrace();
    							}

                			} 
                		}
                	}
                }
                
                String reponse = "";
                
                if(!AbonneTrouve && !DocumentTrouve)
                	reponse = "Impossible, le numéro d'abonné et le numéro du document sont incorrects\n";
                else if(!AbonneTrouve)
                	reponse = "Impossible, le numéro d'abonné est incorrect.\n";
                else if(!DocumentTrouve)
                	reponse = "Impossible, le numéro du document est incorrect\n";
                else
                	reponse+="Traitement de la demande faite avec succès.\n";
                
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