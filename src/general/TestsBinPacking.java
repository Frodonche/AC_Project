package general;

public class TestsBinPacking {
	MainBinPacking main;
	
	public TestsBinPacking(){
		System.out.println("Creation d'une instance MainBinPacking pour les tests...");
		main = new MainBinPacking();
		System.out.println("Instance de MainBinPacking creee !");
	}
	
	/**
	 * Test du fractional bin packing (Q12)
	 */
	public void testFractionalPacking() {
		System.out.println("-- testFractionalPacking --");
		main.fractionalPacking(10, 8, 4, 2, 6);
	}
	
	/**
	 * Test du first fit packing (Q13)
	 */
	public void testFirstFitPacking() {
		System.out.println("-- testFirstFitPacking --");
		main.firstFitPacking(10, 8, 4, 2, 6);
	}
	
	/**
	 * Test du best fit packing (Q14)
	 */
	public void testBestFitPacking() {
		System.out.println("-- testBestFitPacking --");
		main.bestFitPacking(10, 8, 4, 2, 6);
	}
	
	/**
	 * Test du decreasing first fit packing (Q15)
	 */
	public void testFirstFitDecreasingPacking() {
		System.out.println("-- testFirstFitDecreasingPacking --");
		main.firstFitDecreasingPacking(10, 8, 4, 2, 6);
	}
	
	/**
	 * Test du best fit decreasing packing (Q15)
	 */
	public void testBestFitDecreasingPacking() {
		System.out.println("-- testBestFitDecreasingPacking --");
		main.bestFitDecreasingPacking(10, 8, 4, 2, 6);
	}
}
