package general;

import java.util.ArrayList;
import java.util.Collections;

public class MainBinPacking {
	
	public MainBinPacking() {
	}
	
	public int fractionalPacking(int tailleBoite, int...lesObjets) {
		int total = 0;
		int nbBoites;
		for(int objet : lesObjets) {
			total += objet;
		}
		if(total % tailleBoite == 0) // si on remplit totalement un certain nombre de boites
			nbBoites = total / tailleBoite;
		else // si une boite est partiellement remplie, il faut ajouter un au resultat de la division
			nbBoites = (total / tailleBoite)+1;
		System.out.println("La liste d'objets peut etre rangee dans un minimum de "+nbBoites+" boites de taille "+tailleBoite);
		return nbBoites;
	}
	
	public int firstFitPacking(int tailleBoite, int...lesObjets) {
		boolean objetTropGros = checkObjetTropGros(tailleBoite, lesObjets);
		if(objetTropGros == false) { // si aucun objet n'est plus gros que la taille des boites
			boolean caRentre; // utilise pour savoir si l'objet a reussi a rentrer dans les boites presentes ou si il faut en creer une nouvelle
			int cpt; // compteur pour la boucle while
			String tempAff = ""; 
			
			// on cree une liste pour ranger les boites
			// chaque nombre represente l'espace utilise dans une boite
			ArrayList<Integer> lesBoites = new ArrayList<Integer>(); 
			ArrayList<String> lesBoitesAff = new ArrayList<String>(); // boites pour l'affichage du remplissage
			
			// on ajoute une boite par defaut
			lesBoites.add(0);
			lesBoitesAff.add("[ ");
			
			for(int objet : lesObjets) {
				caRentre = false;
				cpt = 0;
				while(caRentre == false && cpt < lesBoites.size()) { // tant qu'on n'a pas trouve une boite ou ca rentre ou qu'on n'a pas cherche dans toutes les boites
					if(lesBoites.get(cpt)+objet <= tailleBoite) { // si on arrive a rentrer l'objet dans une boite
						lesBoites.set(cpt, lesBoites.get(cpt)+objet); // met a jour le nombre dans la boite
						
						// on met a jour le contenu de la string de la boite
						tempAff = lesBoitesAff.get(cpt); 
						tempAff += objet+" ";
						lesBoitesAff.set(cpt, tempAff);
						
						caRentre = true;
					}
					cpt++;
				}
				if(caRentre == false) { // si on n'a pas trouve de boite qui pouvait contenir l'objet, on en cree une nouvelle et on le met dedans
					lesBoites.add(objet);
					lesBoitesAff.add("[ "+objet+" ");
				}
			}
			// une fois qu'on a traite tous les objets, on "ferme" toutes les boites d'affichage
			for(int i = 0; i < lesBoitesAff.size(); i++) {
				tempAff = lesBoitesAff.get(i);
				tempAff += "]";
				lesBoitesAff.set(i, tempAff);
			}
			
			System.out.println("La liste d'objets peut etre rangee dans "+lesBoites.size()+" boites de taille "+tailleBoite);
			afficherRemplissage(lesBoitesAff);
			
			return lesBoites.size();
		}else {
			System.out.println("Impossible : au moins un objet est plus gros que la taille des boites");
			return -1;
		}
	}
	
	
	public int bestFitPacking(int tailleBoite, int...lesObjets) {
		boolean objetTropGros = checkObjetTropGros(tailleBoite, lesObjets);
		if(objetTropGros == false) { // si aucun objet n'est plus gros que la taille des boites
			boolean caRentre; // utilise pour savoir si l'objet a reussi a rentrer dans les boites presentes ou si il faut en creer une nouvelle
			int cpt; // compteur pour la boucle while
			String tempAff = ""; 
			int meilleureBoite; // utilise pour stocker l'indice de la meilleure boite pour l'objet en cours
			int meilleureTaille; // raccord a meilleureBoite
			
			// on cree une liste pour ranger les boites
			// chaque nombre represente l'espace utilise dans une boite
			ArrayList<Integer> lesBoites = new ArrayList<Integer>(); 
			ArrayList<String> lesBoitesAff = new ArrayList<String>(); // boites pour l'affichage du remplissage
			
			// on ajoute une boite par defaut
			lesBoites.add(0);
			lesBoitesAff.add("[ ");
			
			for(int objet : lesObjets) {
				caRentre = false;
				cpt = 0;
				meilleureTaille = -1;
				meilleureBoite = -1;
				
				while(cpt < lesBoites.size()) { // tant qu'on n'a pas cherche dans toutes les boites
					if(lesBoites.get(cpt)+objet <= tailleBoite) { // si on arrive a rentrer l'objet dans une boite
						if(tailleBoite-(lesBoites.get(cpt)+objet) > meilleureTaille) {
							meilleureTaille = tailleBoite-(lesBoites.get(cpt)+objet); // on stocke la nouvelle meilleure taille
							meilleureBoite = cpt; // ainsi que l'indice de la meilleure boite
						}
						caRentre = true;
					}
					cpt++;
				}
				if(caRentre == false) { // si on n'a pas trouve de boite qui pouvait contenir l'objet, on en cree une nouvelle et on le met dedans
					lesBoites.add(objet);
					lesBoitesAff.add("[ "+objet+" ");
				}else { // si on a trouve une boite ou ca rentre
					lesBoites.set(meilleureBoite, lesBoites.get(meilleureBoite)+objet); // met a jour le nombre dans la boite
					
					// on met a jour le contenu de la string de la boite
					tempAff = lesBoitesAff.get(meilleureBoite); 
					tempAff += objet+" ";
					lesBoitesAff.set(meilleureBoite, tempAff);
				}
			}
			
			// une fois qu'on a traite tous les objets, on "ferme" toutes les boites d'affichage
			for(int i = 0; i < lesBoitesAff.size(); i++) {
				tempAff = lesBoitesAff.get(i);
				tempAff += "]";
				lesBoitesAff.set(i, tempAff);
			}
			
			System.out.println("La liste d'objets peut etre rangee dans "+lesBoites.size()+" boites de taille "+tailleBoite);
			afficherRemplissage(lesBoitesAff);
			return lesBoites.size();
			
		}else {
			System.out.println("Impossible : au moins un objet est plus gros que la taille des boites");
			return -1;
		}
	}
	
	
	public int firstFitDecreasingPacking(int tailleBoite, int...lesObjets) {
		// On recupere les objets dans une ArrayList
		ArrayList<Integer> listeObjets = new ArrayList<Integer>();
		for(int objet : lesObjets) {
			listeObjets.add(objet);
		}
		
		// On trie l'ArrayList dans l'ordre croissant, puis on la retourne
		Collections.sort(listeObjets);
		Collections.sort(listeObjets, Collections.reverseOrder());
		
		// la liste est consideree comme une liste d'objet et non d'entier, ce qui pose probleme pour l'appel de fonction
		// donc on cree un tableau d'entiers que l'on remplit avec les Integer value des dit objets
		int temp[] = new int[listeObjets.size()];
		for(int i = 0; i < listeObjets.size(); i++) {
			temp[i] = Integer.valueOf(listeObjets.get(i));
		}
		
		// Puis on appelle la methode firstFitPacking
		return firstFitPacking(tailleBoite, temp);
	}
	
	
	public int bestFitDecreasingPacking(int tailleBoite, int...lesObjets) {
		// On recupere les objets dans une ArrayList
		ArrayList<Integer> listeObjets = new ArrayList<Integer>();
		for(int objet : lesObjets) {
			listeObjets.add(objet);
		}
		
		// On trie l'ArrayList dans l'ordre croissant, puis on la retourne
		Collections.sort(listeObjets);
		Collections.sort(listeObjets, Collections.reverseOrder());
		
		// la liste est consideree comme une liste d'objet et non d'entier, ce qui pose probleme pour l'appel de fonction
		// donc on cree un tableau d'entiers que l'on remplit avec les Integer value des dit objets
		int temp[] = new int[listeObjets.size()];
		for(int i = 0; i < listeObjets.size(); i++) {
			temp[i] = Integer.valueOf(listeObjets.get(i));
		}
		
		// Puis on appelle la methode bestFitPacking
		return bestFitPacking(tailleBoite, temp);
	}
	
	
	/**
	 * Check si la taille d'un objet de la liste est plus gros que la taille des boites
	 * Renvoie true si c'est le cas, false sinon
	 */
	public boolean checkObjetTropGros(int tailleBoite, int...lesObjets) {
		for(int objet : lesObjets) {
			if(objet > tailleBoite) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Affiche proprement le remplissage des boites de la liste de boites donnee en parametre
	 */
	public void afficherRemplissage(ArrayList<String> lesBoitesAff) {
		System.out.println("Le remplissage s'est fait de la maniere suivante :");
		for(String boite : lesBoitesAff) {
			System.out.println(boite);
		}
	}
	
	
	public double[] tableauMethode(int choixMethode) {
		double tab[] = new double[10];
		int instancesTest[] = new int[20];
		double hauteur; // a convertir en int au moment d'utiliser les méthodes
		int tailleTemp;
		int tabTest[];
		int sommeTemp;
		
		for(int i = 100; i <= 1000; i = i+100) {
			hauteur = 1.5 * i; // boites de hauteur 1.5 * n
			tabTest = new int[i]; // tableau de test, de taille i
			
			// on effectue 20 tests
			for(int j = 0; j < 20; j++) {
				// on remplit le tableau de i nombres aléatoires
				for(int k = 0; k < i; k++) {
					tailleTemp = (int)(0.2 * i) + (int)(Math.random() * (((0.8*i) - (0.2*i)) + 1));
					tabTest[k] = tailleTemp;
				}
				
				// On effectue l'opération de packing
				switch (choixMethode){
				case 1: // Fractional Packing
					instancesTest[j] = fractionalPacking((int)hauteur, tabTest);
					break;
				case 2: // First Fit Packing
					instancesTest[j] = firstFitPacking((int)hauteur, tabTest);
					break;
				case 3: // Best Fit Packing
					instancesTest[j] = bestFitPacking((int)hauteur, tabTest);
					break;
				case 4: // First Fit Decreasing Packing
					instancesTest[j] = firstFitDecreasingPacking((int)hauteur, tabTest);
					break;
				case 5: // Best Fit Decreasing Packing
					instancesTest[j] = bestFitDecreasingPacking((int)hauteur, tabTest);
					break;
				}
			}
			// On calcule la moyenne de tous les tests et on l'envoie dans le tableau de moyennes
			sommeTemp = 0;
			for(int m = 0; m < 20; m++) {
				sommeTemp += instancesTest[m];
			}
			tab[(i/100)-1] = sommeTemp / 20;
		}
		
		return tab;
	}
}
