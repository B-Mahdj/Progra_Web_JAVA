package Mediatheque;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.Abonne;
import document.Document;

public class Mediatheque {
	public static ArrayList<Abonne> listeAbonnes = new ArrayList<>();
	public static ArrayList<Document> listeDocuments = new ArrayList<>();
	public static ArrayList<LiaisonReserve> listeReserves = new ArrayList<>();
	public static ArrayList<LiaisonEmprunte> listeEmpruntes = new ArrayList<>();
	
	public static ArrayList<Abonne> getListeAbonnes()
	{
		return listeAbonnes;
	}
	
	public static ArrayList<Document> getListeDocuments()
	{
		return listeDocuments;
	}
	
	public static void setListeAbonnes(Abonne a)
	{
		listeAbonnes.add(a);
	}
	
	public static void setListeDocuments(Document d)
	{
		listeDocuments.add(d);
	}
	
	public static ArrayList<LiaisonReserve> getListeReserves(){
		return listeReserves;
	}
	public static ArrayList<LiaisonEmprunte> getListeEmpruntes(){
		return listeEmpruntes;
	}
	
	public void setListeDocumentsEmpruntes(Abonne a, IDocument d) {
		listeEmpruntes.add(new LiaisonEmprunte(a,d));
	}
	public void setListeDocumentsReseves(Abonne a, IDocument d) {
		listeReserves.add(new LiaisonReserve(a,d, LocalDateTime.now()));
	}
	
	public void removeListeDocumentsReserves(int r) {
		listeReserves.remove(r);
	}
	public void removeListeDocumentsEmpruntes(int r) {
		listeEmpruntes.remove(r);
	}
}
