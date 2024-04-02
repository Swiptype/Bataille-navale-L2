package modele.player;

import java.util.ArrayList;
import java.util.Random;

import modele.game.Game;

public class Ordi implements Player{
    
    protected String nom;
    public Random alea;

    public Ordi(String nom, Random alea) {
        this.nom = nom;
        this.alea = alea;
    }
    
    /**
     * La fonction chooseMove(Game) permet de savoir quel coup joue le joueur.
     * @param jeu est un Object de la classe (interface) Game
     * @return (char[]) le coup du joueur
    */
    public char[] chooseMove(Game jeu) {
        ArrayList<char[]> listeMoves = jeu.validMoves();
        char[] move = listeMoves.get(this.alea.nextInt(listeMoves.size()));
        System.out.println("Robot adverse : " + jeu.moveToString(move));
        return move;
    }

    @Override
    public String toString() {
        return "Ordi [nom=" + this.nom + "]";
    }

    

}
