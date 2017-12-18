package general;

public class Main {
	
	public Main(){
		System.out.println("Creation du programme");
		
		int puis = puissance(9,118,17);
		System.out.println("9 puissance 118 modulo 17 = "+puis);
		
		int premier = nextPrime();
		System.out.println("Nombre premier au pif : "+premier);
		System.out.println("Est-il bien premier ? "+pseudoprime(premier));
		
	}
	
	private int puissance(int x, int k, int p){
		int result = x;
		if(Math.pow(x, k) >= Math.pow(2, 32)){ // si c'est trop grand pour être correctement calculé
			for (int i = 0; i < k-1; i++){
				result = multiply(result, x, p); // on utilise multiply
			}
		}else{
			result = (int) Math.pow(x, k) % p; // sinon on utilise le pow classique avec le %
		}
		return result;
	}
	
	private int multiply(int x, int y, int p){
		int result = 0;
		while(y != 0){
			if((y & 1) != 0) result = (result + x) % p;
			y >>= 1;
			x = (2*x)%p;
		}
		return result;
	}
	
	private boolean pseudoprime(int p){
		return puissance(2, p-1, p) == 1;
	}
	
	private int nextPrime(){
		int result = 4; // nombre par defaut qui n'est pas premier
		
		while(!pseudoprime(result)){
			result = 2 + (int)(Math.random() * ((Math.pow(2, 23)-1 - 2) + 1));
		}
		return result;
	}
}

