package geoanalytique.model;
 
import geoanalytique.controleur.GeoAnalytiqueControleur;
import geoanalytique.exception.VisiteurException;
import geoanalytique.util.GeoObjectVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe de base pour les polygones.
 * 
 */
public abstract class Polygone extends Surface {
    private  List<Point> points;
    private GeoAnalytiqueControleur controleur;
    private String name;


    public Polygone (Collection<Point> controles,GeoAnalytiqueControleur controleur) {
        super(controleur); 
        this.controleur = controleur;
        this.points = new ArrayList<>(controles);
    	// TODO: a completer d
    }
    
    public Polygone (String name,Collection<Point> controles,GeoAnalytiqueControleur controleur) {
        super(name,controleur); 
        this.controleur = controleur;
        this.points = new ArrayList<>(controles);
        this.name = name;
        // TODO: a completer d
    }

    public abstract Segment getSegment (int nb);
    
	@Override
    public boolean contient(Point p) {
    int n = points.size();
    boolean inside = false;

    for (int i = 0, j = n - 1; i < n; j = i++) {
        Point pi = points.get(i);
        Point pj = points.get(j);

        boolean intersect = ((pi.getY() > p.getY()) != (pj.getY() > p.getY())) &&
                (p.getX() < (pj.getX() - pi.getX()) * (p.getY() - pi.getY()) / (pj.getY() - pi.getY()) + pi.getX());

        if (intersect) inside = !inside;
    }

    return inside;
    }


    public Collection<Point> getPoints() {
        return this.points;
    }
    
    @Override
	public <T> T visitor(GeoObjectVisitor<T> obj) throws VisiteurException {
            // TODO: a completer d
        return obj.visitPolygone(this);
	}
}

