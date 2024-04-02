package modele.game;

import java.util.ArrayList;

public class Plateau {

    //Constantes
    protected final static int LARGEUR_PLATEAU = 10;
    protected final static int NOMBRE_BATEAU = 5;
    public final static boolean HORIZONTALE = true;
    protected static int[] TAILLE_BATEAUX = {5,4,3,3,2};
    
    //Attributs
    protected ArrayList<Bateau> bataillon;
    protected Case[][] plateau;
    public boolean remplir;

    //Constructeur
    public Plateau(boolean remplir) {
        this.bataillon = new ArrayList<Bateau>();
        this.plateau = new Case[LARGEUR_PLATEAU][LARGEUR_PLATEAU];
        this.remplirDeCase();
        if(remplir) {
            this.remplirDeBateau(); 
        }
    }
    public Plateau() {
        this(true);
    }

    /**
     * La fonction remplirDeCase() permet de remplir tous les éléments du tableau à 2 dimensions de Case.
     * @return (void)
    */
    public void remplirDeCase() {
        for (int i = 0; i < LARGEUR_PLATEAU; i++) {
            for (int j = 0; j < LARGEUR_PLATEAU; j++) {
                this.plateau[i][j] = new Case();
            }
        }
    }
    
    /**
     * La fonction remplirDeBateau() permet de mettre tous les bateaux dans la grille.
     * @require Plateau.estLibre(int,int,int,boolean)
     * @return (void)
    */
    public void remplirDeBateau() {
        for (int tailleBateau : TAILLE_BATEAUX) {
            int ligneDepart;
            int colonneDepart;
            boolean cas1 = false, cas2 = false;
            do {
                ligneDepart   = (int) (Math.random()*100)%LARGEUR_PLATEAU;
                colonneDepart = (int) (Math.random()*100)%LARGEUR_PLATEAU;

                cas1 = this.estLibre(ligneDepart, colonneDepart, tailleBateau, !HORIZONTALE);
                cas2 = this.estLibre(ligneDepart, colonneDepart, tailleBateau, HORIZONTALE);
            } while ( !(cas1 || cas2) );

            if (cas1) {
                this.mettreBateau(ligneDepart, colonneDepart, tailleBateau, !HORIZONTALE);
                cas2 = false;
            }
            if (cas2) {
                this.mettreBateau(ligneDepart, colonneDepart, tailleBateau, HORIZONTALE);
            }
        }
    }

    /**
     * La fonction getXY(int,int) permet de récupérer un élément de la classe Case à la position (x,y)
     * @param x est un entier
     * @param y est un entier
     * @return (Case) la case en position (x,y).
    */
    public Case getXY(int x, int y) {
        return this.plateau[x][y];
    }

    /**
     * La fonction mettreBateau(int,int,int,boolean) permet de mettre un bateau en fonction des paramètres.
     * @param ligneDepart est un entier
     * @param colonneDepart est un entier
     * @param taille est un entier
     * @param estHorizontal est un boolean
     * @return (void)
    */
    public void mettreBateau(int ligneDepart, int colonneDepart, int taille, boolean estHorizontal) {
        Bateau bateau = new Bateau(ligneDepart, colonneDepart, taille, estHorizontal, this);
        this.bataillon.add(bateau);
        for (int i = 0; i < taille; i++) {
            if (estHorizontal) {
                this.getXY(ligneDepart + i, colonneDepart).setBateau(bateau);
            } else {
                this.getXY(ligneDepart, colonneDepart + i).setBateau(bateau);
            }
        }
    }

    /**
     * La fonction estLibre(int,int,int,boolean) envoie un boolean qui nous dit si il est possible de poser le bateau en fonction des paramètres donnés.
     * @param ligneDepart est un entier
     * @param colonneDepart est un entier
     * @param taille est un entier
     * @param estHorizontal est un entier
     * @return (boolean) true si possible sinon false
    */
    public boolean estLibre(int ligneDepart, int colonneDepart, int taille, boolean estHorizontal){
        boolean ok =true;
        for(int i = 0; i<taille ; i++){

            if (estHorizontal) {
                ok = ok && ligneDepart + i < LARGEUR_PLATEAU && colonneDepart < LARGEUR_PLATEAU && this.getXY(ligneDepart + i, colonneDepart).getBateau() == null;
            }
            else {
                ok = ok && ligneDepart < LARGEUR_PLATEAU && colonneDepart + i < LARGEUR_PLATEAU && this.getXY(ligneDepart, colonneDepart + i).getBateau() == null;
            }
        }
        return ok;
    }

    /**
     * La fonction print() permet d'afficher le plateau dans le terminal
     * @require Plateau.getXY(int,int)
     * @return (void)
    */
    public void print() {
        for (int i = 0; i < LARGEUR_PLATEAU; i++) {
            for (int j = 0; j < LARGEUR_PLATEAU; j++) {
                System.out.print(this.getXY(i, j));
            }
            System.out.println();
        }
    }

    /**
     * La fonction plateauToString() permet de récupérer le plateau en String
     * @require Plateau.getXY(int,int)
     * @return (String) le plateau
    */
    public String plateauToString() {
        String text = "";
        text += "    0 1 2 3 4 5 6 7 8 9\n  ---------------------\n";
        for (int i = 0; i < LARGEUR_PLATEAU; i++) {
            text += String.valueOf((char) (i+65)) + " " + "| ";
            for (int j = 0; j < LARGEUR_PLATEAU; j++) {
                text += this.getXY(i, j).toString() + " ";
            }
            text += "\n";
        }
        return text;
    }

    /**
     * La fonction toutEstCoule() permet de savoir si tous les bateaux sont coulés
     * @return (boolean) true si tous les bateaux sont coulés sinon false
    */
    public boolean toutEstCoule(){
        boolean ok = true;
        for (Bateau bateau  : this.bataillon) {
            ok = ok && bateau.estCoule();
        }
        return ok;
    }

    /**
     * La fonction attaquer(int,int) permet de dire que l'on a joué sur cette case, donc indique à la case qu'elle a été touchée
     * @param x est un entier
     * @param y est un entier
     * @return (void)
    */
    public void attaquer(int x,int y){
        if(x < LARGEUR_PLATEAU && y < LARGEUR_PLATEAU){
            this.getXY(x, y).setEstTouche(true);
        }
    }


    public static void main(String[] args) {
        Plateau plateau = new Plateau();
        plateau.print();
    }

}
