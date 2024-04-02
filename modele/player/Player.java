package modele.player;

import modele.game.Game;

public interface Player {

    /**
     * La fonction chooseMove(Game) permet de savoir quel coup joue le joueur.
     * @param jeu est un Object de la classe (interface) Game
     * @return (char[]) le coup du joueur
    */
    public char[] chooseMove(Game jeu);

}
