package client;

import java.time.LocalDate;
import java.util.ArrayList;

import document.Document;

public class Abonne {
    private int numero;
	private static int compteur=1;
	private LocalDate dateNaissance;

	public Abonne(LocalDate d) {
	    assert(d.isBefore(LocalDate.now()));
	    this.numero=compteur;
	    compteur++;
	    this.dateNaissance=d;
	}
	
	//calcule l'âge de l'abonné en fonction de sa date d'anniversaire
	public int age() {
		if(dateNaissance.getDayOfYear()>LocalDate.now().getDayOfYear())
			return LocalDate.now().getYear()-dateNaissance.getYear()-1;
		return LocalDate.now().getYear()-dateNaissance.getYear();
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public LocalDate getDateNaissance() {
		return this.dateNaissance;
	}
	
	public String toString() {
		return("Abonne n�" + this.numero + " ne le " + dateNaissance);
	}
}
