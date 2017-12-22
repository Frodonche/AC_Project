package general;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
	private int premierRes;
	
	public Main(){	
		premierRes = 0;
	}
	
	public int puissance(int x, int k, int p){
		int result = x;
		if(Math.pow(x, k) >= Math.pow(2, 32)){ // si c'est trop grand pour etre correctement calcule
			for (int i = 0; i < k-1; i++){
				result = multiply(result, x, p); // on utilise multiply
			}
		}else{
			result = (int) Math.pow(x, k) % p; // sinon on utilise le pow classique avec le %
		}
		return result;
	}
	

	public int multiply(int x, int y, int p){
		int result = 0;
		while(y != 0){
			if((y & 1) != 0) result = (result + x) % p;
			y >>= 1;
			x = (2*x)%p;
		}
		return result;
	}
	
	
	public boolean pseudoprime(int p){
		return puissance(2, p-1, p) == 1;
	}
	
	public int nextPrime(){
		int result = 4; // nombre par defaut qui n'est pas premier
		
		while(!pseudoprime(result)){
			result = 2 + (int)(Math.random() * ((Math.pow(2, 23)-1 - 2) + 1));
		}
		return result;
	}
	
	public int fingerprint(int p, String fn){
		int stringSize = fn.length();
		char tableauDeChar[] = fn.toCharArray(); // on recupere chaque caractère de la String dans un tableau de char
		int puissance; // variable qui va stocker la valeur de la puissance de 256 courante
		int result = 0;
		int valeurASCII;
		
		// on fait le calcul une premiere fois pour le premier cas
		valeurASCII = (int)tableauDeChar[0];
		puissance = 1; // = 256 puissance 0
		result += valeurASCII %p;
		
		// puis on calcule la puissance en fonction de la precedente
		for(int i = 1; i < stringSize; i++){ // Construction de la boucle pour application de la formule
			valeurASCII = (int)tableauDeChar[i]; // on recupere la valeur ASCII du caractère courant
			puissance = puissance%p * 256%p;
			result += (valeurASCII%p * puissance)%p;
		}
		return result%p;
	}
	
	/**
	 * Cette fonction effectue la meme operation que fingerprint, mais pour un tableau de bytes
	 */
	public int fingerprintByte(int p, byte[] tab){
		// on convertit tout d'abord les bytes signes en int
		int[] tabValeurs = byteToInt(tab);
		
		int tabSize = tab.length;
		int puissance; // la variable qui va stocker la valeur de la puissance de 256 courante
		int result = 0;
		
		// on fait le calcul une premiere fois pour le premier cas
		puissance = 1;
		result += tabValeurs[0] %p;
		premierRes = result; // on stocke pour les questions 10 et 11. Sale, mais bon...
		
		// puis on calcule la puissance en fonction de la precedente
		// on applique les modulo le plus tot possible pour empêcher le depassement de taille de nombre
		for(int i = 1; i < tabSize; i++){
			puissance = puissance %p * 256%p;
			result = (result%p) + (tabValeurs[i]%p * puissance)%p;
		}
		return result%p;
	}
	
	
	/**
	 * Fonction qui determine si petit est contenu dans gros en utilisant les fingerprints
	 */
	public boolean fingerContains(String petit, String gros){
		// On recupere les tableaux de byte des deux fichiers
		byte[] petitT = getTabFichier(petit);
		byte[] grosT = getTabFichier(gros);
		
		// On stocke la taille des deux tableaux
		int taillePetit = petitT.length;
		int tailleGros = grosT.length;
		
		// On definit un p aleatoire
		int p = nextPrime();
		
		// On calcule le fingerprint du petit
		int petitFP = fingerprintByte(p, petitT);
		
		boolean trouve = false; // boolean qui determine si on a trouve dans gros un fingerprint identique à petit
		int borneMax = taillePetit; 
		while(!trouve && borneMax <= tailleGros){ // tant qu'on n'a pas trouve de fingerprint identique ou qu'on n'a pas parcouru tout le gros tableau
			
		}
		
		
		return false;
	}
	
	/**
	 * Fonction qui se charge le contenu d'un fichier dans un tableau de bytes
	 */
	public byte[] ouvrirFichier(Path kyo){
		byte[] elTablo = null;
		try {
			elTablo = Files.readAllBytes(kyo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return elTablo;
	}
	
	/**
	 * Fonction qui retourne un tableau
	 * Utile pour retourner un fichier pour les calculs de fingerprint
	 */
	public byte[] retournerTableau(byte[] tab){
		byte temp;
		int tabLength = tab.length;
		for(int i = 0; i < tabLength/2; i++){
			temp = tab[i];
			tab[i] = tab[tabLength - i - 1];
			tab[tabLength - i - 1] = temp;
		}
		return tab;
	}

	/**
	 * Fonction qui convertit un tableau de bytes en tableau d'entiers non signes
	 */
	public int[] byteToInt(byte[] tab){
		int[] toReturn = new int[tab.length];
		
		for(int i = 0; i < tab.length; i++){
			toReturn[i] = tab[i] & 0xFF;
		}
		return toReturn;
	}
	
	/**
	 * Fonction qui calcule le fingerPrint d'un fichier
	 */
	public void getFingerPrintFichier(String nom, int p){
		byte[] elTablo = getTabFichier(nom);
		int fp = fingerprintByte(p, elTablo);
		System.out.println("fingerprint du fichier "+nom+" avec p = "+p+" : "+fp);
	}

	/**
	 * Fonction qui retourne un tableau de bytes a partir du nom du fichier
	 */
	private byte[] getTabFichier(String nom){
		Path kyo = Paths.get("./fichiers_fournis/"+nom);
		byte[] elTablo = ouvrirFichier(kyo);
		elTablo = retournerTableau(elTablo);
		return elTablo;
	}
	
	/**
	 * Fonction qui calcule et affiche le fingerprint de chaque fichier que l'on teste un certain de nombre de fois
	 * nbTest correspond au nombre de test que l'on desire faire
	 * p est choisi aleatoirement
	 */
	public void testNombreuxFP(int nbTests, String... noms){
		ArrayList<byte[]> lesTableaux = new ArrayList<byte[]>();
		ArrayList<String> lesNoms = new ArrayList<String>();
		for(String nom : noms){ // pour chaque nom, on va ouvrir le fichier correspondant et recuperer son tableau de bytes
			lesTableaux.add(this.getTabFichier(nom));
			lesNoms.add(nom);
		}
		
		for(int i = 0; i < nbTests; i++){ // boucle des x tests a effectuer
			int p = nextPrime(); // on prend un nombre premier au hasard
			for(int j = 0; j < lesTableaux.size(); j++){ // et on calcule le fingerprint de chaque fichier
				int fp = fingerprintByte(p, lesTableaux.get(j));
				System.out.println("fingerprint du fichier "+lesNoms.get(j)+" avec p = "+p+" : "+fp);
			}
		}
	}
}

