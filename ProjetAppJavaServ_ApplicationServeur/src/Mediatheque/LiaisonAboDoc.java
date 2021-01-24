package Mediatheque;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import client.Abonne;

public class LiaisonAboDoc {
	private Abonne a;
	private IDocument d;
	
	public LiaisonAboDoc(Abonne a, IDocument d)
	{
		this.a = a;
		this.d = d;
	}
	
	public Abonne getAbonne() {
		return this.a;
	}
	
	public IDocument getDocument() {
		return this.d;
	}
}
