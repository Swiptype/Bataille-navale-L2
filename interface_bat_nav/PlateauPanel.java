package interface_bat_nav;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import modele.game.BatailleNavale;
import modele.game.Plateau;
import modele.player.Player;

public class PlateauPanel extends JPanel{

    protected final static String[] ALPHA = {"A","B","C","D","E","F","G","H","I","J"};

    protected BatailleNavale game;
    protected Player joueur;
    protected Plateau plateau;
    protected int row;
    protected int column;

    public PlateauPanel(BatailleNavale game, Player joueur, int row, int column) {
        this.game = game;
        this.joueur = joueur;
        this.plateau = game.getPlateauPlayer(this.joueur);
        this.row = row;
        this.column = column;
        this.setPreferredSize(new Dimension(500,500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / this.column;
        int cellHeight = height / this.row;   

        // Dessine la grille
        for (int i = 0; i < this.column; i++) {
            for (int j = 0; j < this.row; j++) {
                CasePanel caseP = new CasePanel(this.game, this.plateau.getXY(i, j), this.joueur, i, j, cellWidth, cellHeight);
                this.add(caseP);
                caseP.draw(g);
            }
        }
        
    }
}
