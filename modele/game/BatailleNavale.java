package modele.game;

import java.util.ArrayList;

import ecouteur.AbstractModeleEcoutable;
import modele.player.Humain;
import modele.player.Player;

public class BatailleNavale extends AbstractModeleEcoutable implements Game{

    protected Player joueur1, joueur2, currentPlayer;
    protected Plateau mer1, mer2;

    public BatailleNavale(Player joueur1, Player joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.mer1 = new Plateau();
        this.mer2 = new Plateau();
        this.currentPlayer = this.joueur1;
    }

    
    /**
    La fonction getCurrentPlayer() permet de récupérer le joueur courrant.
    @result (Player) le joueur courant
    */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * La fonction getPlayer1() permet de récupérer le joueur 1.
     * @return Player
    */
    public Player getPlayer1() {
        return this.joueur1;
    }

    /**
     * La fonction getPlayer2() permet de récupérer le joueur 1.
     * @return Player
    */
    public Player getPlayer2() {
        return this.joueur2;
    }

    /**
    La fonction changeCurrentPlayer() permet de changer le joueur courant.
    @result void
    */
    public void changeCurrentPlayer() {
        if (this.currentPlayer == this.joueur1) {
            this.currentPlayer = this.joueur2;
        } else {
            this.currentPlayer = this.joueur1;
        }
    }

    /**
    La fonction execute(char[] move) permet d'effectuer un coup tel que ['A','1'].
    @param move est une liste d'éléments de la classe char
    @require move.length == 2
    @require 65 <= (int) move[0] && (int) move[0] < 75
    @require 0 <=  Integer.valueOf(String.valueOf(move[1])) &&  Integer.valueOf(String.valueOf(move[1])) < 10
    @result void
    */
    public void execute(char[] move) {
        if (this.currentPlayer == this.joueur1) {
            char lettre = move[0];
            int x = lettre - 65;
            int y = Integer.valueOf(String.valueOf(move[1]));
            this.mer2.attaquer(x, y);
        }
        if (this.currentPlayer == this.joueur2) {
            char lettre = move[0];
            int x = lettre - 65;
            int y = Integer.valueOf(String.valueOf(move[1]));
            this.mer1.attaquer(x, y);
        }
        this.changeCurrentPlayer();
        this.fireChange();
    }

    /**
    La fonction getPlateauPlayer(Player vPlayer) permet de récupérer le plateau du joueur en argument.
    @param vPlayer
    @return
    */
    public Plateau getPlateauPlayer(Player vPlayer) {
        if (this.joueur1 == vPlayer) {
            return this.mer1;
        }
        if (this.joueur2 == vPlayer) {
            return this.mer2;
        }
        return null;
    }
    /**
    La fontion getPlateauPlayer1() permet de récupérer le plateau du joueur 1.
    @result (Plateau) le plateau du joueur 1
    */
    public Plateau getPlateauPlayer1() {
        return this.mer1;
    }

    /**
    La fontion getPlateauPlayer1() permet de récupérer le plateau du joueur 1.
    @result (Plateau) le plateau du joueur 1
    */
    public Plateau getPlateauPlayer2() {
        return this.mer2;
    }

    /**
    La fonction isOver() renvoie un booléen qui nous dit si le jeu est fini.
    @require Plateau.toutEstCoule()
    @result (boolean)
    */
    public boolean isOver() {
        return this.mer1.toutEstCoule() || this.mer2.toutEstCoule();
    }

    /**
    La fonction isValid(char[] move) renvoie un booléen qui nous dit si le coup en paramètre peut être joué.
    @param move est une liste d'éléments de la classe char
    @require move.length == 2
    @require 65 <= (int) move[0] && (int) move[0] < 75
    @require 0 <=  Integer.valueOf(String.valueOf(move[1])) &&  Integer.valueOf(String.valueOf(move[1])) < 10
    @result (boolean)
    */
    public boolean isValid(char[] move) {
        if (this.currentPlayer == this.joueur1) {
            char lettre = move[0];
            int x = lettre - 65;
            int y = Integer.valueOf(String.valueOf(move[1]));
            return !this.mer2.getXY(x, y).getEstTouche();
        }
        if (this.currentPlayer == this.joueur2) {
            char lettre = move[0];
            int x = lettre - 65;
            int y = Integer.valueOf(String.valueOf(move[1]));
            return !this.mer1.getXY(x, y).getEstTouche();
        }
        return false;
    }

    /**
    La fonction validMoves() permet de récupérer la liste des coups valides.
    @require Plateau.LARGEUR_PLATEAU
    @result (ArrayList<char[]>) la liste des coups valides
    */
    public ArrayList<char[]> validMoves() {
        ArrayList<char[]> validMoves = new ArrayList<char[]>();
        for (int i = 0; i < Plateau.LARGEUR_PLATEAU; i++) {
            for (int j = 0; j < Plateau.LARGEUR_PLATEAU; j++) {
                char[] move = new char[2];
                int number = 65+i;
                move[0] = (char) number;
                move[1] = String.valueOf(j).charAt(0);
                if (this.isValid(move)) {
                    validMoves.add(move);
                }
            }
        }
        return validMoves;
    }

    /**
    La fonction situationToString() permet de renvoyer une version String qui traduit la situation de la partie.
    @result (String)
    */
    public String situationToString() {
        String text = "La mer du joueur 1 : \n";
        text += this.mer1.plateauToString();
        text += "--------------------\n";
        text += "La mer du joueur 2 : \n";
        text += this.mer2.plateauToString();
        return text;
    }

    /**
    La fonction moveToString(char[] move) permet de renvoyer une version String du coup
    @result (String)
    */
    public String moveToString(char[] move) {
        return String.valueOf(move[0]) + String.valueOf(move[1]);
    }

    /**
    La fonction validMoveToString() permet de renvoyer une version String de la liste des coups valides
    @result (String)
    */
    public String validMoveToString() {
        String text = "";
        for (char[] move : this.validMoves()) {
            text += this.moveToString(move) + " / ";
        }
        return text;
    }

    /**
    La fonction getWinner() permet de renvoyer le joueur qui a gagné la partie.
    @result (Player)
    */
    public Player getWinner() {
        if (this.isOver()) {
            if (this.mer1.toutEstCoule()) {
                return this.joueur2;
            }
            if (this.mer2.toutEstCoule()) {
                return this.joueur1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Player j1 = new Humain("Albert", null);
        Player j2 = new Humain("Eric", null);
        BatailleNavale game = new BatailleNavale(j1, j2);
        System.out.println(game.validMoveToString());
    }

}
