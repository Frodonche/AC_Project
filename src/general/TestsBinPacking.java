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
		main.fractionalPacking(10, 2, 4, 6, 8);
	}
	
	
	public void testFirstFitPacking() {
		System.out.println("-- testFirstFitPacking --");
		main.firstFitPacking(10, 2, 4, 6, 8);
	}
}
