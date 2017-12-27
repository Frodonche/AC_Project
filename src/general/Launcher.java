package general;

import java.util.Scanner;

public class Launcher {
	static String menu;
	
	public static void main(String[] args){
		System.out.println("Projet AC");
		
		// Creation de l'instance de tests
		Tests monTest = new Tests();
		
		genererMenu();
		boolean fin = false;
		
		while(!fin) {
			fin = choixMenu(monTest);
		}
	}
	
	public static boolean choixMenu(Tests monTest) {
		Scanner in;
		String input;
		int num = -1;
		boolean result = false;
		while((num != 0)&&(num < 0 || num > 5)) {
			afficherMenu();
			in = new Scanner(System.in);
			try {
				input = in.next();
				num = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Ceci n'est pas un nombre valide.");
			}
		}
		switch(num){
		case 0:
			result = true;
			System.out.println("Fermeture de l'application.");
			break;
		case 1:
			monTest.testPuissance(); // Q1
			break;
		case 2:
			monTest.testPremier(); // Q2 Q3
			break;
		case 3:
			monTest.testFingerprintTexte(); // Q4
			break;
		case 4:
			monTest.testFingerprintFichiers(); // Q4 Q5 
			break;
		case 5:
			monTest.testCentaineExecutions(); // Q6
			break;
		}
		return result;
	}
	
	public static void genererMenu() {
		menu = "\n";
		menu += "Tests disponibles :\n"; 
		menu += "1- Puissance (Q1)\n";
		menu += "2- Premier (Q2, Q3)\n";
		menu += "3- Fingerprint Texte (Q4)\n";
		menu += "4- Fingerprint Fichiers (Q4, Q5)\n";
		menu += "5- Centaine executions (Q6)\n";
		menu += "Faites votre choix (0 pour quitter): ";
	}
	
	public static void afficherMenu() {
		System.out.println(menu);
	}
}
