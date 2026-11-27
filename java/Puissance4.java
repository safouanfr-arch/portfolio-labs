package Puissance4;

import java.util.Scanner;

public class Puissance4 {
	private static final int VIDE = 0;
	private static final int JOUEUR1 = 1;
	private static final int JOUEUR2 = 2;
	private static final int BORDURE = -1;

	private int[][] grille;
	private int joueurActuel;

	public Puissance4() {
		grille = new int[7][9]; // 7 lignes, 9 colonnes (2 de plus pour les bordures)
		initialiserGrille();
		joueurActuel = JOUEUR1;
	}

	private void initialiserGrille() {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (j == 0 || j == grille[i].length - 1 || i == grille.length - 1) {
					grille[i][j] = BORDURE; // Bordure
				} else {
					grille[i][j] = VIDE; // Vide
				}
			}
		}
	}

	public String jeu(int colonne) {
		if (colonne < 1 || colonne > 7 || grille[0][colonne] != VIDE) {
			return "Coup invalide, veuillez choisir une autre colonne.";
		}

		int ligne = 0;
		while (grille[ligne + 1][colonne] == VIDE) {
			ligne++;
		}

		grille[ligne][colonne] = joueurActuel;
		if (verifierVictoire(joueurActuel)) {
			return "Le joueur " + joueurActuel + " a gagné !";
		}
		if (grillePleine()) {
			return "Match nul !";
		}
		joueurActuel = (joueurActuel == JOUEUR1) ? JOUEUR2 : JOUEUR1;
		return "Coup joué avec succès.";
	}

	private boolean grillePleine() {
		for (int colonne = 1; colonne <= 7; colonne++) {
			if (grille[0][colonne] == VIDE) {
				return false;
			}
		}
		return true;
	}

	private boolean verifierVictoire(int joueur) {
		// Vérification des lignes
		for (int ligne = 0; ligne < 6; ligne++) {
			for (int colonne = 1; colonne < 5; colonne++) {
				if (grille[ligne][colonne] == joueur && grille[ligne][colonne] == grille[ligne][colonne + 1]
						&& grille[ligne][colonne] == grille[ligne][colonne + 2]
						&& grille[ligne][colonne] == grille[ligne][colonne + 3]) {
					return true;
				}
			}
		}

		// Vérification des colonnes
		for (int colonne = 1; colonne < 8; colonne++) {
			for (int ligne = 0; ligne < 3; ligne++) {
				if (grille[ligne][colonne] == joueur && grille[ligne][colonne] == grille[ligne + 1][colonne]
						&& grille[ligne][colonne] == grille[ligne + 2][colonne]
						&& grille[ligne][colonne] == grille[ligne + 3][colonne]) {
					return true;
				}
			}
		}

		// Vérification des diagonales \
		for (int ligne = 0; ligne < 3; ligne++) {
			for (int colonne = 1; colonne < 5; colonne++) {
				if (grille[ligne][colonne] == joueur && grille[ligne][colonne] == grille[ligne + 1][colonne + 1]
						&& grille[ligne][colonne] == grille[ligne + 2][colonne + 2]
						&& grille[ligne][colonne] == grille[ligne + 3][colonne + 3]) {
					return true;
				}
			}
		}

		// Vérification des diagonales /
		for (int ligne = 0; ligne < 3; ligne++) {
			for (int colonne = 4; colonne < 8; colonne++) {
				if (grille[ligne][colonne] == joueur && grille[ligne][colonne] == grille[ligne + 1][colonne - 1]
						&& grille[ligne][colonne] == grille[ligne + 2][colonne - 2]
						&& grille[ligne][colonne] == grille[ligne + 3][colonne - 3]) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		Puissance4 jeu = new Puissance4();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Bienvenue dans le jeu Puissance 4 !");

		while (true) {
			// Afficher la grille
			afficherGrille(jeu.grille);

			// Demander au joueur de choisir une colonne
			System.out.println("Joueur " + jeu.joueurActuel + ", veuillez choisir une colonne (1-7) : ");
			if (!scanner.hasNextInt()) {
				if (!scanner.hasNext()) {
					break;
				}
				scanner.next();
				System.out.println("Entrez un nombre de 1 à 7.");
				continue;
			}
			int colonne = scanner.nextInt();

			// Jouer le coup
			String message = jeu.jeu(colonne);
			System.out.println(message);

			// Vérifier s'il y a un gagnant ou un match nul
			if (message.contains("a gagné") || message.equals("Match nul !")) {
				// Afficher la grille finale
				afficherGrille(jeu.grille);
				break;
			}
		}

		scanner.close();
	}

	private static void afficherGrille(int[][] grille) {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 1; j < grille[i].length - 1; j++) {
				System.out.print(grille[i][j] == -1 ? "| " : grille[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------");
	}
}
