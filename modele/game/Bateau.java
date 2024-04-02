package modele.game;

public class Bateau {

    public int ligneDepart,colonneDepart,taille;
    public boolean estHorizontal, estCoule;

    protected Plateau plateau;

    public Bateau(int ligneDepart,int colonneDepart,int taille,boolean estHorizontal, Plateau plateau){
        this.ligneDepart = ligneDepart;
        this.colonneDepart = colonneDepart;
        this.taille = taille;
        this.estHorizontal = estHorizontal;
        this.estCoule = false;

        this.plateau = plateau;
    }
    
    /**
    La fonction getLigneDepart() permet de renvoyer la ligne de le la première case.
    @result int
    */
    public int getLigneDepart(){
        return this.ligneDepart;
    }

    /**
    La fonction getColonneDepart() permet de renvoyer la colonne de le la première case.
    @result int
    */
    public int getColonneDepart(){
        return this.colonneDepart;
    }

    /**
    La fonction getTaille() permet de récupérer la taille du bateau
    @result (int) taille du bateau
    */
    public int getTaille(){
        return this.taille;
    }

    /**
    La fonction estHorizontal() renvoie un booleen qui dit si le bateau est horizontal ou pas
    @result (boolean)
    */
    public boolean estHorizontal(){
        return estHorizontal;
    }

    /**
    La fonction estCoule() renvoie un booléen qui dit si le bateau est coulé
    @require Plateau.getXY(int,int)
    @result (boolean)
    */
    public boolean estCoule(){
        boolean ok =true;
        for(int i = 0; i<taille ; i++){ // j ai enleve taille-1

            if (this.estHorizontal) {
                ok = ok && this.plateau.getXY(this.ligneDepart + i, this.colonneDepart).getEstTouche();
            }
            else {
                ok = ok && this.plateau.getXY(this.ligneDepart, this.colonneDepart + i).getEstTouche();
            }
        }
        return ok;
    }

}
