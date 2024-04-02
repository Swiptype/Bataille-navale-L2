package modele.game;

import java.util.ArrayList;

import modele.player.Player;

public interface Game {

    /**
    La fonction getCurrentPlayer() permet de récupérer le joueur courrant.
    @result (Player) le joueur courant
    */
    public Player getCurrentPlayer();

    /**
     * La fonction getPlayer1() permet de récupérer le joueur 1.
     * @return Player
    */
    public Player getPlayer1();

    /**
     * La fonction getPlayer2() permet de récupérer le joueur 1.
     * @return Player
    */
    public Player getPlayer2();

    /**
    La fonction validMoves() permet de récupérer la liste des coups valides.
    @require Plateau.LARGEUR_PLATEAU
    @result (ArrayList<char[]>) la liste des coups valides
    */
    public ArrayList<char[]> validMoves();

    /**
    La fonction isValid(char[] move) renvoie un booléen qui nous dit si le coup en paramètre peut être joué.
    @param move est une liste d'éléments de la classe char
    @require move.length == 2
    @require 65 <= (int) move[0] && (int) move[0] < 75
    @require 0 <=  Integer.valueOf(String.valueOf(move[1])) &&  Integer.valueOf(String.valueOf(move[1])) < 10
    @result (boolean)
    */
    public boolean isValid(char[] move);

    /**
    La fonction execute(char[] move) permet d'effectuer un coup tel que ['A','1'].
    @param move est une liste d'éléments de la classe char
    @require move.length == 2
    @require 65 <= (int) move[0] && (int) move[0] < 75
    @require 0 <=  Integer.valueOf(String.valueOf(move[1])) &&  Integer.valueOf(String.valueOf(move[1])) < 10
    @result void
    */
    public void execute(char[] move);

    /**
    La fonction isOver() renvoie un booléen qui nous dit si le jeu est fini.
    @require Plateau.toutEstCoule()
    @result (boolean)
    */
    public boolean isOver();

    /**
    La fonction getWinner() permet de renvoyer le joueur qui a gagné la partie.
    @result (Player)
    */
    public Player getWinner();

    /**
    La fonction moveToString(char[] move) permet de renvoyer une version String du coup
    @result (String)
    */
    public String moveToString(char[] move);

    /**
    La fonction situationToString() permet de renvoyer une version String qui traduit la situation de la partie.
    @result (String)
    */
    public String situationToString();

    /**
    La fonction validMoveToString() permet de renvoyer une version String de la liste des coups valides
    @result (String)
    */
    public String validMoveToString();

}