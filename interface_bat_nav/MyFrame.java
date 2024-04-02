package interface_bat_nav;

import java.util.Random;

//import java.util.Random;

import javax.swing.JFrame;
//import javax.swing.JPanel;

import modele.game.BatailleNavale;
//import modele.player.Ordi;
//import modele.player.Player;
//import modele.game.Plateau;
import modele.player.Ordi;
import modele.player.Player;
//import modele.plays.Orchestrator;

public class MyFrame {
    
    protected JFrame frame;
    protected BatailleNavale game;

    public MyFrame(BatailleNavale game) {
        this.frame = new JFrame("Bataille Navale");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.game = game;
        LesPlateaux lesGrilles = new LesPlateaux(game);
        this.frame.setContentPane(lesGrilles);

        this.frame.setVisible(true);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Random alea = new Random();
        Player ordi1 = new Ordi("Jo", alea);
        Player ordi2 = new Ordi("Damien", alea);
        BatailleNavale game = new BatailleNavale(ordi1, ordi2);
        new MyFrame(game);
    }

}
