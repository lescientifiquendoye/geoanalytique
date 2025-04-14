package geoanalytique.model;

import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.exception.VisiteurException;
import geoanalytique.util.GeoObjectVisitor;
import java.awt.Color;
 
/**
 * Classe représentant les points
 *
 */
// TODO: compléter les commentaires javadoc, pour toutes les fonctions publiques
public class Point extends GeoObject {
    private  String name;
    private double x;
    private double y;
    GeoAnalytiqueControleur controleur;
    Color couleur = Color.RED;

    
    // En informatique il est difficile de savoir si deux flottants sont
    // egaux à cause des imprecisions dans les calculs du co-processeur. 
    // Donc pour simplifier nous disons qu'ils sont egaux s'il existe un 
    // delta minuscule (epsilon) entre deux flottants.
    // la valeur choisis a ete prise au hasard
    public static final double DELTA_PRECISION = 0.3;
    
    public Point(double x, double y,GeoAnalytiqueControleur controleur) {
        super(controleur);
        this.controleur = controleur;
        this.x = x;
        this.y = y;
    }
    
    public Point(String name, double x, double y,GeoAnalytiqueControleur controleur) {
        super(name,controleur);
        this.name = name;
        this.controleur=controleur;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    public GeoAnalytiqueControleur getControleur(){
        return this.controleur;
    }
    
    public void setY(double y) {
        this.y = y;
        modifie();
    }
    
    public void setX(double x) {
        this.x = x;
        modifie();
    }
    
    public double calculPente(Point a) {
        // TODO: a completer d
        return (a.getY() - this.y) / (a.getX() - this.x);
    }

    public Point calculMilieu(Point b) {

        return new  Point((this.x+b.x)/2, (this.y+b.y)/2, this.controleur);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // même objet
        if (o == null || getClass() != o.getClass()) return false; // null ou classe différente
    
        Point autrePoint = (Point) o; // cast
        return Double.compare(x, autrePoint.x) == 0 && Double.compare(y, autrePoint.y) == 0;
        // TODO: a completer d
    }
   
    public double calculerDistance(Point b) {
        double dx = this.x - b.x;
        double dy = this.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public void deplacer(double dx, double dy) {
        this.x=dx;
        this.y=dy;
        this.controleur.update(this);   

        // TODO: a completer d
    }

	@Override
	public <T> T visitor(GeoObjectVisitor<T> obj) throws VisiteurException {
		return obj.visitPoint(this);
	}

	@Override
	public boolean contient(Point p) {
		return equals(p);
	}

    public String getNom() {
        return this.name;
    }
}