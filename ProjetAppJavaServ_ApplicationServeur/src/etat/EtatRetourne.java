package etat;

import java.time.LocalDateTime;

import Mediatheque.LiaisonEmprunte;
import Mediatheque.LiaisonReserve;
import Mediatheque.Mediatheque;
import client.Abonne;
import document.Document;
import exception.EmpruntException;
import exception.ReservationException;

public class EtatRetourne extends AbstractEtat{
	
	@Override
	public EtatRetourne retourner(Document d) {
		return new EtatRetourne();
	}
	
	@Override
	public EtatEmprunte emprunter(Document d, Abonne ab) throws EmpruntException{
		Mediatheque.getListeEmpruntes().add(new LiaisonEmprunte(ab,d));
		return new EtatEmprunte();
	}
	
	@Override
	public EtatReserve reserver(Document d, Abonne ab) throws ReservationException{
		Mediatheque.getListeReserves().add(new LiaisonReserve(ab,d,LocalDateTime.now()));
		return new EtatReserve();
	}
}
