package etat;

import java.time.LocalDateTime;

import Mediatheque.LiaisonEmprunte;
import Mediatheque.LiaisonReserve;
import Mediatheque.Mediatheque;
import client.Abonne;
import document.Document;
import document.Etat;
import exception.EmpruntException;
import exception.ReservationException;

public abstract class AbstractEtat implements Etat{
	
	public EtatRetourne retourner(Document d) {
		
		for(int i = 0; i < Mediatheque.getListeEmpruntes().size(); i++) 
			if(Mediatheque.getListeEmpruntes().get(i).getDocument().numero() == d.numero())
				Mediatheque.getListeEmpruntes().remove(i);
		
		for(int i = 0; i < Mediatheque.getListeReserves().size(); i++) 
			if(Mediatheque.getListeReserves().get(i).getDocument().numero() == d.numero())
				Mediatheque.getListeReserves().remove(i);
		
		return new EtatRetourne();
		
	}
	
	public EtatEmprunte emprunter(Document d, Abonne ab) throws EmpruntException{
		for(int i = 0; i < Mediatheque.getListeReserves().size(); i++) 
			if(Mediatheque.getListeReserves().get(i).getDocument().numero() == d.numero())
				if(Mediatheque.getListeReserves().get(i).getAbonne().getNumero() == ab.getNumero())
				{
					Mediatheque.getListeReserves().remove(i);
					Mediatheque.getListeEmpruntes().add(new LiaisonEmprunte(ab,d));
					return new EtatEmprunte();
				}
					
				else if(Mediatheque.getListeReserves().get(i).ReservationOK())
				{
					throw new EmpruntException("Le document est déjà réservé.");
				}
				else
					Mediatheque.getListeReserves().remove(i);
					
		for(int i = 0; i < Mediatheque.getListeEmpruntes().size(); i++) 
			if(Mediatheque.getListeEmpruntes().get(i).getDocument().numero() == d.numero())
				throw new EmpruntException("Le document est déjà emprunté.");
		Mediatheque.getListeEmpruntes().add(new LiaisonEmprunte(ab,d));
		return new EtatEmprunte();
		
	}

	public EtatReserve reserver(Document d, Abonne ab) throws ReservationException{
		//Vérifier si le DVD est déjà emprunté par quelqu'un
		
		for(int i = 0; i < Mediatheque.getListeEmpruntes().size(); i++) 
			if(Mediatheque.getListeEmpruntes().get(i).getDocument().numero() == d.numero())
				throw new ReservationException("Le document est déjà emprunté.");
		
		//Vérifier si le DVD est déjà réservé pour quelqu'un d'autres
		//et si la date de réservation n'est pas expiré
		
		for(int i = 0; i < Mediatheque.getListeReserves().size(); i++) 
			if(Mediatheque.getListeReserves().get(i).getDocument().numero() == d.numero())
			{
				if(Mediatheque.getListeReserves().get(i).ReservationOK())
				{
					throw new ReservationException("Le document est déjà réservé.");
				}
				else
				{
					Mediatheque.getListeReserves().remove(i);
					Mediatheque.getListeReserves().add(new LiaisonReserve(ab,d,LocalDateTime.now()));
					return new EtatReserve();
				}
			}
		Mediatheque.getListeReserves().add(new LiaisonReserve(ab,d,LocalDateTime.now()));
		return new EtatReserve();
	
	}
}
