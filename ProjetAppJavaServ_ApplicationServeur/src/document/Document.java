package document;

import java.time.LocalDateTime;

import Mediatheque.IDocument;
import Mediatheque.LiaisonEmprunte;
import Mediatheque.LiaisonReserve;
import Mediatheque.Mediatheque;
import client.Abonne;
import etat.EtatRetourne;
import exception.EmpruntException;
import exception.ReservationException;

public abstract class Document implements IDocument{

	private static int numeroAuto = 1;
    private int numero;
    private String titre;
    protected Etat etat;

    public Document(String titre) {
        this.numero = numeroAuto;
        this.titre=titre;
        numeroAuto++;
        etat = new EtatRetourne();
    }

	@Override
	public int numero() {
		return this.numero;
	}

	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		this.etat = etat.reserver(this, ab);
	}
	
	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		this.etat = etat.emprunter(this, ab);
	};
	
	@Override
	public void retour() {
		
		synchronized(this) {
			this.etat = etat.retourner(this);
		}
		
	}
}
