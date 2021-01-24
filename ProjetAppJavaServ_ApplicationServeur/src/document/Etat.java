package document;

import client.Abonne;
import etat.EtatEmprunte;
import etat.EtatReserve;
import etat.EtatRetourne;
import exception.EmpruntException;
import exception.ReservationException;

public interface Etat {
	EtatRetourne retourner(Document d);
	EtatReserve reserver(Document d, Abonne ab) throws ReservationException;
	EtatEmprunte emprunter(Document d, Abonne ab)throws EmpruntException;
}
