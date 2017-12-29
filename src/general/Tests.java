package general;

public class Tests {
	Main main;
	
	public Tests(){
		System.out.println("Creation d'une instance Main pour les tests...");
		main = new Main();
		System.out.println("Instance de Main creee !");
	}
	
	/**
	 * Test de puissance (Q1)
	 */
	public void testPuissance(){
		System.out.println("-- testPuissance --");
		int puis = main.puissance(9,118,17);
		System.out.println("9 puissance 118 modulo 17 = "+puis);
		System.out.println();
	}
	
	/**
	 * Nombre premier tire aleatoirement et test si il est bien premier (Q2 et Q3)
	 */
	public void testPremier(){
		System.out.println("-- testPremier --");
		int premier = main.nextPrime();
		System.out.println("Nombre premier au pif : "+premier);
		System.out.println("Est-il bien premier ? "+main.pseudoprime(premier));
		System.out.println();
	}
	
	
	// Test du fingerprint avec "bonjour" (Q4)
	public void testFingerprintTexte(){
		System.out.println("-- testFingerprintTexte --");
		int fp = main.fingerprint(17, "bonjour");
		System.out.println("fingerprint de 'bonjour' avec p = 17 : "+fp);
		System.out.println();
	}
	
	/**
	 * Tests avec differents fichiers (Q4 et Q5)
	 */
	public void testFingerprintFichiers(){
		System.out.println("-- testFingerprintFichiers --");
		main.getFingerPrintFichier("test1", 5407);
		main.getFingerPrintFichier("test2", 5407);
		main.getFingerPrintFichier("test3.xpm", 5407);
		main.getFingerPrintFichier("test4", 5407);
		main.getFingerPrintFichier("test5.xpm", 5407);
		System.out.println();
	}
	
	/**
	 * Test d'une centaine d'execution sur test3.xpm et test4 (Q6)
	 */
	public void testCentaineExecutions(){
		System.out.println("-- testCentaineExecutions -- ");
		main.testNombreuxFP(100, "test3.xpm", "test4");
	}
	
	
	/**
	 * Test si les fichiers test2 et test4 sont respectivement contenus dans les fichiers test5.xpm et test3.xpm 
	 */
	public void testFingerContains() {
		System.out.println("-- testFingerContains -- ");
		
		boolean test25 = main.fingerContains("test2", "test5.xpm");
		System.out.println("test2 est contenu dans test5.xpm : "+test25);
		
		boolean test43 = main.fingerContains("test4", "test3.xpm");
		System.out.println("test4 est contenu dans test3.xpm : "+test43);
	}
	
}
