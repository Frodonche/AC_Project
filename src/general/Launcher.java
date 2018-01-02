package general;

import java.util.Scanner;

public class Launcher {
	static String menu;

	public static void main(String[] args) {
		System.out.println("Projet AC");

		// Creation de l'instance de tests
		TestsFingerprint testFP = new TestsFingerprint();
		TestsBinPacking testBP = new TestsBinPacking();

		genererMenu();
		boolean fin = false;

		while (!fin) {
			fin = choixMenu(testFP, testBP);
		}
	}

	public static boolean choixMenu(TestsFingerprint testFP, TestsBinPacking testBP) {
		Scanner in;
		String input;
		int num = -1;
		boolean result = false;
		while ((num != 0) && (num < 0 || num > 12)) {
			afficherMenu();
			in = new Scanner(System.in);
			try {
				input = in.next();
				num = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Ceci n'est pas un nombre valide.");
			}
		}
		switch (num) {
		case 0:
			result = true;
			System.out.println("Fermeture de l'application.");
			break;
		case 1:
			testFP.testPuissance(); // Q1
			break;
		case 2:
			testFP.testPremier(); // Q2 Q3
			break;
		case 3:
			testFP.testFingerprintTexte(); // Q4
			break;
		case 4:
			testFP.testFingerprintFichiers(); // Q4 Q5
			break;
		case 5:
			testFP.testCentaineExecutions(); // Q6
			break;
		case 6:
			testFP.testFingerContains(); // Q10 Q11
			break;
		case 7:
			testBP.testFractionalPacking(); // Q12
			break;

		case 8:
			testBP.testFirstFitPacking(); // Q13
			break;
		case 9:
			testBP.testBestFitPacking(); // Q14
			break;
		case 10:
			testBP.testFirstFitDecreasingPacking(); // Q15
			break;
		case 11:
			testBP.testBestFitDecreasingPacking(); // Q15
			break;
		case 12:
			testBP.testMethodesPacking(); // Q16
			break;
		}
		return result;
	}

	public static void genererMenu() {
		menu = "\n";
		menu += "Tests disponibles :\n";
		menu += "FINGERPRINT\n";
		menu += "1- Puissance (Q1)\n";
		menu += "2- Premier (Q2, Q3)\n";
		menu += "3- Fingerprint Texte (Q4)\n";
		menu += "4- Fingerprint Fichiers (Q4, Q5)\n";
		menu += "5- Centaine executions (Q6)\n";
		menu += "6- FingerContains (Q10 Q11)\n\n";
		menu += "BINPACKING\n";
		menu += "7- Fractional packing (Q12)\n";
		menu += "8- First fit packing (Q13)\n";
		menu += "9- Best fit packing (Q14)\n";
		menu += "10- First fit decreasing packing (Q15)\n";
		menu += "11- Best fit decreasing packing (Q15)\n";
		menu += "12- Tableaux test methodes packing (Q16)\n";
		menu += "\nFaites votre choix (0 pour quitter): ";
	}

	public static void afficherMenu() {
		System.out.println(menu);
	}
}
