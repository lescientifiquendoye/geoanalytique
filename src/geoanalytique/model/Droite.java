package geoanalytique.model;
 
import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.exception.VisiteurException;
import geoanalytique.util.GeoObjectVisitor;

/**
 * Modele mathematique pour les droites
 * 
 */

public class Droite extends GeoObject {

    // Ce constructeur EST INTERDIT d'utilisation
    // PAR CONSEQUENT IL NE FAUT PAS LE MODIFIER
    // OU MIEUX IL FAUT LE SUPPRIMER.
    // On laisse ce constructeur au debut, pour pouvoir compiler le programme
    // simplement


    Point p;
    double pente;
    private double b; // ordonnée à l'origine



    public Droite() {
       throw new RuntimeException("INTERDICTION D'UTILISER CE CONSTRUCTEUR!!!!") ;
    }
    
    public Droite(Point p, double pente,GeoAnalytiqueControleur controleur) {
       super(controleur);
       this.p=p;
       this.pente=pente;
       // Calcul de b : b = y - a*x
       this.b = p.getY() - pente * p.getX();

       // TODO: a completer b
    }

    public Droite( Point a, Point b,GeoAnalytiqueControleur controleur) {
       this(a,a.calculPente(b),controleur);

    }
    
    @Override
    public boolean equals(Object o) {
        // TODO: a completer
        return false;
    }

    public double getOrdonnée(double x) {
        return this.pente * x + b;
    }


    
   	@Override
	public <T> T visitor(GeoObjectVisitor<T> obj) throws VisiteurException {
            // TODO: a completer
            return null;
	}

	@Override
	public boolean contient(Point p) {
            // TODO: a completer
            return false;
	}
    
}