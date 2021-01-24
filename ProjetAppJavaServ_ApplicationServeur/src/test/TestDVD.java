package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import client.Abonne;
import document.DVD;
import document.Document;
import exception.EmpruntException;
import exception.ReservationException;

class TestDVD {
	
	@Test
	void testReservationPour() {
		Abonne a1=new Abonne(LocalDate.of(2001, 7, 23));
		DVD dvd_batman = new DVD("batman", true);
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.reservationPour(a1);
		});
	}
	
	@Test
	void testReservationPour_Age() {
		Abonne a1=new Abonne(LocalDate.of(2007, 7, 23));
		DVD dvd_batman = new DVD("batman", true);
		ReservationException reserv = assertThrows(ReservationException.class, () -> {
			dvd_batman.reservationPour(a1);
		});
		
		Abonne a2 = new Abonne(LocalDate.of(2001, 12, 22));
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.reservationPour(a2);
		});
	}
	
	@Test
	void testReservationPour_DejaReserve() {
		Abonne a1=new Abonne(LocalDate.of(2001, 7, 23));
		Abonne a2 = new Abonne(LocalDate.of(2001, 12, 22));
		DVD dvd_batman = new DVD("batman", false);
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.reservationPour(a1);
		});
		ReservationException reserv = assertThrows(ReservationException.class, () -> {
			dvd_batman.reservationPour(a2);
		});
	}
	
	@Test 
	void testEmpruntPar() {
		Abonne a1=new Abonne(LocalDate.of(2001, 7, 23));
		DVD dvd_batman = new DVD("batman", true);
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.empruntPar(a1);
		});
	}
	
	@Test
	void testReservation_EmpruntPar() {
		Abonne a1=new Abonne(LocalDate.of(2001, 7, 23));
		DVD dvd_batman = new DVD("batman", true);
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.reservationPour(a1);
		});
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.empruntPar(a1);
		});
	}

	@Test 
	void testEmpruntPar_DejaReserver() {
		Abonne a1=new Abonne(LocalDate.of(2001, 7, 23));
		Abonne a2=new Abonne(LocalDate.of(2001, 7, 23));
		DVD dvd_batman = new DVD("batman", true);
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.reservationPour(a1);
		});
		
		EmpruntException reserv = assertThrows(EmpruntException.class, () -> {
			dvd_batman.empruntPar(a2);
		});
	}
	
	@Test
	void testRetour_Reservation() {
		Abonne a1=new Abonne(LocalDate.of(2001, 7, 23));
		DVD dvd_batman = new DVD("batman", true);
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.reservationPour(a1);
		});
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.retour();
		});
	}
	
	@Test
	void testRetour_Emprunt() {
		Abonne a1=new Abonne(LocalDate.of(2001, 7, 23));
		DVD dvd_batman = new DVD("batman", true);
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.reservationPour(a1);
		});
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.empruntPar(a1);
		});
		
		Assertions.assertDoesNotThrow(() -> {
			dvd_batman.retour();
		});
	}

}
