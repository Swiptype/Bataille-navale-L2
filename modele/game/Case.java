package modele.game;

public class Case {

    public Bateau bateau;
    public Boolean estTouche;

    public Case() {
        this.bateau = null;
        this.estTouche = false;
    }

    /**
     * La fonction getBateau() permet de récupérer le bateau de la case.
     * @return (Bateau)
     */
    public Bateau getBateau() {
        return this.bateau;
    }

    /**
     * La fonction getEstTouche() renvoie un boolean qui permet de savoir si la case a été touché.
     * @return (boolean)
    */
    public boolean getEstTouche() {
        return this.estTouche;
    }

    /**
     * La fonction setBateau(Bateau bateau) permet de changer le bateau de la case.
     * @param bateau est un object de la classe Bateau
     * @return (void)
     */
    public void setBateau(Bateau bateau) {
        this.bateau = bateau;
    }

    /**
     * La fonction setEstTouche(boolean estTouche) permet de changer la valeur de 'estTouche' de la case.
     * @param estTouche est un élément de la classe boolean
     * @return (void)
     */
    public void setEstTouche(boolean estTouche) {
        this.estTouche = estTouche;
    }

    /**
     * La fonction estCoule() renvoie un booléen qui permet de savoir si le bateau de la case est coulé.
     * @return (boolean)
    */
    public boolean estCoule() {
        return this.bateau.estCoule();
    }

    public String toString() {
        if (this.bateau != null) {
            if (this.estTouche) {
                return "x";
            }
            return "o";
        } else {
            if (this.estTouche) {
                return "+";
            }
            return "-";
        }

    }
}