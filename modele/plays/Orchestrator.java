package modele.plays;

import java.util.Random;

import modele.game.BatailleNavale;
import modele.game.Game;
import modele.player.*;

public class Orchestrator {

    public Game jeu;

    public Orchestrator(Game jeu){
        this.jeu = jeu;
    }

    /**
     * La fonction play() permet de jouer au jeu en attribut de la classe via le terminal.
     * @return (void)
    */
    public void play(){
        int coup = 0;
        while(!jeu.isOver()){
            System.out.println(jeu.situationToString());
            System.out.println("\nA " + jeu.getCurrentPlayer() + " de jouer !");
            char[] move = jeu.getCurrentPlayer().chooseMove(jeu);
            System.out.println(move);
            jeu.execute(move);
            coup++;
        }
        System.out.println(jeu.situationToString());
        if (jeu.getWinner() != null){
            System.out.println("Le vainqueur est : " + jeu.getWinner() + " nb de coup : " + coup);
        }
    }

    public static void main(String[] args) {
        Random alea = new Random();
        Player ordi1 = new Ordi("Jo", alea);
        Player ordi2 = new Ordi("Damien", alea);
        BatailleNavale jeu = new BatailleNavale(ordi1, ordi2);
        Orchestrator root = new Orchestrator(jeu);
        root.play();
    }


}
