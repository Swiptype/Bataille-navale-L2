package interface_bat_nav;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import modele.game.BatailleNavale;
import modele.game.Case;
import modele.player.Player;

public class CasePanel extends JLabel implements MouseListener{

    protected final static Color SEA_COLOR = new Color(3, 34, 76); //seagreen (46,139,87) _ Bleu marine (3, 34, 76)
    protected final static Color BATEAU_TOUCHE_COLOR = Color.RED; //heavy metal (46,45,52)
    protected final static Color BATEAU_DORMANT_COLOR = Color.YELLOW;
    protected final static boolean TRICHE_BATEAU = false;
    
    protected BatailleNavale game;
    protected Case caseInfo;
    protected Player joueur;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public CasePanel(BatailleNavale game, Case caseInfo, Player joueur, int x, int y, int width, int height) {
        //super(x*width, y*height, width, height);
        this.game = game;
        this.caseInfo = caseInfo;
        this.joueur = joueur;

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height; 

        this.setBounds(this.x * this.width, this.y * this.height, this.width, this.height);
        this.addMouseListener(this);
    }

    /**
    La fonction draw(..) permet de dessiner la case sur le graphique au position en attribut.
    @param g est une classe Graphics
    @require 0 <= x && x <= Plateau.LARGEUR_PLATEAU
    @require 0 <= y && y <= Plateau.LARGEUR_PLATEAU
    @result void
    */
    public void draw(Graphics g) {
        g.setColor(SEA_COLOR);
        g.fillRect(this.x * this.width, this.y * this.height, this.width, this.height);
        g.setColor(Color.BLACK);
        g.drawRect(this.x * this.width, this.y * this.height, this.width, this.height);

        if(caseInfo.getBateau() != null) {
            if (caseInfo.getEstTouche()) {
                if (caseInfo.getBateau().estCoule()) {
                    //Ces 2 lignes permettent de dessiner une case (couleur CasePanel.BATEAU_TOUCHE_COLOR) à la position de nos attributs
                    g.setColor(BATEAU_TOUCHE_COLOR);
                    g.fillRect(this.x * this.width, this.y * this.height, this.width, this.height);

                    //Ces conditions permet de dire la position de la case en fonction du bateau.
                    Boolean c1 = caseInfo.getBateau().getLigneDepart() == this.x && caseInfo.getBateau().getColonneDepart() == this.y;
                    Boolean c2 = (caseInfo.getBateau().getLigneDepart()+caseInfo.getBateau().getTaille()-1) == this.x && caseInfo.getBateau().getColonneDepart() == this.y;
                    Boolean c3 = caseInfo.getBateau().getLigneDepart() == this.x && (caseInfo.getBateau().getColonneDepart()+caseInfo.getBateau().getTaille()-1) == this.y;
                    if (c1) { // Ici, la case est la première du bateau.
                        if (caseInfo.getBateau().estHorizontal()) { //Si le bateau est horizontal, on va faire en sorte que seule le côté Est ne s'affiche pas.
                            g.setColor(BATEAU_TOUCHE_COLOR);
                            g.fillRect(this.x * this.width, this.y * this.height, this.width, this.height);
                            g.setColor(Color.BLACK);
                            g.drawLine(this.x*this.width, this.y * this.height, (this.x+1)*this.width, this.y * this.height);
                            g.drawLine(this.x*this.width, this.y * this.height, this.x*this.width, (this.y+1)*this.height);
                            g.drawLine(this.x*this.width, (this.y+1)*this.height, (this.x+1)*this.width, (this.y+1)*this.height);
                        } else {  //Si le bateau est vertical, on va faire en sorte que seule le côté Sud ne s'affiche pas.
                            g.setColor(BATEAU_TOUCHE_COLOR);
                            g.fillRect(this.x * this.width, this.y * this.height, this.width, this.height);
                            g.setColor(Color.BLACK);
                            g.drawLine(this.x*this.width, this.y * this.height, (this.x+1)*this.width, this.y * this.height);
                            g.drawLine(this.x*this.width, this.y * this.height, this.x*this.width, (this.y+1)*this.height);
                            g.drawLine((this.x+1)*this.width, this.y*this.height, (this.x+1)*this.width, (this.y+1)*this.height);
                        }
                    }
                    if (c2) { //Ici, la condition nous dit que notre case est la dernière dans un bateau qui est à l'horizontale donc on enlève le côté Nord
                        g.setColor(BATEAU_TOUCHE_COLOR);
                        g.fillRect(this.x * this.width, this.y * this.height, this.width, this.height);
                        g.setColor(Color.BLACK);
                        g.drawLine(this.x*this.width, this.y * this.height, (this.x+1)*this.width, this.y * this.height);
                        g.drawLine((this.x+1)*this.width, this.y * this.height, (this.x+1)*this.width, (this.y+1)*this.height);
                        g.drawLine(this.x*this.width, (this.y+1)*this.height, (this.x+1)*this.width, (this.y+1)*this.height);
                    }
                    if (c3) { //Ici, la condition nous dit que notre case est la dernière dans un bateau qui est à la verticale donc on enlève le côté Ouest
                        g.setColor(BATEAU_TOUCHE_COLOR);
                        g.fillRect(this.x * this.width, this.y * this.height, this.width, this.height);
                        g.setColor(Color.BLACK);
                        g.drawLine(this.x*this.width, this.y * this.height, this.x*this.width, (this.y+1)*this.height);
                        g.drawLine((this.x+1)*this.width, this.y*this.height, (this.x+1)*this.width, (this.y+1)*this.height);
                        g.drawLine(this.x*this.width, (this.y+1) * this.height, (this.x+1)*this.width, (this.y+1) * this.height);
                    }
                    if (!c1 && !c2 && !c3) { // Ici, le case est ni à la fin et ni au debut du bateau
                        if (caseInfo.getBateau().estHorizontal()) { // Si le bateau est horizontal, on enlèvera les côtés Ouest et Est 
                            g.setColor(BATEAU_TOUCHE_COLOR);
                            g.fillRect(this.x * this.width, this.y * this.height, this.width, this.height);
                            g.setColor(Color.BLACK);
                            g.drawLine(this.x*this.width, this.y * this.height, (this.x+1)*this.width, this.y * this.height);
                            g.drawLine(this.x*this.width, (this.y+1)*this.height, (this.x+1)*this.width, (this.y+1)*this.height);
                        } else { // Si le bateau est vertical, on enlèvera les côtés Nord et Sud
                            g.setColor(BATEAU_TOUCHE_COLOR);
                            g.fillRect(this.x * this.width, this.y * this.height, this.width, this.height);
                            g.setColor(Color.BLACK);
                            g.drawLine(this.x*this.width, this.y * this.height, this.x*this.width, (this.y+1)*this.height);
                            g.drawLine((this.x+1)*this.width, this.y*this.height, (this.x+1)*this.width, (this.y+1)*this.height);
                        }
                    }

                } else {//Ici, si notre case est touché mais que le bateau n'est pas coulé alors on mets un rond rouge
                    g.setColor(BATEAU_TOUCHE_COLOR);
                    g.fillOval(this.x * this.width, this.y * this.height, this.width, this.height);
                }           
            } 
            else { // Qui sera enlevé ! Ici, si la case n'est pas touché et a un bateau, on met un rond jaune
                if (TRICHE_BATEAU) {
                    g.setColor(BATEAU_DORMANT_COLOR);
                    g.fillOval(this.x * this.width, this.y * this.height, this.width, this.height);
                } 
            }
        } else {
            if (caseInfo.getEstTouche()) { //On dessine une croix pour dire que l'on a déjà exploré cette case.
                g.setColor(Color.red);
                g.drawLine(this.x * this.width + 10, this.y * this.height + 10, (this.x + 1) * this.width - 10 , (this.y + 1) * this.height - 10);
                g.drawLine( (this.x + 1) * this.width -10, this.y * this.height +10, this.x * this.width +10, (this.y + 1) * this.height -10);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (game.getCurrentPlayer() != this. joueur && !this.game.isOver() && this.caseInfo.getEstTouche() != true) {
            System.out.println("clicked x : " + Integer.toString(this.x) + " | y : " + Integer.toString(this.y));
            char[] move = new char[2];
            move[0] = (char) (65+this.x);
            move[1] = String.valueOf(this.y).charAt(0);
            game.execute(move);
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

}
