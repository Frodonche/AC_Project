package general;

public class Launcher {
	public static void main(String[] args){
		System.out.println("Projet AC");
		
		// Creation de l'instance de tests
		Tests monTest = new Tests();
		
		monTest.testPuissance(); // Q1
		monTest.testPremier(); // Q2 Q3
		monTest.testFingerprintTexte(); // Q4
		monTest.testFingerprintFichiers(); // Q4 Q5 
		monTest.testCentaineExecutions(); // Q6
	}
}
