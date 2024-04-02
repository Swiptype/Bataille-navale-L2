package interface_bat_nav;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ecouteur.EcouteurModele;
import modele.game.BatailleNavale;

public class LesPlateaux extends JPanel implements EcouteurModele{
    
    protected BatailleNavale game;
    protected JLabel labPlayer1;
    protected JLabel labPlayer2;
    protected PlateauPanel plateauPanel1;
    protected PlateauPanel plateauPanel2;
    
    public LesPlateaux(BatailleNavale game) {
        this.game = game;

        this.plateauPanel1 = new PlateauPanel(game, game.getPlayer1(), 10, 10);
        this.plateauPanel2 = new PlateauPanel(game, game.getPlayer2(), 10, 10);

        this.setLayout(new BorderLayout(5,1));
        JPanel pan = new JPanel();
        this.labPlayer1 = new JLabel(game.getPlayer1().toString());
        this.labPlayer2 = new JLabel(game.getPlayer2().toString());
        this.colorCurrentPlayer();
        pan.add(this.labPlayer1);
        pan.add(this.labPlayer2);
        this.add(pan, BorderLayout.NORTH);
        this.add(this.plateauPanel1, BorderLayout.WEST);
        this.add(this.plateauPanel2, BorderLayout.EAST);
        game.ajoutEcouteur(this);
    }

    /**
    La fonction colorCurrentPlayer() permet de changer la couleur des joueurs dans l'affichage graphique
    pour pouvoir savoir qui joue.
    @require Game.getCurrentPlayer()
    @result void
    */
    public void colorCurrentPlayer() {
        if (game.getCurrentPlayer() == game.getPlayer1()) {
            this.labPlayer1.setForeground(new Color(255,0,0));
            this.labPlayer2.setForeground(new Color(0,0,0));
        } else {
            this.labPlayer2.setForeground(new Color(255,0,0));
            this.labPlayer1.setForeground(new Color(0,0,0));
        }
    }

    /**
    La fonction modeleMaj(Object src) permet de mettre à jour l'affichage graphique si il y a un changement dans les plateaux.
    @param src est de la classe 'Object'
    @result void
    */
    public void modeleMAJ(Object src) {
        if (this.getGraphics() != null) {
            this.repaint();
            this.colorCurrentPlayer();
        }
        if (this.game.isOver()) {
            JFrame frame = new JFrame("FIN");
            String vainqueurString = "Le vainqueur est le joueur " + (this.game.getWinner() == this.game.getPlayer1()? "1" : "2") + "!";
            JLabel label = new JLabel(vainqueurString);
            frame.setPreferredSize(new Dimension(200,100));
            frame.setContentPane(label);
            frame.setVisible(true);
            frame.pack();
            frame.setLocationRelativeTo(null);
        }
    }

}
