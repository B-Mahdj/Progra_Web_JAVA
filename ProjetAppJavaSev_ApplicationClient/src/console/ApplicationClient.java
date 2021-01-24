package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ApplicationClient {
	private final static int PORT_SERVEUR_RESERVATION = 3000;
	private final static int PORT_SERVEUR_EMPRUNT = 4000;
	private final static int PORT_SERVEUR_RETOUR = 5000;
	private final static String HOST = "localhost"; 
	
	public static void main(String[] args) throws IOException {
		Socket socket = null;		
		// Cree une socket pour communiquer avec le service se trouvant sur la
		// machine host au port PORT
		Scanner sc = new Scanner(System.in);
		int choixAction = 0;
		
		String informationServeur;
		boolean choixCorrecte = false;
		
		while(!choixCorrecte) {
			
			System.out.println("Que désirez-vous faire ? "
					+ "			\n[1] Retourner un document."
					+ "			\n[2] Reserver un document."
					+ "			\n[3] Emprunter un document.\n");
			
			choixAction = sc.nextInt();
			
			switch(choixAction) {
			case 1 : socket = new Socket(HOST, PORT_SERVEUR_RETOUR);
					 choixCorrecte = true;
					 break;
			case 2 : socket = new Socket(HOST, PORT_SERVEUR_RESERVATION);
					 choixCorrecte = true;
					 break;
			case 3 : socket = new Socket(HOST, PORT_SERVEUR_EMPRUNT);
					 choixCorrecte = true;
			 		 break;
			default : System.out.println("Choix incorrecte, choisissez entre 1, 2 et 3.\n\n");
			}
		}
		// Cree les streams pour lire et ecrire du texte dans cette socket
		BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
		PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
		// Cree le stream pour lire du texte a partir du clavier 
		// (on pourrait aussi utiliser Scanner)
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			
		// Informe l'utilisateur de la connection
		System.out.println("\nConnecté au serveur :"+ socket.getPort());
		
		String nAbonne ="";
		
		if(choixAction != 1) {
			System.out.print("Ecrivez le numero de l'abonnée : ");
			nAbonne = clavier.readLine();
		}
	
		System.out.print("\nEcrivez le numero du document : ");	
		String nDocument = clavier.readLine();
        int choixDocument = Integer.parseInt(nDocument);
        
		if(choixAction != 1) {
			int choixAbonne = Integer.parseInt(nAbonne);
			sout.println(choixAbonne);
		}
		sout.println(choixDocument);
		
		informationServeur = sin.readLine();
		
		System.out.println(informationServeur);
		
		socket.close();
		// Refermer dans tous les cas la socket
		try { if (socket != null) socket.close(); } 
		catch (IOException e2) { ; }		
	}
}
