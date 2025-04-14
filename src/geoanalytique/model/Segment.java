package geoanalytique.model;

import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.exception.VisiteurException;
import geoanalytique.util.GeoObjectVisitor;
 

/**
 *        Un segment est considerer comme une droite car nous passons par au moins 
 *        1 point et que la pente est aussi definit dans le Segment. En revanche 
 *        on pourra lancer une exception si le traitement ne peut s'appliquer sur 
 *        le segment en cours.
 * 
 * 
 */
public class Segment extends Droite {

    private  Point p1;
    private  Point p2;
    GeoAnalytiqueControleur controleur;

    public Segment (Point a, Point b,GeoAnalytiqueControleur controleur) {
        super(a, b, controleur);
        this.p1=a;this.p2=b; this.controleur=controleur;
        
        // TODO: a completer d
    }
    
    @Override
    public boolean equals(Object o) {
        // TODO: a completer
        return false;
    }
    
        @Override
        public boolean contient(Point p) {
            // Supposons que le segment a deux extrémités a et b
            Point a = this.p1; // méthode pour obtenir l'extrémité A
            Point b = this.p2; // méthode pour obtenir l'extrémité B
        
            // Vérifier si les trois points sont alignés : vecteurs (a->p) et (a->b)
            double cross = (p.getX() - a.getX()) * (b.getY() - a.getY()) - 
                           (p.getY() - a.getY()) * (b.getX() - a.getX());
        
            if (Math.abs(cross) > 1e-6) {
                return false; // pas aligné
            }
        
            // Vérifier si p est entre a et b (inclus)
            double minX = Math.min(a.getX(), b.getX());
            double maxX = Math.max(a.getX(), b.getX());
            double minY = Math.min(a.getY(), b.getY());
            double maxY = Math.max(a.getY(), b.getY());
        
            return (p.getX() >= minX && p.getX() <= maxX &&
                    p.getY() >= minY && p.getY() <= maxY);
        }
        
    public Point getP1() {
        return p1;
    }
    public  Point getP2() {
        return p2;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    public double getLongueur() {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getPente() {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return dy / dx;
    }
    public Point getCentre(){
        return new Point(p1.getX() + p2.getX() / 2, p1.getY() + p2.getY() / 2, controleur)  ;
    }

    
    @Override
	public <T> T visitor(GeoObjectVisitor<T> obj) throws VisiteurException {
            // TODO: a completer d
            return obj.visitSegment(this);
	}
}

