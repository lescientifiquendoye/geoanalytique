package geoanalytique.model;

import geoanalytique.controleur.GeoAnalytiqueControleur;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * La classe Triangle représente un triangle géométrique défini par trois points.
 * Elle hérite de la classe {@link Polygone}.
 * 
 * Cette classe permet de calculer l'aire, le périmètre et le centre de gravité du triangle.
 * 
 * @author Moussa Ndoye
 * @version 1.0
 */
public class Triangle extends Polygone {

    private String nom;
    private GeoAnalytiqueControleur controleur;
    private  List<Point> points;

    /**
     * Constructeur de la classe Triangle.
     * Initialise un triangle à partir de trois sommets.
     * 
     * @param nom         Le nom du triangle.
     * @param a           Le premier sommet du triangle.
     * @param b           Le deuxième sommet du triangle.
     * @param c           Le troisième sommet du triangle.
     * @param controleur  Le contrôleur principal de l'application.
     */
    public Triangle(String nom, Point a, Point b, Point c, GeoAnalytiqueControleur controleur) {
        super(nom, Arrays.asList(a, b, c), controleur);
        this.nom = nom;
        this.controleur = controleur;
        this.points = Arrays.asList(a, b, c);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Calcule l'aire du triangle en utilisant la formule de Heron.
     * 
     * @return L'aire du triangle.
     */
    @Override
    public double calculerAire() {
        double a = points.get(0).calculerDistance(points.get(1));
        double b = points.get(1).calculerDistance(points.get(2));
        double c = points.get(2).calculerDistance(points.get(0));
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    /**
     * Calcule le périmètre du triangle.
     * 
     * @return Le périmètre du triangle.
     */

    public double calculerPerimetre() {
        double a = points.get(0).calculerDistance(points.get(1));
        double b = points.get(1).calculerDistance(points.get(2));
        double c = points.get(2).calculerDistance(points.get(0));
        return a + b + c;
    }

    /**
     * Calcule le centre de gravité du triangle (médiane des sommets).
     * 
     * @return Le point représentant le centre de gravité.
     */
    @Override
    public Point calculerCentreGravite() {
        double x = (points.get(0).getX() + points.get(1).getX() + points.get(2).getX()) / 3;
        double y = (points.get(0).getY() + points.get(1).getY() + points.get(2).getY()) / 3;
        return new Point(x, y, controleur);
    }

    /**
     * Retourne un des segments du triangle.
     * 
     * @param i L'index du segment (0 à 2).
     * @return Le segment correspondant, ou {@code null} si l'index est invalide.
     */
    @Override
    public Segment getSegment(int i) {
        switch (i) {
            case 0:
                return new Segment(points.get(0), points.get(1), controleur);
            case 1:
                return new Segment(points.get(1), points.get(2), controleur);
            case 2:
                return new Segment(points.get(2), points.get(0), controleur);
            default:
                return null;
        }
    }
}
