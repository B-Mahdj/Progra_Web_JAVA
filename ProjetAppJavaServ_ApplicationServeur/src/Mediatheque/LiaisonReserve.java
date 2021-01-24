package Mediatheque;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import client.Abonne;

public class LiaisonReserve extends LiaisonAboDoc{
	private final static int DELAI_RESERVATION_EN_HEURE = 2;
	private LocalDateTime dateReservation;
	
	public LiaisonReserve(Abonne a, IDocument d, LocalDateTime date)
	{
		super(a,d);
		this.dateReservation = date;
	}
	
	public long getTempsDeReservation() {
		return ChronoUnit.HOURS.between(LocalDateTime.now(), dateReservation);
	}
	
	public LocalDateTime getDateReservation() {
		return this.dateReservation;
	}
	
	public boolean ReservationOK() {
		return this.getTempsDeReservation() <= DELAI_RESERVATION_EN_HEURE;
	}
}
