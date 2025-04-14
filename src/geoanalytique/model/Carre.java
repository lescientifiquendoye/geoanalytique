
package geoanalytique.model;

import geoanalytique.controleur.GeoAnalytiqueControleur;

public class Carre extends Rectangle {
    private double cote;
    private  String nom ;

    /**
     * Construit un carré à partir du point a, avec une direction horizontale, et une longueur donnée.
     * @param nom Nom de l’objet
     * @param a Point de départ (coin inférieur gauche)
     * @param cote Longueur du côté du carré
     * @param controleur Contrôleur géométrique
     */
    public Carre(String nom, Point a, double cote, GeoAnalytiqueControleur controleur) {
        super(nom,a, cote, cote, controleur);
        this.cote= cote;
        this.nom = nom;
    }

    public double getCote() {
        return cote;
    }

    /**
     * Construit un carré avec une direction quelconque donnée par le vecteur (dx, dy)
     * Le vecteur (dx, dy) sera normalisé pour avoir une direction mais garder la forme carrée.
     */
//    public Carre( Point a, double cote, double dx, double dy, GeoAnalytiqueControleur controleur) {
//        super( a, cote, cote, dx, dy, controleur);
//    }

    @Override
    public double calculerAire() {
//        double cote = a.calculerDistance(b);
        return cote * cote;
    }

}


