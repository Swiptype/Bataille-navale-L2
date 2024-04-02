package modele.player;

import java.util.Scanner;

//import interface_bat_nav.Affichage;
import modele.game.Game;

public class Humain implements Player{
    
    protected Scanner scan;
    protected String nom;

    public Humain(String nom, Scanner scan) { //Juste le Scanner pour le moment, penser a ajouter Affichage
        this.nom = nom;
        this.scan = scan;
    }

    /**
     * La fonction chooseMove(Game) permet de savoir quel coup joue le joueur.
     * @param jeu est un Object de la classe (interface) Game
     * @return (char[]) le coup du joueur
    */
    public char[] chooseMove(Game jeu) {
        System.out.println("Attaques posssibles : " + jeu.validMoveToString());
        System.out.println("\nVotre attaque :");
        char[] move = (scan.next()).toCharArray();
        if (jeu.isValid(move)){
            return move;
        }
        System.out.println("Attaquer ici n'est pas possible ! Attaquez ailleurs !");
        return chooseMove(jeu);
    }

    public String toString(){
        return this.nom;
    }

}
