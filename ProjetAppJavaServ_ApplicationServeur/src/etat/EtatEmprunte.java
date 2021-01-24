package etat;

import Mediatheque.Mediatheque;
import client.Abonne;
import document.Document;
import exception.EmpruntException;
import exception.ReservationException;

public class EtatEmprunte extends AbstractEtat {

	@Override
	public EtatEmprunte emprunter(Document d, Abonne ab) throws EmpruntException{
		throw new EmpruntException("Le document est déjà emprunté.");
	}

	@Override
	public EtatReserve reserver(Document d, Abonne ab) throws ReservationException{
		throw new ReservationException("Le document est déjà emprunté.");
	
	}

}
