package document;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Mediatheque.LiaisonEmprunte;
import Mediatheque.LiaisonReserve;
import Mediatheque.Mediatheque;
import client.Abonne;
import exception.EmpruntException;
import exception.ReservationException;

public class DVD extends Document{
	
	private final static int AGE_ADULTE = 16;
    private Boolean adulte;

    public DVD(String titre,Boolean adulte) {
    	super(titre);
        this.adulte=adulte;
        
    }

	@Override
	public void reservationPour(Abonne ab) throws ReservationException {
		
		synchronized(this) {
			//Vérifier si le DVD est pour adulte et si l'abonné a moins de 16 ans.
			
			if(this.adulte && ab.age() < AGE_ADULTE)
				throw new ReservationException("Le DVD est interdit au moins de "+AGE_ADULTE+ " ans.");
			
			super.reservationPour(ab);
		}
	}
		
	@Override
	public void empruntPar(Abonne ab) throws EmpruntException {
		
		synchronized(this) {
			//Vérifier si le DVD est pour adulte et si l'abonné a moins de 16 ans.
			
			if(this.adulte && ab.age() < AGE_ADULTE)
				throw new EmpruntException("Le DVD est interdit au moins de "+AGE_ADULTE+ " ans.");
			
			super.empruntPar(ab);
		}
		
	}
}
