package serveur;

import java.io.IOException;
import java.time.LocalDate;

import Mediatheque.Mediatheque;
import client.Abonne;
import document.DVD;
import document.Document;

class ApplicationServeur {
	private final static int PORT_SERVEUR_RESERVATION = 3000;
	private final static int PORT_SERVEUR_EMPRUNT = 4000;
	private final static int PORT_SERVEUR_RETOUR = 5000;

	public static void main(String[] args) {
		try {
			
			Abonne a1 = new Abonne(LocalDate.of(2000, 3, 27));
			Abonne a2 = new Abonne(LocalDate.of(2018, 3, 27));
			Document dvd_batman = new DVD("batman", true);
			Document dvd_spiderman = new DVD("spiderman", false);
			Document dvd_americanHorror = new DVD("americanHorror", true);
			
			Mediatheque.setListeAbonnes(a1);
			Mediatheque.setListeAbonnes(a2);
			Mediatheque.setListeDocuments(dvd_batman);
			Mediatheque.setListeDocuments(dvd_spiderman);
			Mediatheque.setListeDocuments(dvd_americanHorror);
			
			new Thread(new ServeurReservation(PORT_SERVEUR_RESERVATION)).start();
			new Thread(new ServeurEmprunt(PORT_SERVEUR_EMPRUNT)).start();
			new Thread(new ServeurRetour(PORT_SERVEUR_RETOUR)).start();
		} catch (IOException e) {
				System.err.println(e);			
		}
	}
}
