package general;

import java.util.ArrayList;

public class MainBinPacking {
	
	public MainBinPacking() {
	}
	
	public void fractionalPacking(int tailleBoite, int...lesObjets) {
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
	}
	
	public void firstFitPacking(int tailleBoite, int...lesObjets) {
		boolean objetTropGros = checkObjetTropGros(tailleBoite, lesObjets);
		if(objetTropGros == false) { // si aucun objet n'est plus gros que la taille des boites
		
			boolean caRentre; // utilise pour savoir si l'objet a reussi a rentrer dans les boites presentes ou si il faut en creer une nouvelle
			int cpt; // compteur pour la boucle while
			
			// on cree une liste pour ranger les boites
			// chaque nombre represente l'espace utilise dans une boite
			ArrayList<Integer> lesBoites = new ArrayList<Integer>(); 
			
			// on ajoute une boite par defaut
			lesBoites.add(0);
			
			for(int objet : lesObjets) {
				caRentre = false;
				cpt = 0;
				while(caRentre == false && cpt < lesBoites.size()) { // tant qu'on n'a pas trouve une boite ou ca rentre ou qu'on n'a pas cherche dans toutes les boites
					if(lesBoites.get(cpt)+objet <= tailleBoite) { // si on arrive a rentrer l'objet dans une boite
						lesBoites.set(cpt, lesBoites.get(cpt)+objet);
						caRentre = true;
					}
					cpt++;
				}
				if(caRentre == false) { // si on n'a pas trouve de boite qui pouvait contenir l'objet, on en cree une nouvelle et on le met dedans
					lesBoites.add(objet);
				}
			}
			System.out.println("La liste d'objets peut etre rangee dans "+lesBoites.size()+" boites de taille "+tailleBoite);
		}else {
			System.out.println("Impossible : au moins un objet est plus gros que la taille des boites");
		}
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
}
