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
			
		}else {
			System.out.println("Impossible : au moins un objet est plus gros que la taille des boites");
		}
	}
	
	
	public void bestFitPacking(int tailleBoite, int...lesObjets) {
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
	
	/**
	 * Affiche proprement le remplissage des boites de la liste de boites donnee en parametre
	 */
	public void afficherRemplissage(ArrayList<String> lesBoitesAff) {
		System.out.println("Le remplissage s'est fait de la maniere suivante :");
		for(String boite : lesBoitesAff) {
			System.out.println(boite);
		}
	}
}
